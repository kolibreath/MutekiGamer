package com.guineap_pig_329.guinea_pig.controller;

import com.guineap_pig_329.guinea_pig.dao.Game;
import com.guineap_pig_329.guinea_pig.repo.GameRepo;
import com.guineap_pig_329.guinea_pig.repo.UserGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("battle")
public class TeamController {

    @Autowired
    GameRepo gameRepo;

    @RequestMapping("/search?p={gameName}")
    public List<Game> search(@PathVariable("gameName")String gameName){
        List<Game> games=gameRepo.findAllByGameName(gameName);
        return games;
    }
}
