package com.czerwo.ftrot.roles.technicalProjectManager;



import java.time.LocalDate;


class WorkPackageDto {


        private long id;

        private String name;

        private String description;

        private LocalDate deadline;

        private String leadEngineerUsername;

        private String teamName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeadEngineerUsername() {
        return leadEngineerUsername;
    }

    public void setLeadEngineerUsername(String leadEngineerUsername) {
        this.leadEngineerUsername = leadEngineerUsername;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
