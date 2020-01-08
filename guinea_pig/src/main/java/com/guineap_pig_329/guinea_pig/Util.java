package com.guineap_pig_329.guinea_pig;

import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.Cen;
import com.guineap_pig_329.guinea_pig.dao.wrapper.PostWrapper;
import com.guineap_pig_329.guinea_pig.repo.UserInfoRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Util {


    public static Cen CEN ;
    public static String PATH = "/Users/kolibreath/Desktop/cen.txt";

    private static Logger getLoggerInstance(Class className){
        return LoggerFactory.getLogger(className);
    }

    /**
     * debug method
     * @param className
     * @param string 输出的内容
     */
    public static void debug(Class className,String string){
        getLoggerInstance(className).debug(string);
    }

    /**
     * info method
     * @param className
     * @param string 输出的内容
     */


    public static void info(Class className,String string){
        getLoggerInstance(className).info(string);
    }

    /**
     *
     * warning method
     * @param className
     * @param string 输出的内容
     */
    public static void warning(Class className,String string){
        getLoggerInstance(className).warn(string);
    }
    /**
     * error method
     * @param className
     * @param string 输出的内容
     */
    public static void error(Class className,String string){
        getLoggerInstance(className).error(string);
    }

    public static Object getInstance(String path, Class className){
        File file = new File(path);
        if(!file.exists() || !file.isFile() ){
            return null;
        }
        StringBuffer content = new StringBuffer();
        try{
            char[] temp = new char[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream,"GBK");
            while(reader.read(temp) != -1){
                content.append(new String(temp));
                temp = new char[1024];
            }

            fileInputStream.close();
            reader.close();
        }catch(Exception e){
            return null;
        }

        JSONObject jsonObject = JSONObject.fromObject(content.toString());
        return JSONObject.toBean(jsonObject,className);
    }

    public static List<PostWrapper> transform(List<Post> posts, UserRepo userRepo, UserInfoRepo userInfoRepo) {
        List<PostWrapper> postWrappers = new ArrayList<>();
        for (Post post : posts) {
            User user = userRepo.findUserByUserId(post.getUserId());
            UserInfo userinfo = userInfoRepo.findUserInfoByUserId(post.getUserId());
            PostWrapper postWrapper = new PostWrapper(
                    post.getPostId(),
                    post.getUserId(),
                    post.getGameId(),
                    post.getTag(),
                    post.getTime(),
                    post.getContent(),
                    post.getTitle(),
                    user.getUserName(),
                    userinfo.getUserAvatar()

            );
            postWrappers.add(postWrapper);
        }
        return postWrappers;
    }


    //所有的都在一个路径下
    public static void genData(String path) {
        File temp = new File("temp.txt");
        String absolutePath = temp.getAbsolutePath().substring(0, temp.getAbsolutePath().length() - 9) + path;
        File dir = new File(absolutePath);
        temp.delete();
        //最外层路径
        if (!dir.isDirectory()) {
            error(Util.class, "文件夹打开失败");
            return;
        }
        for (String inner : dir.list()) {
            //第二层也就是最里层路径
            List<String> dirs = new ArrayList<>();
            File innerDir = new File(absolutePath + inner);
            Collections.addAll(dirs, innerDir.list());

            User user = new User();
            UserInfo userInfo = new UserInfo();
            Game game = new Game();
            GameAttribute gameAttribute = new GameAttribute();
            List<Post> posts = new LinkedList<>();
            List<Team> teams = new LinkedList<>();
            List<Player> players = new LinkedList<>();
            List<Contest> contests = new LinkedList<>();

            //顺序问题
            for (String p : dirs) {
                //todo actual
                if (p.endsWith("user")) {
                    user = (User) getInstance(p, User.class);
                }
                if (p.endsWith("userinfo")) {
                    userInfo = (UserInfo) getInstance(p, UserInfo.class);
                    userInfo.setUserId(user.getUserId());
                }
                if (p.endsWith("game")) {
                    game = (Game) getInstance(p, Game.class);
                }
                if (p.endsWith("attr")) {
                    gameAttribute = (GameAttribute) getInstance(p, GameAttribute.class);
                    gameAttribute.setGameId(game.getGameId());
                }

                if (p.endsWith("team")) {
                    String teamName = p.split(" ")[1];
                    for (String q : dirs) {
                        if (p.endsWith("player")) {
                            String playerTeamName = q.split(" ")[1];
                            if (teamName.equals(playerTeamName)) {
                                Team team = (Team) getInstance(p, Team.class);
                                Player player = (Player) getInstance(q, Player.class);

                                teams.add(team);
                                players.add(player);
                            }
                        }
                    }
                }

                if (p.contains("contest")) {
                    Contest contest = (Contest) getInstance(p, Contest.class);
                    contest.setGameId(contest.getGameId());
                    if (contest.getWinnerId() == 1) {
                        //说明队伍一获胜 从teams中找到队伍一
                        for (Team team : teams) {
                            if (team.getTeamName().equals(contest.getTeamName1())) {
                                contest.setWinnerId(team.getTeamId());
                            }
                        }
                    }

                    if (contest.getWinnerId() == 2) {
                        //说明队伍一获胜 从teams中找到队伍二
                        for (Team team : teams) {
                            if (team.getTeamName().equals(contest.getTeamName1())) {
                                contest.setWinnerId(team.getTeamId());
                            }
                        }
                    }
                    contests.add(contest);
                }

                if (p.contains("post")) {
                    Post post = (Post) getInstance(p, Post.class);
                    posts.add(post);
                }
            }
        }
    }

}
