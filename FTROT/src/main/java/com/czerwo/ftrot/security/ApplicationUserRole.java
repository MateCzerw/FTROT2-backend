package com.czerwo.ftrot.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    TECHNICAL_PROJECT_MANAGER(Sets.newHashSet()),
    TEAM_LEADER(Sets.newHashSet()),//COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    LEAD_ENGINEER(Sets.newHashSet()),//COURSE_READ, STUDENT_READ)),
    ENGINEER(Sets.newHashSet());//COURSE_READ, STUDENT_READ));
    //PRODUCT_GROUP_ENGINEER(Sets.newHashSet());


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
