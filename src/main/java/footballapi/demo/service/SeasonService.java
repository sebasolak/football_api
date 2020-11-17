package footballapi.demo.service;

import com.google.gson.Gson;
import footballapi.demo.model.season.SeasonArray;
import footballapi.demo.provider.UrlAddressProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonService {

    private final Gson gson;
    private final GsonService gsonService;
    private final UrlAddressProvider addressProvider;

    @Autowired
    public SeasonService(Gson gson,
                         GsonService gsonService,
                         UrlAddressProvider addressProvider) {
        this.gson = gson;
        this.gsonService = gsonService;
        this.addressProvider = addressProvider;
    }

    public String getCurrentSeason(Long idTeam) {
        String s = gsonService.downloadJsonFromURL(addressProvider.getAllSeasonById() + idTeam);
        SeasonArray seasonArray = gson.fromJson(s, SeasonArray.class);
        int length = seasonArray.seasons.length;
        return seasonArray.seasons[length - 1].strSeason;
    }
}
