package login;

import java.io.IOException;

import Database.UserDAO;
import Domain.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Register {
    private static Scene scene;

    public void start (Stage stage) throws IOException {
        stage.setTitle("Register");
        scene = BuildScene(stage);
        stage.setScene(scene);
        stage.show();
    }

    private Scene BuildScene(Stage stage) {
        
        GridPane grid = BuildGrid(stage);
        Scene scene = new Scene(grid, 400, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        return scene;
    }

    private GridPane BuildGrid(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Register");
        grid.add(title,0,0,2,1);

        Text nameLabel = new Text("Name:");
        TextField nameField = new TextField();
        grid.add(nameLabel,0,1);
        grid.add(nameField,1,1);

        Text raLabel = new Text("RA:");
        TextField raField = new TextField();
        grid.add(raLabel,0,2);
        grid.add(raField,1,2);

        Text loginLabel = new Text("Login:");
        TextField loginField = new TextField();
        grid.add(loginLabel,0,3);
        grid.add(loginField,1,3);

        Text passwordLabel = new Text("Password:");
        TextField passwordField = new TextField();
        grid.add(passwordLabel,0,4);
        grid.add(passwordField,1,4);

        HBox horizontalBox = new HBox();
        Button registerButton = new Button("Register");
        horizontalBox.setAlignment(Pos.BOTTOM_RIGHT);
        horizontalBox.getChildren().add(registerButton);
        grid.add(horizontalBox,1,5);

        registerButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent arg0) {
                    User newUser = new User();
                    newUser.name = nameField.getText();
                    newUser.ra = raField.getText();
                    newUser.login = loginField.getText();
                    newUser.password = passwordField.getText();
                    try {
                        UserDAO.Create(newUser);
                        App.setScene(stage);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

        return grid;
    }
}
