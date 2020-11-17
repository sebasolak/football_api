package footballapi.demo.controller;

import footballapi.demo.model.event.Event;
import footballapi.demo.model.league.League;
import footballapi.demo.model.table.Table;
import footballapi.demo.model.team.FakeTeamDto;
import footballapi.demo.model.team.Team;
import footballapi.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("team")
public class AppController {

    private final TeamService teamService;
    private final SecurityContextHolderService contextHolderService;
    private final MailService mailService;
    private final UserService userService;
    private final TableService tableService;
    private final LeagueService leagueService;

    @Autowired
    public AppController(TeamService teamService,
                         SecurityContextHolderService contextHolderService,
                         MailService mailService,
                         UserService userService,
                         TableService tableService,
                         LeagueService leagueService) {
        this.teamService = teamService;
        this.contextHolderService = contextHolderService;
        this.mailService = mailService;
        this.userService = userService;
        this.tableService = tableService;
        this.leagueService = leagueService;
    }

    @GetMapping(path = "name/{teamName}")
    public Team[] getTeamsInfo(@PathVariable String teamName) {
        return teamService.selectTeamsInfo(teamName);
    }

    @GetMapping(path = "id/{idTeam}")
    public Team getTeamById(@PathVariable Long idTeam) {
        return teamService.selectTeamInfoById(idTeam);
    }

    @PostMapping(path = "id/{idTeam}")
    public Team saveTeam(@PathVariable Long idTeam) {
        Team team = teamService.selectTeamInfoById(idTeam);
        teamService.save(team, contextHolderService.getUserUsername());
        return team;
    }

    @GetMapping(path = "event/{idTeam}")
    public Event[] getEventsInfoByTeamId(@PathVariable Long idTeam) {
        return teamService.selectEventsInfoByTeamId(idTeam);
    }

    @DeleteMapping(path = "id/{teamId}")
    public void deleteTeamById(@PathVariable Long teamId) {
        teamService.deleteTeamByAutoId(teamId, contextHolderService.getUserUsername());
    }

    @GetMapping(path = "my/next")
    public List<Event> getAllEvents() {
        return teamService.selectAllEvents(contextHolderService.getUserUsername());
    }

    @GetMapping("my/send")
    public String sendMail() throws MessagingException {
        String username = contextHolderService.getUserUsername();
        mailService.sendMail(userService.selectEmailByLogin(username),
                "Closest events",
                teamService.selectReadableEvents(username), true);
        return teamService.selectReadableEvents(username);
    }

    @GetMapping(path = "table/{idLeague}")
    public Table[] getTable(@PathVariable Long idLeague) {
        return tableService.selectTable(idLeague);
    }

    @GetMapping("table/lig")
    public League[] getAllLeagues() {
        return leagueService.selectAllLeagues();
    }

    @GetMapping("my/saved")
    public List<FakeTeamDto> getUserSavedTeams() {
        return teamService.selectUserSavedTeams(contextHolderService.getUserUsername());
    }
}
