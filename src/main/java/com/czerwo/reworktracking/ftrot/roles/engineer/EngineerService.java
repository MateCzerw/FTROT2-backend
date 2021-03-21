package com.czerwo.reworktracking.ftrot.roles.engineer;

import com.czerwo.reworktracking.ftrot.auth.ApplicationUser;
import com.czerwo.reworktracking.ftrot.auth.ApplicationUserRepository;
import com.czerwo.reworktracking.ftrot.models.DataService;
import com.czerwo.reworktracking.ftrot.models.data.Day.Day;
import com.czerwo.reworktracking.ftrot.models.data.Day.DayName;
import com.czerwo.reworktracking.ftrot.models.data.Task;
import com.czerwo.reworktracking.ftrot.models.data.Week;
import com.czerwo.reworktracking.ftrot.models.data.WorkPackage;
import com.czerwo.reworktracking.ftrot.models.dtos.DayDto;
import com.czerwo.reworktracking.ftrot.models.dtos.TaskDto;
import com.czerwo.reworktracking.ftrot.models.dtos.WeekDto;
import com.czerwo.reworktracking.ftrot.models.exceptions.Date.WrongDateException;
import com.czerwo.reworktracking.ftrot.models.exceptions.Day.DayNotFoundException;
import com.czerwo.reworktracking.ftrot.models.exceptions.Task.TaskNotFoundException;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.TeamLeaderNotFoundException;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.UserIsNotTaskOwnerException;
import com.czerwo.reworktracking.ftrot.models.exceptions.User.UserNotFoundException;
import com.czerwo.reworktracking.ftrot.models.exceptions.Week.DuplicateWeekExceptionException;
import com.czerwo.reworktracking.ftrot.models.exceptions.WorkPackage.WorkPackageNotFoundException;
import com.czerwo.reworktracking.ftrot.models.mappers.DayTasksMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.TaskMapper;
import com.czerwo.reworktracking.ftrot.models.mappers.WeekDayMapper;
import com.czerwo.reworktracking.ftrot.models.repositories.DayRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.TaskRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WeekRepository;
import com.czerwo.reworktracking.ftrot.models.repositories.WorkPackageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EngineerService {

    private final DataService dataService;
    private final ApplicationUserRepository applicationUserRepository;
    private final TaskRepository taskRepository;
    private final WorkPackageRepository workPackageRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TaskMapper taskMapper;
    private final DayTasksMapper dayTasksMapper;
    private final WeekDayMapper weekDayMapper;


    public EngineerService(DataService dataService, ApplicationUserRepository applicationUserRepository,
                           TaskRepository taskRepository,
                           WorkPackageRepository workPackageRepository, WeekRepository weekRepository,
                           DayRepository dayRepository,
                           TaskMapper taskMapper, DayTasksMapper dayTasksMapper, WeekDayMapper weekDayMapper) {
        this.dataService = dataService;
        this.applicationUserRepository = applicationUserRepository;
        this.taskRepository = taskRepository;
        this.workPackageRepository = workPackageRepository;
        this.weekRepository = weekRepository;
        this.dayRepository = dayRepository;
        this.taskMapper = taskMapper;
        this.dayTasksMapper = dayTasksMapper;
        this.weekDayMapper = weekDayMapper;
    }

    public UserInfoDto getUserInfoByUsername(String username) {

        Optional<ApplicationUser> userWithTeamAndUserInfoByUsername = applicationUserRepository
                .findByUsernameWithTeamAndUserInfo(username);

        Optional<ApplicationUser> teamLeader = applicationUserRepository
                .findTeamLeaderWithUserInfoByTeam(
                        userWithTeamAndUserInfoByUsername
                        .map(ApplicationUser::getTeam)
                        .orElseThrow(() -> new TeamLeaderNotFoundException()));

        int unFinishedTasks = taskRepository
                .countTaskByAssignedEngineerIdAndStatusIsNotStatusFinished(
                        userWithTeamAndUserInfoByUsername.map(ApplicationUser::getId)
                                .orElseGet(() -> 0L));



        return UserInfoMapper.toDto(userWithTeamAndUserInfoByUsername, teamLeader, 0, unFinishedTasks);

    }

    public List<TaskSimplifyDto> getTasksForDay(String username) {

        LocalDate date = dataService.getClosestWorkingDay();

        ApplicationUser userByUsername = applicationUserRepository
                .findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(username));

        Day dayByDate = dayRepository.findByEngineerAndDate(userByUsername, date)
                .orElseThrow( () -> new DayNotFoundException());

        List<Task> AssignedTasksForDay = taskRepository
                .findAllByAssignedEngineerIdAndDay(
                        userByUsername.getId(),dayByDate);


        return AssignedTasksForDay
                .stream()
                .map(TaskSimplifyMapper::toDto)
                .collect(Collectors.toList());

    }


    public int getTotalDurationOfAssignedTasksInCurrentWeek(String username){

        int weekNumber = dataService.getCurrentWeekNumber();
        int yearNumber = dataService.getCurrentYearNumber();

       ApplicationUser userByUsername = applicationUserRepository
                .findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        weekRepository
                .findByWeekNumberAndYearNumberAndUser(weekNumber, yearNumber, userByUsername)
                .orElseGet(() -> createWeek(userByUsername, weekNumber, yearNumber));

        return taskRepository.sumTasksByAssignerEngineerIdAndWeekAndYear(userByUsername.getId(), weekNumber, yearNumber);
    }



    public WeekDto getCurrentUserWeekWithTasks(String username){

        int weekNumber = dataService.getCurrentWeekNumber();
        int yearNumber = dataService.getCurrentYearNumber();

        return getUserWeekWithTasks(username, weekNumber, yearNumber);

    }


    public WeekDto getUserWeekWithTasks(String username, int weekNumber, int yearNumber){

        ApplicationUser userByUsername = applicationUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Week week = weekRepository
                .findByWeekNumberAndYearNumberAndUser(weekNumber, yearNumber, userByUsername)
                .orElseGet(() -> createWeek(userByUsername, weekNumber, yearNumber));

        List<Day> daysByWeekId = dayRepository.findAllByWeekId(week.getId());

        List<DayDto> dayDtos = new LinkedList<>();

        for (Day day: daysByWeekId) {
            List<TaskDto> taskDtos = taskRepository.findAllByDayId(day.getId())
                    .stream()
                    .map(task -> {
                        //todo plannedat and assigned engineer
                        return taskMapper.toDto(task, null, null);
                    })
                    .collect(Collectors.toList());

            DayDto dayDto = dayTasksMapper.toDto(day, taskDtos);
            dayDtos.add(dayDto);
        }

        return weekDayMapper.toDto(week,dayDtos);
    }


    @Transactional
    public Week createWeek(ApplicationUser user, int weekNumber, int yearNumber) {

    //todo check if week exists and days exists


        weekRepository
                .findByWeekNumberAndYearNumberAndUser(weekNumber,yearNumber,user)
                .ifPresent(item ->{
                    throw new DuplicateWeekExceptionException();
                });


        Week week = new Week();
        week.setUser(user);
        week.setWeekNumber(weekNumber);
        week.setYearNumber(yearNumber);


        for (DayName dayName : DayName.values()) {
            Day day = new Day();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, yearNumber);
            cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
            cal.set(Calendar.DAY_OF_WEEK, DayName.getCalendarDay(dayName));
            day.setDate(LocalDate.parse(sdf.format(cal.getTime())));
            day.setDayName(dayName);
            day.setWeek(week);

            week.addDayToWeek(day);

        }

        return weekRepository.save(week);

    }

    public WeekDto getNextUserWeekWithTasks(String username, int initialWeekNumber, int initialYearNumber) {

        if(dataService.getWeeksInWeekYear(initialYearNumber) < initialWeekNumber) throw new WrongDateException();

        int nextWeekNumber = dataService.getNextWeekNumber(initialWeekNumber, initialYearNumber);
        int nextWeekYear = dataService.getNextWeekYear(initialWeekNumber, initialYearNumber);

        return getUserWeekWithTasks(username, nextWeekNumber, nextWeekYear);

    }

    public WeekDto getPreviousUserWeekWithTasks(String username, int initialWeekNumber, int initialYearNumber) {

        if(initialWeekNumber < 1) throw new WrongDateException();

        int previousWeekNumber = dataService.getPreviousWeekNumber(initialWeekNumber, initialYearNumber);
        int previousWeekYear = dataService.getPreviousWeekYear(initialWeekNumber, initialYearNumber);

        return getUserWeekWithTasks(username, previousWeekNumber, previousWeekYear);

    }

    @Transactional
    public void editTaskStatus(String username, double status, long taskId) {
        ApplicationUser userByUsername = applicationUserRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

        if(!task.getAssignedEngineer().equals(userByUsername)) throw new UserIsNotTaskOwnerException();

        task.setStatus(status);
        taskRepository.save(task);


        WorkPackage workPackage = workPackageRepository
                .workPackageByTaskId(taskId)
                //todo wrong exception
                .orElseThrow(() -> new WorkPackageNotFoundException(0l));

        List<Task> tasksForStatus =
        taskRepository.findAllByWorkPackageId(workPackage.getId());

        workPackage.setStatus(Math.round(recalculateWorkPackageStatus(tasksForStatus) * 100.0 )/ 100.0);

    }


    public double recalculateWorkPackageStatus(List<Task> tasks) {

        double totalDuration = tasks
                .stream()
                .map(Task::getDuration)
                .collect(Collectors.summingInt(value -> value.intValue()));
        int totalWorkDone = tasks
                .stream()
                .map(task -> task.getStatus() * task.getDuration())
                .collect(Collectors.summingInt(value -> value.intValue()));


        return totalDuration != 0 ? totalWorkDone / totalDuration : 0;
    }
}
