package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Contest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestRepo extends JpaRepository<Contest,Integer> {

    List<Contest> findByGameId(int gameId);
    List<Contest> findAllByTeamName1OrTeamName2(String str1,String str2);
}
