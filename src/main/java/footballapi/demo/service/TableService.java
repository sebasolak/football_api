package footballapi.demo.service;

import com.google.gson.Gson;
import footballapi.demo.model.table.Table;
import footballapi.demo.model.table.TableArray;
import footballapi.demo.provider.UrlAddressProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    private final UrlAddressProvider addressProvider;
    private final GsonService gsonService;
    private final Gson gson;
    private final SeasonService seasonService;

    @Autowired
    public TableService(UrlAddressProvider addressProvider,
                        GsonService gsonService,
                        Gson gson,
                        SeasonService seasonService) {
        this.addressProvider = addressProvider;
        this.gsonService = gsonService;
        this.gson = gson;
        this.seasonService = seasonService;
    }

    public Table[] selectTable(Long idLeague) {
        String s = gsonService.downloadJsonFromURL(addressProvider.getCurrentTableById(idLeague,
                seasonService.getCurrentSeason(idLeague)));
        TableArray tableArray = gson.fromJson(s, TableArray.class);
        return tableArray.table;
    }
}
