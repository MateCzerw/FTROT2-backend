package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageSimplifiedMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
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
    private final TaskRepository taskRepository;

    public TeamLeaderService(ApplicationUserRepository applicationUserRepository, WorkPackageRepository workPackageRepository, WorkPackageSimplifiedMapper workPackageSimplifiedMapper, TeamRepository teamRepository, TaskRepository taskRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.workPackageRepository = workPackageRepository;
        this.workPackageSimplifiedMapper = workPackageSimplifiedMapper;
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
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

    public int getAssignedHoursForCurrentWeek(String username, int week, int year) {

        List<ApplicationUser> engineers = applicationUserRepository
                .findEngineersAndLeadEngineersFromTeamByTeamLeaderUsername(username);

        int assignedHours = engineers
                .stream()
                .map(e -> taskRepository.sumTasksByAssignerEngineerIdAndWeekAndYear(e.getId(), week, year))
                .collect(Collectors.summingInt(Integer::intValue));


        return assignedHours;

    }
}