package classes;


import java.time.LocalTime;
import java.util.Date;

public class Server {

    private String Name;
    private Date LastUpdateTime;
    private int GroupCount;
    private Group[] Groups;


    public String getName() {
        return Name;
    }

    public Date getLastUpdateTime() {
        return LastUpdateTime;
    }

    public int getGroupCount() {
        return GroupCount;
    }

    public Group[] getGroups() {
        return Groups;
    }

    public Player[] getPlayers() {
        int playerCount = 0;
        for(Group group : Groups) {
            playerCount += group.getAllMembers().length;
        }
        Player[] ret = new Player[playerCount];
        int i = 0;
        for(Group group : Groups) {
            for(Player player : group.getAllMembers()) {
                ret[i++] = player;
            }
        }
        return ret;
    }
}
