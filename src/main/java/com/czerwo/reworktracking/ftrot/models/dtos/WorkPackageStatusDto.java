package com.czerwo.reworktracking.ftrot.models.dtos;

public class WorkPackageStatusDto {
    private int onTime;
    private int stopped;
    private int delayed;

    public int getOnTime() {
        return onTime;
    }

    public void setOnTime(int onTime) {
        this.onTime = onTime;
    }

    public int getStopped() {
        return stopped;
    }

    public void setStopped(int stopped) {
        this.stopped = stopped;
    }

    public int getDelayed() {
        return delayed;
    }

    public void setDelayed(int delayed) {
        this.delayed = delayed;
    }
}
