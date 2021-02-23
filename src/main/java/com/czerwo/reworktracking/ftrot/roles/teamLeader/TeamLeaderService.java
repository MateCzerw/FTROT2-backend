package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageSimplifiedMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TeamRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamLeaderService {

    private final ApplicationUserRepository applicationUserRepository;
    private final WorkPackageRepository workPackageRepository;
    private final WorkPackageSimplifiedMapper workPackageSimplifiedMapper;
    private final TeamRepository teamRepository;

    public TeamLeaderService(ApplicationUserRepository applicationUserRepository, WorkPackageRepository workPackageRepository, WorkPackageSimplifiedMapper workPackageSimplifiedMapper, TeamRepository teamRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.workPackageRepository = workPackageRepository;
        this.workPackageSimplifiedMapper = workPackageSimplifiedMapper;
        this.teamRepository = teamRepository;
    }

    public UserInfoDto getUserInfoByUsername(String username) {

        Optional<ApplicationUser> userByUsername = applicationUserRepository
                .findByUsernameWithTeamAndUserInfo(username);

        return UserInfoMapper.toDto(userByUsername);

    }

    public List<WorkPackageSimplifiedDto> getTopFiveWorkPackagesWithClosestDeadline(String username){


        Team team = teamRepository.findByTeamLeaderUsername(username).orElseThrow(() -> new RuntimeException());

        Pageable topFive = PageRequest.of(0, 5);

        List<WorkPackageSimplifiedDto> workPackagesDto = workPackageRepository
                .findTop5ByTeamIdOrderByDeadlineAsc(team.getId(), topFive)
                .stream()
                .map(workPackageSimplifiedMapper::toDto)
                .collect(Collectors.toList());

        return workPackagesDto;
    }
}