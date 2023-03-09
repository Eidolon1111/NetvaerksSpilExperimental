package NetvaerksSpil;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LobbyWindow extends Application {

    private ArrayList<Player> players = new ArrayList<>();
    private Label lbPlayers = new Label("Players: ");
    private ListView<Player> lvPlayers = new ListView<>();
    private Button btnReady = new Button("Ready");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Game Lobby");
        GridPane gridPane = new GridPane();
        init(gridPane);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    private void init(GridPane pane){
        pane.setMinSize(400, 200);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(0, 10, 0, 10));

        pane.add(lvPlayers, 0, 0);
        lvPlayers.getItems().setAll(players);
        pane.add(btnReady, 0, 1);
    }

    private void update(){

    }

    private void readyAction(){

    }
}
