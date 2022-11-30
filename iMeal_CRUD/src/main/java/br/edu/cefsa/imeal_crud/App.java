package br.edu.cefsa.imeal_crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage stageIni;

    @Override
    public void start(Stage stage) throws IOException {
        stageIni = stage;
        scene = new Scene(loadFXML("ViewLogin"), 640, 480);
        stage.getIcons().add(new Image("/imgs/iMeal_Logo_Oficial_2.png"));
        stage.setTitle("iMeal");
        stage.setWidth(804);
        stage.setHeight(540);
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
