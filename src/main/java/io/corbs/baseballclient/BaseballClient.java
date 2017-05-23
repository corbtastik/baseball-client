package io.corbs.baseballclient;

import cworks.json.Json;
import cworks.json.JsonArray;
import cworks.json.JsonObject;
import net.cworks.http.Http;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

@RestController
public class BaseballClient {

    @Value("${api.root}")
    private String apiRoot;

    private Random randomizer = new Random();

    /**
     * Call the player REST API
     */
    public void callPlayerApi() {

        try {
            String playerId = nextPlayer();
            if(playerId == null) {
                return;
            }
            String response = Http.get(apiRoot + "/players/player/" + nextPlayer()).asString();
            System.out.println(new Date() + ": " + response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Call the player REST API
     */
    @Scheduled(fixedDelay=5000)
    public void callRandomPlayerAPI() {

        try {
            String response = Http.get(apiRoot + "/players/players/random").asString();
            System.out.println(new Date() + ": " + response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String nextPlayer() throws IOException {

        String response = Http.get(apiRoot + "/players/players").asString();
        JsonObject responseObject = Json.asObject(response);

        JsonArray players = responseObject.getArray("data");
        if(players == null) {
            return null;
        }

        JsonObject player = responseObject.getArray("data").get(
            randomizer.nextInt(responseObject.getArray("data").size()));

        return player.getString("id");
    }

}
