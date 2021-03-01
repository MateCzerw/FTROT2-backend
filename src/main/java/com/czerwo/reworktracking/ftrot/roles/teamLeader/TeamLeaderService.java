package com.czerwo.reworktracking.ftrot.roles.teamLeader;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.data.Day.Day;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.data.Team;
import com.czerwo.reworktracking.ftrot.models.data.Week;
import com.czerwo.reworktracking.ftrot.models.dtos.DayDto;
import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WeekDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.reworktracking.ftrot.models.mappers.DayTasksMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.TaskMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WeekDayMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WorkPackageSimplifiedMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.*;
import com.czerwo.reworktracking.ftrot.roles.engineer.EngineerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
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
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final EngineerService engineerService;
    private final DayTasksMapper dayTasksMapper;
    private final TaskMapper taskMapper;
    private final WeekDayMapper weekDayMapper;


    public TeamLeaderService(ApplicationUserRepository applicationUserRepository, WorkPackageRepository workPackageRepository, WorkPackageSimplifiedMapper workPackageSimplifiedMapper, TeamRepository teamRepository, TaskRepository taskRepository, WeekRepository weekRepository, DayRepository dayRepository, EngineerService engineerService, DayTasksMapper dayTasksMapper, TaskMapper taskMapper, WeekDayMapper weekDayMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.workPackageRepository = workPackageRepository;
        this.workPackageSimplifiedMapper = workPackageSimplifiedMapper;
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.engineerService = engineerService;
        this.dayTasksMapper = dayTasksMapper;
        this.taskMapper = taskMapper;
        this.weekDayMapper = weekDayMapper;
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

    public AssignTasksPanelDto getDataForAssignTasksPanelForCurrentWeek(String username, int weekNumber, int yearNumber) {

        AssignTasksPanelDto assignTasksPanelDto = new AssignTasksPanelDto();

        Team teamByByLeaderUsername = teamRepository.findByTeamLeaderUsername(username).orElseThrow(() -> new RuntimeException());

        List<ApplicationUser> engineers = applicationUserRepository.
                findEngineersAndLeadEngineersWithUserInfoByTeamId(teamByByLeaderUsername.getId());

        //for each generate engineer dto
        for (ApplicationUser engineer: engineers) {

            EngineerDto engineerDto = new EngineerDto();

            engineerDto.setId(engineer.getId());
            engineerDto.setFirstName(engineer.getUserInfo().getName());
            engineerDto.setLastName(engineer.getUserInfo().getSurname());
            engineerDto.setPicture(engineer.getUserInfo().getPictureUrl());

            Week week = weekRepository
                    .findByWeekNumberAndYearNumberAndUser(weekNumber, yearNumber, engineer)
                    .orElseGet(() -> engineerService.createWeek(engineer, weekNumber, yearNumber));

            WeekDto weekDtoForEngineer = createWeekDtoForEngineer(engineer, week);

            engineerDto.setWeek(weekDtoForEngineer);

            List<Task> tasksFromBacklogByEngineerId = taskRepository.findTasksFromBacklogByEngineerId(engineer.getId());

            engineerDto.setBacklog(
                    tasksFromBacklogByEngineerId
                            .stream()
                            .map(task -> {
                                //todo plannedat and assigned engineer
                                return taskMapper.toDto(task, LocalDate.now(), "Repela");
                            })
                            .collect(Collectors.toList())
            );

            assignTasksPanelDto.getEngineers().add(engineerDto);
            }

        assignTasksPanelDto.setUnassignedTasks(
                taskRepository
                        .findUnassignedTasksByTeamId(teamByByLeaderUsername.getId())
                        .stream()
                        .map(task -> {
                            //todo plannedat and assigned engineer
                            return taskMapper.toDto(task, LocalDate.now(), "Repela");
                        })
                        .collect(Collectors.toList())
        );


        return assignTasksPanelDto;
        }








    private WeekDto createWeekDtoForEngineer(ApplicationUser engineer, Week week){


        List<Day> daysByWeekId = dayRepository.findAllByWeekId(week.getId());
        List<DayDto> dayDtos = new LinkedList<>();

        for (Day day: daysByWeekId) {
            List<TaskDto> taskDtos = taskRepository.findAllByDayId(day.getId())
                    .stream()
                    .map(task -> {
                        //todo plannedat and assigned engineer
                        return taskMapper.toDto(task, LocalDate.now(), "Repela");
                    })
                    .collect(Collectors.toList());

            DayDto dayDto = dayTasksMapper.toDto(day, taskDtos);
            dayDtos.add(dayDto);
        }

        return weekDayMapper.toDto(week,dayDtos);
    };

    public WeekDto getWeekWithTasksForEngineerByEngineerId(String username, int weekNumber, int yearNumber, long engineerId) {

        ApplicationUser leaderByUsername = applicationUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException());

        ApplicationUser engineer = applicationUserRepository
                .findById(engineerId)
                .orElseThrow(() -> new RuntimeException());

        List<ApplicationUser> engineersFromTeamLeaderTeam = applicationUserRepository
                .findEngineersAndLeadEngineersFromTeamByTeamLeaderUsername(username);

        //todo check if user belongs to team;


        Week week = weekRepository
                .findByWeekNumberAndYearNumberAndUser(weekNumber, yearNumber, engineer)
                .orElseGet(() -> engineerService.createWeek(engineer, weekNumber, yearNumber));

        return createWeekDtoForEngineer(engineer, week);
    }

}