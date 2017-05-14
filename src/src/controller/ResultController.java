package src.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.Main;

import javax.swing.text.TabExpander;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by Mel on 08/03/2017.
 */
public class ResultController {
    private Main main;
    private BorderPane root = new BorderPane();

    @FXML
    private Button menu;

    @FXML
    private GridPane grid;

    @FXML
    private Text fitInit = new Text();
    @FXML
    private Text fitFinal = new Text();
    @FXML
    private Text nbIterat = new Text();
    @FXML
    private Text temps = new Text();


    public ResultController() {


    }

    public void init(int init, int fitfin, int nbIt, double temps){
        fitInit.setText(Integer.toString(init));
        fitFinal.setText(Integer.toString(fitfin));
        nbIterat.setText(Integer.toString(nbIt));
        this.temps.setText(Double.toString(temps) + " secondes");
    }


    @FXML
    public void quit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter");
        alert.setHeaderText("Vous êtes sur le point de fermer l'application.");
        alert.setContentText("Voulez-vous vraiment quitter ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(0);
        } else {
            alert.close();
        }
    }

    @FXML
    public void relaunch(){
        Stage stage = (Stage) menu.getScene().getWindow();
        stage.close();

        Scene scene = null;
        try {
            final URL url = getClass().getResource("../view/Start.fxml");
            final FXMLLoader loader = new FXMLLoader(url);
            root = (BorderPane) loader.load();
            scene = new Scene(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Projet Dames - Optimisation");
        stage.setResizable(false);
        stage.show();
    }

    private void textDisplay(GridPane grid, String theText, int row, int col) {
        Text text = new Text();
        text.setWrappingWidth(50);
        text.setText(theText);
        text.setTextAlignment(TextAlignment.CENTER);
        GridPane.setRowIndex(text, row);
        GridPane.setColumnIndex(text, col);
        grid.getChildren().addAll(text);
    }
}
