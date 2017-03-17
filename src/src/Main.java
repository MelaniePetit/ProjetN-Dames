package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private Scene scene;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Parent root;



    @Override
    public void start(Stage primaryStage){
        Scene scene = start();
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(scene);
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Projet Dames - Optimisation");
        this.primaryStage.getIcons().setAll(new Image(getClass().getResource("../ressource/icon_32.png").toExternalForm()),
                new Image(getClass().getResource("../ressource/icon_126.png").toExternalForm()) );

        this.primaryStage.show();

    }

    //initialise la fenetre
    public Scene start() {
        Scene scene = null;
        try{
            final URL url = getClass().getResource("view/Start.fxml");
            final FXMLLoader loader = new FXMLLoader(url);

            root = (BorderPane) loader.load();
            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public Parent getRoot(){
        return root;
    }

    public void setRoot(Parent parent){
        root = parent;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
