package com.mileminder.domain;

import com.owlike.genson.annotation.JsonProperty;

public class Workout {

    @JsonProperty("activity_type")
    private String activityType;
    private Distance distance;
    private int duration;
    private String title;
    private String felt;

    public Workout() {
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String formatDistance() {
        return distance.toString();
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFelt() {
        return felt;
    }

    public void setFelt(String felt) {
        this.felt = felt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String formatDuration() {
        int hours = duration / 3600;
        duration -= hours * 3600;
        int min = duration / 60;
        duration -= min * 60;
        int sec = duration;
        return String.format("%d:%d:%d", hours, min, sec);
    }

}
