package com.guineap_pig_329.guinea_pig.repo;

import com.guineap_pig_329.guinea_pig.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findAllByUserEmail(String useremail);
    User findByUserEmail(String userEmail);
    User findUserByUserId(int userId);
    User findByUserName(String userName);

    @Transactional
    @Query("update User u set u.userPassword = ?1 where u.userId = ?2")
    @Modifying
    int updateUser(String password,int userId);

    User findByUserEmailAndUserPassword(String userEmail,String userPassword);
}

