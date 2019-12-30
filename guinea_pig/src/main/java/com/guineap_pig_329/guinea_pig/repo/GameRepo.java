package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepo  extends JpaRepository<Game,Integer> {
    @Query("select game from Game game where game.gameName like %?1%")
    List<Game>  findAllByGameName(String gameName);

}
