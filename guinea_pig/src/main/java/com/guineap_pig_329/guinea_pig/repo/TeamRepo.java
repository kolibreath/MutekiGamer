package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface TeamRepo extends JpaRepository<Team,Integer> {
    List<Team> findByGameId(int gameId);

    @Transactional
    @Query("update Team team set team.gameId = ?1 where team.teamId = ?2")
    @Modifying
    int updateTeamGameId(int newGameId, int teamId);
}
