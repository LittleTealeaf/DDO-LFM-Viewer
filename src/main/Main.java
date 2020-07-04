package main;


import classes.Group;
import classes.Player;
import classes.Report;
import classes.Server;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static Stage stage;



    public static void main(String[] args) {
        Json.load();

        launch(args);
    }

    /**
     * Creation of the main stage
     *
     * @param stage Stage created and passed through
     */
    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        stage.setMaximized(true);

        TabPane tabs = new TabPane();

        for(Server server : AuditAPI.fetchReport().getServers()) {
            Tab tab = new Tab(server.getName());

            TableView<Group> lfms = Group.groupTable();
            lfms.getItems().addAll(server.getGroups());
            tab.setContent(lfms);
            tabs.getTabs().add(tab);
        }


        Scene scene = new Scene(tabs);

        stage.setScene(scene);
        stage.show();
    }



}
