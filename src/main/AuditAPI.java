package main;

import classes.Report;
import classes.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class AuditAPI {

    private static final String api = "https://www.playeraudit.com/api/groups";

    public static Report fetchReport() {
        try {
            return new Report((Server[]) Json.deserialize(getJson(),false, Server[].class)).finalizeReport();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getJson() {
        try {
            return new BufferedReader(new InputStreamReader(new URL(api).openConnection().getInputStream())).readLine();
        } catch(Exception e) {}
        return null;
    }
}
