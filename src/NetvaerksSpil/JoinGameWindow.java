package NetvaerksSpil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JoinGameWindow extends Stage {

    private Label lbName = new Label("Player name: ");
    private TextField txfName = new TextField();
    private Button btnJoin = new Button("Join Game");

    public JoinGameWindow() throws Exception {
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
        btnJoin.setOnAction(event -> joinGameAction());

    }

    private void joinGameAction(){
        String name = txfName.getText();

    }
}
