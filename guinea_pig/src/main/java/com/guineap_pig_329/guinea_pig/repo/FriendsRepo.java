package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 关注 和 被关注都会存储在FriendsRepo
 */
@Repository
public interface FriendsRepo extends JpaRepository<Friends,Integer> {

    Friends findByUserId1AndUserId2(int userId1, int userId2);

    //找到某个用户所有的粉丝
    @Query(value = "select fd from Friends fd  where fd.userId2 = ?1")
    List<Friends> findFollowed(int userId2);

    //找到某个用户关注的所有人
    @Query(value = "select fd from Friends fd  where fd.userId1 = ?1")
    List<Friends> findFollowing(int userId1);
}
