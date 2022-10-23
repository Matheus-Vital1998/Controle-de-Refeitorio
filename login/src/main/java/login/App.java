package login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import Domain.*;
import Database.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static final String title = "Authenticate";

    public static void setScene(Stage stage) throws Exception {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Welcome");
        title.setId("title");
        grid.add(title, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);
        TextField passwordTextField = new TextField();
        grid.add(passwordTextField, 1, 2);
        
        HBox buttonBox = new HBox();
        
        Button button = new Button("Sign in");
        button.setId("signIn");        
        
        Button button2 = new Button("Register");
        button.setId("register");

        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.getChildren().add(button);
        buttonBox.getChildren().add(button2);
        grid.add(buttonBox, 1, 4);
        
        Text actionTarget = new Text();
        actionTarget.setId("actionTarget");
        grid.add(actionTarget, 1, 6);
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    if (UserDAO.Authenticate(usernameTextField.getText(), passwordTextField.getText())) {
                        actionTarget.setText("User found!");
                    } else {
                        actionTarget.setText("User not found!");
                    }                    
                } catch (Exception e) {
                    // TODO: handle exception
                }
            } 
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    new Register().start(stage);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });

        scene = new Scene(grid, 400, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}