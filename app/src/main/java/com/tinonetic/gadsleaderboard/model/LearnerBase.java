package com.tinonetic.gadsleaderboard.model;

/*
 * The Base learner class
 * */
public abstract class LearnerBase
{
    // TODO: remove, temporary
    private String id;

    private String name;

    private String country;

    private String badgeUrl;

    public LearnerBase(String id, String name, String country, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setBadgeUrl(String badgeUrl){
        this.badgeUrl = badgeUrl;
    }
    public String getBadgeUrl(){
        return this.badgeUrl;
    }
}



