package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Official;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficialRepo extends JpaRepository<Official,Integer> {
    Official findAllByGameId(Integer gameId);
}
