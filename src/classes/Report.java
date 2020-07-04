package classes;

import java.time.Instant;
import java.util.Date;

public class Report {

    private Server[] servers;
    private Date reportTime;

    public Report(Server[] servers) {
        this.servers = servers;
    }

    public Server[] getServers() {
        return servers;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public Report finalizeReport() {
        reportTime = Date.from(Instant.now());

        for(Server server : servers) {

            if(reportTime.after(server.getLastUpdateTime())) {
                reportTime = server.getLastUpdateTime();
            }

            for(Group group : server.getGroups()) {
                group.setServer(server.getName());
                for(Player player : group.getAllMembers()) {
                    player.setServer(server.getName());
                }
            }
        }
        return this;
    }



}
