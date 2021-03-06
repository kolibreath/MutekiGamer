package com.guineap_pig_329.guinea_pig.repo;


import com.guineap_pig_329.guinea_pig.dao.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
用户关注的游戏的存储情况
 */
@Repository
public interface UserGameRepo extends JpaRepository<UserGame,Integer> {
    List<UserGame> findAllByUserId(int userId);
    UserGame findByGameId(int gameId);
}
