package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.GameAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAttributeRepo extends JpaRepository<GameAttribute,Integer> {
    GameAttribute findByGameId(int gameId);
}
