package src.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class MainController {

    @FXML
    private final ToggleGroup choiceAlgo = new ToggleGroup();
    @FXML
    private RadioButton recuitSimule =  new RadioButton();
    @FXML
    private RadioButton tabou = new RadioButton();
    @FXML
    private RadioButton grasp = new RadioButton();
    @FXML
    private RadioButton genetique = new RadioButton();
    @FXML
    private TextField size;
    @FXML
    private Button launch;

    private int sizeBoard;
    private Parent root;
    private Board board;
    private Algorithm algorithm;

    public MainController(){
        recuitSimule.setToggleGroup(choiceAlgo);
        tabou.setToggleGroup(choiceAlgo);
        grasp.setToggleGroup(choiceAlgo);
        genetique.setToggleGroup(choiceAlgo);
    }

    //message d'erreur par rapport aux algos
    private void errorChoice(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur algorithme");
        alert.setHeaderText("Une erreur est survenue !");
        alert.setContentText("Veuillez selectionner un algorithme avant de lancer le programme.");

        alert.showAndWait();
    }

    //message d'erreur par rapport a la size du Board
    private void errorSize(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur size Board");
        alert.setHeaderText("Une erreur est survenue !");
        alert.setContentText("Veuillez renseigner un nombre pour le Board avant de lancer le programme.");

        alert.showAndWait();
    }

    //teste si la valeur renseigner par le client est bien un chiffre
    public boolean testDigit(String val){
        char[] cs = val.toCharArray();
        for ( int i = 0;  i< cs.length; i++){
            if (!Character.isDigit(cs[i])) return false;
        }
        return true;
    }

    @FXML
    private void launch(){
        if (recuitSimule.isSelected()){
            if (!(size.getText().equals("")) && testDigit(size.getText())) {
                sizeBoard = Integer.parseInt(size.getText());
                board = new Board(sizeBoard);
                algorithm = new RecuitAlgorithm(board);
            } else {
                errorSize();
                return;

            }
        }
        else if(tabou.isSelected()){
            if (!(size.getText().equals(""))&& testDigit(size.getText())){
                sizeBoard = Integer.parseInt(size.getText());
                board = new Board(sizeBoard);
                algorithm = new TabouAlgorithm(board);
            }
            else{
                errorSize();
                return;

            }
        }
        else if(genetique.isSelected()) {
            if (!(size.getText().equals(""))&& testDigit(size.getText())) {
                sizeBoard = Integer.parseInt(size.getText());
                algorithm = new GeneticAlgorithm(15, sizeBoard);
            } else {
                errorSize();
                return;

            }
        }
        else{
            errorChoice();
            return;

        }

        Stage stage = (Stage)launch.getScene().getWindow();
        stage.close();

        Scene scene = null;
        try {
//            ResultController controller =
            final URL url = getClass().getResource("../view/Result.fxml");
            final FXMLLoader loader = new FXMLLoader(url);
            ResultController controller = new ResultController();
            loader.setController(controller);
            root = (AnchorPane) loader.load();
            controller.init(algorithm.getFitInit(), algorithm.getBestFit(), algorithm.getNbItera(), algorithm.getDuree());

            scene = new Scene(root);


        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Projet Dames - Optimisation");
        stage.setResizable(false);
        stage.show();


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

}
