package footballapi.demo.service;

import com.google.gson.Gson;
import footballapi.demo.dao.TeamRepository;
import footballapi.demo.model.event.Event;
import footballapi.demo.model.event.EventsArray;
import footballapi.demo.model.team.FakeTeamDto;
import footballapi.demo.model.team.Team;
import footballapi.demo.model.team.TeamDto;
import footballapi.demo.model.team.TeamsArray;
import footballapi.demo.provider.UrlAddressProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final UrlAddressProvider addressProvider;
    private final TeamRepository teamRepository;
    private final GsonService gsonService;
    private final Gson gson;

    @Autowired
    public TeamService(UrlAddressProvider addressProvider,
                       @Qualifier("teamRepository") TeamRepository teamRepository,
                       GsonService gsonService,
                       Gson gson) {
        this.addressProvider = addressProvider;
        this.teamRepository = teamRepository;
        this.gsonService = gsonService;
        this.gson = gson;
    }

    public void save(Team team, String username) {
        if (isAlreadyTeamSaved(team.idTeam, username) == 0) {
            TeamDto teamDto = new TeamDto(team.idTeam, team.strTeam, username);
            teamRepository.save(teamDto);
        }
    }

    public int isAlreadyTeamSaved(Long idTeam, String username) {
        return teamRepository.isAlreadyTeamSaved(idTeam, username);
    }

    public void deleteTeamByAutoId(Long teamId, String username) {
        teamRepository.deleteByTeamId(teamId, username);
    }

    public Team[] selectTeamsInfo(String teamName) {
        String s = gsonService.downloadJsonFromURL(addressProvider.getTeamInfoUrl() + teamName);
        TeamsArray teamsArray = gson.fromJson(s, TeamsArray.class);
        return teamsArray.teams;
    }

    public Team selectTeamInfoById(Long idTeam) {
        String s = gsonService.downloadJsonFromURL(addressProvider.getTeamInfoById() + idTeam);
        TeamsArray teamsArray = gson.fromJson(s, TeamsArray.class);
        return teamsArray.teams[0];
    }


    public Event[] selectEventsInfoByTeamId(Long idTeam) {
        String s = gsonService.downloadJsonFromURL(addressProvider.getEventInfoUrl() + idTeam);
        EventsArray eventsArray = gson.fromJson(s, EventsArray.class);
        return eventsArray.events;
    }

    public List<Event> selectAllEvents(String username) {
        List<Long> teamsIds = teamRepository.selectAllTeamsIds(username);
        return teamsIds.stream()
                .map(idTeam -> gson.fromJson(gsonService.downloadJsonFromURL(
                        addressProvider.getEventInfoUrl() + idTeam),
                        EventsArray.class).events[0]).collect(Collectors.toList());
    }

    public List<FakeTeamDto> selectUserSavedTeams(String username) {
        List<Long> teamsIds = teamRepository.selectAllTeamsIds(username);
        List<FakeTeamDto> fakeTeamDtos = new ArrayList<>();
        for (Long id : teamsIds) {
            Team team = selectTeamInfoById(id);
            FakeTeamDto fakeTeamDto = new FakeTeamDto(team.idTeam, team.strTeam);
            fakeTeamDtos.add(fakeTeamDto);
        }
        return fakeTeamDtos;
    }

    public String selectReadableEvents(String username) {
        List<Event> events = selectAllEvents(username);

        StringBuilder sb = new StringBuilder();
        for (Event event : events) {
            sb.append("# ").append(event.strEvent).append("\n");
            sb.append(event.strLeague).append("\n");
            sb.append(event.dateEvent).append("\n");
            sb.append(event.strTime).append("\n");
            sb.append(event.strVenue).append("\n");
            sb.append(event.strCountry).append("\n");
            sb.append(event.strStatus).append("\n");
        }
        return sb.toString();
    }


}
