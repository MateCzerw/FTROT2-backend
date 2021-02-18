package com.czerwo.reworktracking.ftrot.models.data;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne
    private ApplicationUser teamLeader;

    @OneToMany(mappedBy = "team")
    private Set<ApplicationUser> users = new HashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<WorkPackage> workPackages = new HashSet<>();

    String name;

    public ApplicationUser getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(ApplicationUser teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationUser> users) {
        this.users = users;
    }

    public Set<WorkPackage> getWorkPackages() {
        return workPackages;
    }

    public void setWorkPackages(Set<WorkPackage> workPackages) {
        this.workPackages = workPackages;
    }

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


}
