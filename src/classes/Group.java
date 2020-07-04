package classes;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Group {

    private String Comment;
    private String QuestName;
    private String AdventureType;
    private String Difficulty;
    private String[] AcceptedClasses;
    private int AcceptedCount;
    private int MinimumLevel;
    private int MaximumLevel;
    private int AdventureActive;
    private Player Leader;
    private Player[] Members;

    private String server;

    public String getComment() {
        return Comment;
    }

    public String getQuestName() {
        return QuestName;
    }

    public String getAdventureType() {
        return AdventureType;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public String[] getAcceptedClasses() {
        return AcceptedClasses;
    }

    public int getAcceptedCount() {
        return AcceptedCount;
    }

    public int getMinimumLevel() {
        return MinimumLevel;
    }

    public int getMaximumLevel() {
        return MaximumLevel;
    }

    public int getAdventureActive() {
        return AdventureActive;
    }

    public Player getLeader() {
        return Leader;
    }

    public Player[] getMembers() {
        return Members;
    }

    public String getLevelRange() {
        return getMinimumLevel() + " - "  + getMaximumLevel();
    }

    public String getQuestDetails() {
        if(QuestName == null) {
            return "";
        } else {
            return QuestName + " " + Difficulty;
        }
    }


    public Player[] getAllMembers() {
        Player[] ret = new Player[Members.length + 1];
        ret[0] = Leader;
        for(int i = 0; i < Members.length; i++) {
            ret[i+1] = Members[i];
        }
        return ret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getMembersToString() {
        StringBuilder builder = new StringBuilder();
        for(Player player : getAllMembers()) {
            builder.append(player.toString() + "\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static TableView<Group> groupTable() {
        TableView<Group> table = new TableView<>();

        TableColumn<Group,String> cLevelRange = new TableColumn<>("Level Range");
        cLevelRange.setCellValueFactory(new PropertyValueFactory<Group,String>("LevelRange"));

        TableColumn<Group,String> cQuestDetails = new TableColumn<>("Quest");
        cQuestDetails.setCellValueFactory(new PropertyValueFactory<Group,String>("QuestDetails"));

        TableColumn<Group,String> cComment = new TableColumn<>("Comment");
        cComment.setCellValueFactory(new PropertyValueFactory<Group,String>("Comment"));

        TableColumn<Group,String> cMembers = new TableColumn<>("Members");
        cMembers.setCellValueFactory(new PropertyValueFactory<Group,String>("MembersToString"));


        for(TableColumn col : new TableColumn[] {cLevelRange,cQuestDetails,cComment,cMembers}) {
            col.setEditable(false);
            table.getColumns().add(col);
        }

        return table;
    }


}
