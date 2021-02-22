package com.czerwo.reworktracking.ftrot.models.data.Day;

import java.time.LocalDate;

public class WorkPackageSimplifiedDto {

    private int id;
    private String name;
    private LocalDate deadline;
    private double status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }
}
