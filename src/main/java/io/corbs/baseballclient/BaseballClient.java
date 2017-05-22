package io.corbs.baseballclient;

import net.cworks.http.Http;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class BaseballClient {

    @Value("${api.root}")
    private String apiRoot;

    private static Set<String> players = new HashSet<>();

    private Random randomizer = new Random();

    /**
     * Call the player REST API
     */
    @Scheduled(fixedDelay=5000)
    public void callPlayerApi() {

        try {
            if(players.size() > 0) {
                String response = Http.get(apiRoot + "/players/player/" + nextPlayerId()).asString();
                System.out.println(new Date() + ": " + response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add a player into this client's memory
     * @param playerId
     */
    @RequestMapping(method = RequestMethod.POST, path="/players/{playerId}")
    void addPlayer(@PathVariable String playerId) {
        if(!StringUtils.isEmpty(playerId)) {
            players.add(playerId);
        }
    }

    /**
     * Remove a player from this client's memory
     * @param playerId
     */
    @RequestMapping(method = RequestMethod.DELETE, path="/players/{playerId}")
    void removePlayer(@PathVariable String playerId) {
        if(!StringUtils.isEmpty(playerId)) {
            players.remove(playerId);
        }
    }

    /**
     * Return a random playerId
     * @return
     */
    private String nextPlayerId() {
        int i = randomizer.nextInt(players.size());
        return String.valueOf(players.toArray()[i]);
    }

}
