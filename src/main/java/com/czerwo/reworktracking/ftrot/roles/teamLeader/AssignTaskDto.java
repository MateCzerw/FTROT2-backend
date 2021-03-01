package com.czerwo.reworktracking.ftrot.roles.teamLeader;

public class AssignTaskDto {

    private long dayId;
    boolean isInBacklog;
    private long sorting;

    public long getDayId() {
        return dayId;
    }

    public void setDayId(long dayId) {
        this.dayId = dayId;
    }

    public boolean isInBacklog() {
        return isInBacklog;
    }

    public void setInBacklog(boolean inBacklog) {
        isInBacklog = inBacklog;
    }

    public long getSorting() {
        return sorting;
    }

    public void setSorting(long sorting) {
        this.sorting = sorting;
    }
}
