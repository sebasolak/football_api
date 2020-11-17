package footballapi.demo.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlAddressProvider {

    @Value("${team.info.url}")
    private String teamInfoUrl;
    @Value("${event.info.url}")
    private String eventInfoUrl;
    @Value("${team.id.url}")
    private String teamInfoById;
    @Value("${all.leagues.url}")
    private String allLeaguesUrl;
    @Value("${table.id.url}")
    private String currentTableById;
    @Value("${season.id.url}")
    private String allSeasonsById;


    public String getTeamInfoUrl() {
        return teamInfoUrl;
    }

    public String getEventInfoUrl() {
        return eventInfoUrl;
    }

    public String getTeamInfoById() {
        return teamInfoById;
    }

    public String getAllLeaguesUrl() {
        return allLeaguesUrl;
    }

    public String getCurrentTableById(Long idLeague, String season) {
        return currentTableById + idLeague + "&s=" + season;
    }

    public String getAllSeasonById() {
        return allSeasonsById;
    }


}
