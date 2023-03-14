package NetvaerksSpil;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class LobbyWindow extends Stage {

    private Label lbPlayers = new Label("Players: ");
    private ListView<String> lvPlayers = new ListView<>();
    private Button btnReady = new Button("Ready");

    public LobbyWindow() throws Exception {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        GridPane gridPane = new GridPane();
        init(gridPane);
        setTitle("Game Lobby");

        Scene scene = new Scene(gridPane);
        setScene(scene);
    }

    private void init(GridPane pane) throws Exception {
        JoinGameWindow joinGameWindow = new JoinGameWindow();
        joinGameWindow.showAndWait();

        pane.setMinSize(400, 200);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(0, 10, 0, 10));

        pane.add(lbPlayers, 0, 0);

        pane.add(lvPlayers, 0, 1);
        update();
        pane.add(btnReady, 0, 2);
        btnReady.setOnAction(event -> readyAction());

    }

    private void update(){
        lvPlayers.getItems().clear();
        GUI.players.forEach(p -> lvPlayers.getItems().add(p.getLobbyString()));
    }

    private void readyAction(){
        GUI.me.setReady(true);
        GUI.outString = "ready " + GUI.me.getName();
        btnReady.setDisable(true);
        update();
    }
}
