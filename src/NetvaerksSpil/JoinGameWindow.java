package NetvaerksSpil;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.DataOutputStream;
import java.util.ArrayList;

public class JoinGameWindow extends Stage {

    private Label lbName = new Label("Player name: ");
    private TextField txfName = new TextField();
    private Button btnJoin = new Button("Join Game");
    private DataOutputStream outToServer;
    public JoinGameWindow(DataOutputStream outToServer) throws Exception {
        this.outToServer = outToServer;
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        GridPane gridPane = new GridPane();
        init(gridPane);
        setTitle("Join Game");

        Scene scene = new Scene(gridPane);
        setScene(scene);

    }

    private void init(GridPane pane){
        pane.setMinSize(200, 100);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(0, 10, 0, 10));
        pane.add(lbName, 0, 0);
        pane.add(txfName, 1, 0);
        pane.add(btnJoin, 0, 1, 2,1);
        btnJoin.setOnAction(event -> {
            try {
                joinGameAction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void joinGameAction() throws Exception {
        String name = txfName.getText();
        boolean taken = false;
        for (Player p : GUI.players) {
            if (p.getName().equals(name)) {
                taken = true;
            }
        }
        if(taken){
            new Alert(Alert.AlertType.WARNING, "Name taken!");
        } else {
            GUI.me = new Player(name, 9, 4, "up");
            GUI.players.add(GUI.me);
            outToServer.writeBytes("join " + name + " " + GUI.me.xpos + " " + GUI.me.ypos + " " + GUI.me.direction);
            this.close();
        }
    }
}
