package main;


import classes.Group;
import classes.Player;
import classes.Report;
import classes.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

public class Main extends Application {

    public static Stage stage;

    public static String[] launchArgs;

    public static void main(String[] args) throws AWTException {

        Platform.setImplicitExit(false);

        launchArgs = args;
        Json.load();

        trayIcon();
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

        stage.setOnCloseRequest(event -> {
            SystemTray.getSystemTray().remove(trayIcon);
        });

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

    private static int stateWindow = 1;
    private static TrayIcon trayIcon;

    private static void trayIcon() {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();

        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        trayIcon = new TrayIcon(image);

        final SystemTray tray = SystemTray.getSystemTray();

//        // Create a pop-up menu components
//        MenuItem aboutItem = new MenuItem("About");
//        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
//        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
//        Menu displayMenu = new Menu("Display");
//        MenuItem errorItem = new MenuItem("Error");
//        MenuItem warningItem = new MenuItem("Warning");
//        MenuItem infoItem = new MenuItem("Info");
//        MenuItem noneItem = new MenuItem("None");
//        MenuItem exitItem = new MenuItem("Exit");
//
//        //Add components to pop-up menu
//        popup.add(aboutItem);
//        popup.addSeparator();
//        popup.add(cb1);
//        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
//        popup.add(exitItem);

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (stateWindow == 1) {
                                stage.hide();
                                stateWindow = 0;
                            } else if (stateWindow == 0) {
                                stage.show();
                                stateWindow = 1;
                            }
                        }
                    });
                }

            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

}
