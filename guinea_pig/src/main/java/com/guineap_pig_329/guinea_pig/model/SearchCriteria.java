package com.guineap_pig_329.guinea_pig.model;

import javax.validation.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "username can't empty!")
    private int bannerId;

    public int getBannerId (){
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }
}


