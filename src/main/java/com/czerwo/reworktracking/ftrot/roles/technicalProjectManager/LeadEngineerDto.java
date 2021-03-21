package com.czerwo.reworktracking.ftrot.roles.technicalProjectManager;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.models.data.UserInfo;

class LeadEngineerDto {

    long id;
    String name;
    String surname;
    String username;

    public static LeadEngineerDto toDto(ApplicationUser leadEngineer, UserInfo leadEngineerUserInfo){
        LeadEngineerDto dto = new LeadEngineerDto();

        dto.setId(leadEngineer.getId());
        dto.setName(leadEngineerUserInfo.getName());;
        dto.setSurname(leadEngineerUserInfo.getSurname());
        dto.setUsername(leadEngineer.getUsername());

        return dto;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
