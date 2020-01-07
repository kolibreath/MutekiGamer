package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeamRepo extends JpaRepository<Team,Integer> {
    List<Team> findByGameId(int gameId);
}
