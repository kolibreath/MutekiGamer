package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.GameAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameAttributeRepo extends JpaRepository<GameAttribute,Integer> {
    GameAttribute findAllByGameId(int gameId);
}
