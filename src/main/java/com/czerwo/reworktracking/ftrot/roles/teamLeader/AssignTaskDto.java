package com.czerwo.reworktracking.ftrot.roles.teamLeader;

public class AssignTaskDto {

    private long dayId;
    private long engineerId;
    private boolean inBacklog;
    private boolean inUnfinishedTasks;
    private long sorting;

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public long getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(long engineerId) {
        this.engineerId = engineerId;
    }

    public boolean isInBacklog() {
        return inBacklog;
    }

    public void setInBacklog(boolean inBacklog) {
        this.inBacklog = inBacklog;
    }

    public boolean isInUnfinishedTasks() {
        return inUnfinishedTasks;
    }

    public void setInUnfinishedTasks(boolean inUnfinishedTasks) {
        this.inUnfinishedTasks = inUnfinishedTasks;
    }

    public long getSorting() {
        return sorting;
    }

    public void setSorting(long sorting) {
        this.sorting = sorting;
    }
}
