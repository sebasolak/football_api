package footballapi.demo.service;

import com.google.gson.Gson;
import footballapi.demo.model.league.League;
import footballapi.demo.model.league.LeagueArray;
import footballapi.demo.provider.UrlAddressProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LeagueService {

    private final UrlAddressProvider providerService;
    private final GsonService gsonService;
    private final Gson gson;

    @Autowired
    public LeagueService(UrlAddressProvider providerService,
                         GsonService gsonService,
                         Gson gson) {
        this.providerService = providerService;
        this.gsonService = gsonService;
        this.gson = gson;
    }

    public League[] selectAllLeagues() {
        String s = gsonService.downloadJsonFromURL(providerService.getAllLeaguesUrl());
        LeagueArray leagueArray = gson.fromJson(s, LeagueArray.class);
        League[] leagues = Arrays.stream(leagueArray.leagues)
                .filter(league -> "Soccer".equalsIgnoreCase(league.strSport))
                .toArray(League[]::new);
        return leagues;
    }
}
