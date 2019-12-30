package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.dao.ResultBean;
import com.guineap_pig_329.guinea_pig.dao.User;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserGameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("battle")
public class TeamController {

    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/search/{gameName}")
    public ResultBean search(@PathVariable("gameName")String gameName){
        List<Game> list = gameRepo.findByGameNameLike(gameName);
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(ResultBean.success_code);
        resultBean.setMessage("查询成功");
        resultBean.setData(list);
       return resultBean;
    }

}
