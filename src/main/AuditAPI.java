package main;

import classes.Report;
import classes.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;
import java.util.Date;

/**
 * API integration with the <a href="https://www.playeraudit.com/">www.playeraudit.com</a> site, used with permission
 * @author Tealeaf
 * @see <a href="https://www.playeraudit.com/api/">www.playeraudit.com/api</a>
 */
public class AuditAPI {

    private static final String api = "https://www.playeraudit.com/api/groups";

    private static String JSON;
    private static Date lastRequest;

    public static Report fetchReport() {
        try {
            return new Report((Server[]) Json.deserialize(getJson(),false, Server[].class)).finalizeReport();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getJson() {
        if(lastRequest == null || lastRequest.before(Date.from(Instant.now().minusSeconds(15)))) {
            try {
                lastRequest = Date.from(Instant.now());
                System.out.println("Requested JSON at " + lastRequest.toString());
                return JSON = new BufferedReader(new InputStreamReader(new URL(api).openConnection().getInputStream())).readLine();
            } catch(Exception e) {
                return JSON;
            }
        } else {
            return JSON;
        }


    }
}
