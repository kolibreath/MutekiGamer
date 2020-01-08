package com.guineap_pig_329.guinea_pig;

import com.guineap_pig_329.guinea_pig.dao.*;
import com.guineap_pig_329.guinea_pig.dao.wrapper.Cen;
import com.guineap_pig_329.guinea_pig.dao.wrapper.PostWrapper;
import com.guineap_pig_329.guinea_pig.repo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Util {


    public static Cen CEN ;
    public static String PATH = "/Users/kolibreath/Desktop/cen.txt";

    private static UserRepo userRepo;
    private static UserInfoRepo userInfoRepo;
    private static PostRepo postRepo;
    private static ContestRepo contestRepo;
    private static TeamRepo teamRepo;
    private static PlayerRepo playerRepo;
    private static GameRepo gameRepo;
    private static GameAttributeRepo gameAttributeRepo;
    private static BannerRepo bannerRepo;

    public static BannerRepo getBannerRepo() {
        return bannerRepo;
    }

    public static void setBannerRepo(BannerRepo bannerRepo) {
        Util.bannerRepo = bannerRepo;
    }

    public static GameAttributeRepo getGameAttributeRepo() {
        return gameAttributeRepo;
    }

    public static void setGameAttributeRepo(GameAttributeRepo gameAttributeRepo) {
        Util.gameAttributeRepo = gameAttributeRepo;
    }

    public static GameRepo getGameRepo() {
        return gameRepo;
    }

    public static void setGameRepo(GameRepo gameRepo) {
        Util.gameRepo = gameRepo;
    }

    public static UserRepo getUserRepo() {
        return userRepo;
    }

    public static void setUserRepo(UserRepo userRepo) {
        Util.userRepo = userRepo;
    }

    public static UserInfoRepo getUserInfoRepo() {
        return userInfoRepo;
    }

    public static void setUserInfoRepo(UserInfoRepo userInfoRepo) {
        Util.userInfoRepo = userInfoRepo;
    }

    public static PostRepo getPostRepo() {
        return postRepo;
    }

    public static void setPostRepo(PostRepo postRepo) {
        Util.postRepo = postRepo;
    }

    public static ContestRepo getContestRepo() {
        return contestRepo;
    }

    public static void setContestRepo(ContestRepo contestRepo) {
        Util.contestRepo = contestRepo;
    }

    public static TeamRepo getTeamRepo() {
        return teamRepo;
    }

    public static void setTeamRepo(TeamRepo teamRepo) {
        Util.teamRepo = teamRepo;
    }

    public static PlayerRepo getPlayerRepo() {
        return playerRepo;
    }

    public static void setPlayerRepo(PlayerRepo playerRepo) {
        Util.playerRepo = playerRepo;
    }

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
        String content = readJsonStr(path);
        JSONObject jsonObject = JSONObject.fromObject(content);
        return JSONObject.toBean(jsonObject,className); }


    public static List<Object> getInstanceArray(String path, Class className){
        String content = readJsonStr(path);
        JSONArray array = JSONArray.fromObject(content);
        List<Object> list = new LinkedList<>();
        for(int i =0 ;i < array.size();i++){
            list.add(JSONObject.toBean(array.getJSONObject(i),className));
        }

        return list;
    }

    private static String readJsonStr(String path){
        File file = new File(path);
        if(!file.exists() || !file.isFile() ){
            return null;
        }
        StringBuffer content = new StringBuffer();
        try{
            char[] temp = new char[1024];
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            while(reader.read(temp) != -1){
                content.append(new String(temp));
                temp = new char[1024];
            }

            fileInputStream.close();
            reader.close();
        }catch(Exception e){
            return null;
        }
        return content.toString();
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
            if(!innerDir.isDirectory())
                continue;
            Collections.addAll(dirs, innerDir.list());

            User user = new User();
            UserInfo userInfo = new UserInfo();
            Game game = new Game();
            GameAttribute gameAttribute = new GameAttribute();
            List<Post> posts = new LinkedList<>();
            List<Team> teams = new LinkedList<>();
            List<Player> players = new LinkedList<>();
            List<Contest> contests = new LinkedList<>();
            List<Banner> banners = new LinkedList<>();

            //顺序问题
            for (int i = 0;i <dirs.size() ;i++) {
                String pa = dirs.get(i);
                String absoluteP = absolutePath + File.separator+inner +File.separator+ pa;
                String p = pa.split("\\.")[0];
                if (p.endsWith("user")) {
                    user = (User) getInstance(absoluteP, User.class);
                }else if (p.endsWith("userinfo")) {
                    userInfo = (UserInfo) getInstance(absoluteP, UserInfo.class);
                }else if (p.endsWith("game")) {
                    game = (Game) getInstance(absoluteP, Game.class);
                }else if (p.endsWith("attr")) {
                    gameAttribute = (GameAttribute) getInstance(absoluteP, GameAttribute.class);
                }else if (p.endsWith("team")) {
                    String teamName = p.split("-")[1];
                    for (String q : dirs) {
                        String absoluteQ = absolutePath + File.separator+inner+File.separator +q;
                        String tempQ = q.split("\\.")[0];
                        if (tempQ.endsWith("player")) {
                            String playerTeamName = q.split("-")[1];
                            if (teamName.equals(playerTeamName)) {

                                Team team = (Team) getInstance(absoluteP, Team.class);

                                char firstChar = readJsonStr(absoluteQ).charAt(0);
                                if(firstChar == '['){
                                    List<Object> objects = getInstanceArray(absoluteQ,Player.class);
                                    teamRepo.save(team);

                                    teams.add(team);
                                    for(Object object:objects){
                                        Player player = (Player) object;
                                        player.setTeamId(team.getTeamId());
                                        players.add((player));
                                    }
                                }else{
                                    Player player = (Player) getInstance(absoluteQ, Player.class);
                                    teamRepo.save(team);

                                    player.setTeamId(team.getTeamId());
                                    teams.add(team);
                                    players.add(player);
                                }

                                break;
                            }
                        }
                    }
                }else if(p.endsWith("banner")){
                    Banner banner = (Banner) getInstance(absoluteP,Banner.class);
                    banners.add(banner);
                }
                else if (p.contains("contest")) {
                    Contest contest = (Contest) getInstance(absoluteP, Contest.class);
                    contests.add(contest);
                }else if(p.contains("post")){
                    Post post = (Post) getInstance(absoluteP, Post.class);
                    posts.add(post);
                }
            }

            userRepo.save(user);
            gameRepo.save(game);

            userInfo.setUserId(user.getUserId());
            userInfoRepo.save(userInfo);

            gameAttribute.setGameId(game.getGameId());
            gameAttributeRepo.save(gameAttribute);

            for (Post post:posts){
                post.setGameId(game.getGameId());
                postRepo.save(post);
            }
            for(Banner banner:banners){
                banner.setGameId(game.getGameId());
                bannerRepo.save(banner);
            }

            for(Player player:players){
                playerRepo.save(player);
            }

            for(Contest contest:contests){
                contest.setGameId(game.getGameId());
                if (contest.getWinnerId() == 1) {
                    //说明队伍一获胜 从teams中找到队伍一
                    for (Team team : teams) {
                        if (team.getTeamName().equals(contest.getTeamName1())) {
                            contest.setWinnerId(team.getTeamId());
                        }
                    }
                }

                if (contest.getWinnerId() == 2) {
                    //说明队伍二获胜 从teams中找到队伍二
                    for (Team team : teams) {
                        if (team.getTeamName().equals(contest.getTeamName1())) {
                            contest.setWinnerId(team.getTeamId());
                        }
                    }
                }

                contestRepo.save(contest);
            }

            for(Team team:teams){
                team.setGameId(game.getGameId());
                teamRepo.save(team);
            }
        }
    }

}
