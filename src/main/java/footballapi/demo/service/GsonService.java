package footballapi.demo.service;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

@Service
public class GsonService {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    public String downloadJsonFromURL(String urlText) {
        try {
            URL myUrl = new URL(urlText);

            StringBuilder jsonText = new StringBuilder();

            InputStream inputStream = myUrl.openStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                jsonText.append(scanner.nextLine());
            }
            return jsonText.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
