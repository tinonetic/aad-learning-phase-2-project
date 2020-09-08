package com.tinonetic.gadsleaderboard.model;

/*
 * Skill IQ Leader learner model
 * */
public class SkillIqLeader extends LearnerBase {
    private int score;

    public SkillIqLeader(String id, String name, String country, String badgeUrl, int score) {
        super(id, name, country, badgeUrl);
        this.score = score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }
}
