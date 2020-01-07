package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Official;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficialRepo extends JpaRepository<Official,Integer> {
    Official findByUserId(int userId);
    Official findByGameId(int gameId);
}
