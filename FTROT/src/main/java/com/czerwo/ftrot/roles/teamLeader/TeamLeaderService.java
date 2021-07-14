package com.czerwo.ftrot.roles.teamLeader;

import com.czerwo.ftrot.auth.ApplicationUser;
import com.czerwo.ftrot.auth.ApplicationUserRepository;
import com.czerwo.ftrot.models.DataService;
import com.czerwo.ftrot.models.data.Day.Day;
import com.czerwo.ftrot.models.data.Task;
import com.czerwo.ftrot.models.data.Team;
import com.czerwo.ftrot.models.data.Week;
import com.czerwo.ftrot.models.dtos.DayDto;
import com.czerwo.ftrot.models.dtos.TaskDto;
import com.czerwo.ftrot.models.dtos.WeekDto;
import com.czerwo.ftrot.models.dtos.WorkPackageSimplifiedDto;
import com.czerwo.ftrot.models.mappers.TaskMapper;
import com.czerwo.ftrot.models.mappers.WeekDayMapper;
import com.czerwo.ftrot.models.mappers.WorkPackageSimplifiedMapper;
import com.czerwo.ftrot.models.repositories.*;
import com.czerwo.ftrot.models.mappers.DayTasksMapper;
import com.czerwo.ftrot.roles.engineer.EngineerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
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
    private final DataService dataService;
    private final DayTasksMapper dayTasksMapper;
    private final TaskMapper taskMapper;
    private final WeekDayMapper weekDayMapper;


    public TeamLeaderService(ApplicationUserRepository applicationUserRepository, WorkPackageRepository workPackageRepository, WorkPackageSimplifiedMapper workPackageSimplifiedMapper, TeamRepository teamRepository, TaskRepository taskRepository, WeekRepository weekRepository, DayRepository dayRepository, EngineerService engineerService, DataService dataService, DayTasksMapper dayTasksMapper, TaskMapper taskMapper, WeekDayMapper weekDayMapper) {
        this.applicationUserRepository = applicationUserRepository;
        this.workPackageRepository = workPackageRepository;
        this.workPackageSimplifiedMapper = workPackageSimplifiedMapper;
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.engineerService = engineerService;
        this.dataService = dataService;
        this.dayTasksMapper = dayTasksMapper;
        this.taskMapper = taskMapper;
        this.weekDayMapper = weekDayMapper;
    }

    public UserInfoDto getUserInfoByUsername(String username) {

        Optional<ApplicationUser> userByUsername = applicationUserRepository
                .findByUsernameWithTeamAndUserInfo(username);

        return UserInfoMapper.toDto(userByUsername);

    }

    public List<WorkPackageSimplifiedDto> getTopFiveWorkPackagesWithClosestDeadline(String username) {


        Team team = teamRepository.findByTeamLeaderUsername(username).orElseThrow(() -> new RuntimeException());

        Pageable topFive = PageRequest.of(0, 5);

        List<WorkPackageSimplifiedDto> workPackagesDto = workPackageRepository
                .findTop5ByTeamIdOrderByDeadlineAsc(team.getId(), topFive)
                .stream()
                .map(workPackageSimplifiedMapper::toDto)
                .collect(Collectors.toList());

        return workPackagesDto;
    }

    public int getAssignedHoursForCurrentWeek(String username) {

        int currentWeek = dataService.getCurrentWeekNumber();
        int currentYearNumber = dataService.getCurrentYearNumber();

        List<ApplicationUser> engineers = applicationUserRepository
                .findEngineersAndLeadEngineersFromTeamByTeamLeaderUsername(username);

        int assignedHours = engineers
                .stream()
                .map(e -> taskRepository.sumTasksByAssignerEngineerIdAndWeekAndYear(e.getId(), currentWeek, currentYearNumber))
                .collect(Collectors.summingInt(Integer::intValue));


        return assignedHours;

    }

    public AssignTasksPanelDto getDataForAssignTasksPanelForCurrentWeek(String username) {

        int weekNumber = dataService.getCurrentWeekNumber();
        int yearNumber = dataService.getCurrentYearNumber();

        AssignTasksPanelDto assignTasksPanelDto = new AssignTasksPanelDto();

        Team teamByByLeaderUsername = teamRepository.findByTeamLeaderUsername(username).orElseThrow(() -> new RuntimeException());

        List<ApplicationUser> engineers = applicationUserRepository.
                findEngineersAndLeadEngineersWithUserInfoByTeamId(teamByByLeaderUsername.getId());

        //for each generate engineer dto
        for (ApplicationUser engineer : engineers) {

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


    private WeekDto createWeekDtoForEngineer(ApplicationUser engineer, Week week) {


        List<Day> daysByWeekId = dayRepository.findAllByWeekId(week.getId());
        List<DayDto> dayDtos = new LinkedList<>();

        for (Day day : daysByWeekId) {
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

        return weekDayMapper.toDto(week, dayDtos);
    }

    ;

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

    @Transactional
    public void modifyTaskAssignment(String teamLeaderName, AssignTaskDto assignTaskDto, long taskId) {

        ApplicationUser teamLeader = applicationUserRepository
                .findByUsername(teamLeaderName)
                .orElseThrow(() -> new RuntimeException());

        Team team = teamLeader.getTeam();

        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new RuntimeException());

        if (assignTaskDto.isInBacklog() && assignTaskDto.isInUnfinishedTasks()) throw new RuntimeException();


        if (assignTaskDto.isInBacklog()) {

            //todo check if assignedEngineer belongs to TeamLeaderTeam
            ApplicationUser assignedEngineer = applicationUserRepository
                    .findById(assignTaskDto.getEngineerId())
                    .orElseThrow(() -> new RuntimeException());


            task.setDay(null);
            task.setAssignedEngineer(assignedEngineer);
            task.setSorting(assignTaskDto.getSorting());
            replaceSorting();
            taskRepository.save(task);
            return;

        }

        if (assignTaskDto.isInUnfinishedTasks()) {
            task.setDay(null);
            task.setAssignedEngineer(null);
            task.setSorting(assignTaskDto.getSorting());
            replaceSorting();
            taskRepository.save(task);
            return;
        }


        //todo check if there is day in assignTaskDto
        Day day = dayRepository
                .findById(assignTaskDto.getDayId())
                .orElseThrow(() -> new RuntimeException());
        //todo check if day belongs to teamleader team user

        Week week = weekRepository
                .findWeekByDayId(day.getId())
                .orElseThrow(() -> new RuntimeException());

        ApplicationUser assignedEngineer = applicationUserRepository
                .findById(assignTaskDto.getEngineerId())
                .orElseThrow(() -> new RuntimeException());

        if (!week.getUser().equals(assignedEngineer)) throw new RuntimeException();

        task.setAssignedEngineer(assignedEngineer);
        task.setDay(day);
        task.setSorting(assignTaskDto.getSorting());
        replaceSorting();
        taskRepository.save(task);


    }


    private void replaceSorting() {
        //todo

    }




}