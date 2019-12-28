package com.guineap_pig_329.guinea_pig.repo;


import com.guineap_pig_329.guinea_pig.dao.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepo  extends JpaRepository<Banner,Integer> {

    Banner findBannerByBannerId(int id);
}
