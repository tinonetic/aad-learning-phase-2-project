package com.tinonetic.gadsleaderboard.model;

/*
 * Learning Leader model
 * */
public class LearningLeader extends LearnerBase {
    private int hours;

    public LearningLeader(String id, String name, String country, String badgeUrl, int hours) {
        super(id, name, country, badgeUrl);
        this.hours = hours;
    }

    public void setHours(int hours){
        this.hours = hours;
    }

    public int getHours(){
        return this.hours;
    }
}
