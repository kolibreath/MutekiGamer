package com.guineap_pig_329.guinea_pig;

import com.guineap_pig_329.guinea_pig.dao.Post;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.dao.UserInfo;
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
    private static String PATH = "/Users/kolibreath/Desktop/cen.txt";

    private static Logger getInstance(Class className){
        return LoggerFactory.getLogger(className);
    }

    /**
     * debug method
     * @param className
     * @param string 输出的内容
     */
    public static void debug(Class className,String string){
        getInstance(className).debug(string);
    }

    /**
     * info method
     * @param className
     * @param string 输出的内容
     */


    public static void info(Class className,String string){
        getInstance(className).info(string);
    }

    /**
     *
     * warning method
     * @param className
     * @param string 输出的内容
     */
    public static void warning(Class className,String string){
        getInstance(className).warn(string);
    }
    /**
     * error method
     * @param className
     * @param string 输出的内容
     */
    public static void error(Class className,String string){
        getInstance(className).error(string);
    }

    public static Object getCenInstance(String path, Class className){
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
    public static void genData(String path){
        File dir = new File(path);
        if(!dir.isDirectory()){
            error(Util.class,"文件夹打开失败");
            return;
        }
        List<String> files = new ArrayList<>();
        Collections.addAll(files,dir.list());

        LinkedList<String> userQueue = new LinkedList<>();
        LinkedList<String> userInfoQueue = new LinkedList<>();

        for(String p:files){
            //todo actual
        }
    }

}
