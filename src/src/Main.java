package src;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @FXML
    private final ToggleGroup choixAlgo = new ToggleGroup();
    @FXML
    private RadioButton recuitSimule;
    @FXML
    private RadioButton tabou;
    @FXML
    private RadioButton grasp;
    @FXML
    private RadioButton genetique;
    @FXML
    private TextField taille;
    @FXML
    private ImageView dame;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Projet Dames - Optimisation");
        this.primaryStage.getIcons().setAll(new Image(getClass().getResource("../ressource/icon_32.png").toExternalForm()),
                new Image(getClass().getResource("../ressource/icon_126.png").toExternalForm()) );

        recuitSimule = new RadioButton();
        tabou = new RadioButton();
        grasp = new RadioButton();
        genetique = new RadioButton();
        taille = new TextField();

        initRootLayout();
        initGroupButton();

    }

    //initialise la fenetre
    public void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Start.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //initialise les radioBoutons
    public void initGroupButton(){
        recuitSimule.setToggleGroup(choixAlgo);
        tabou.setToggleGroup(choixAlgo);
        grasp.setToggleGroup(choixAlgo);
        genetique.setToggleGroup(choixAlgo);

    }

    //lancement du programme en fonction de l'algo choisi
    public void showResult() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Result.fxml"));
            AnchorPane home = (AnchorPane) loader.load();
            rootLayout.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //message d'erreur par rapport aux algos
    private void errorChoice(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur algorithme");
        alert.setHeaderText("Une erreur est survenue !");
        alert.setContentText("Veuillez selectionner un algorithme avant de lancer le programme.");

        alert.showAndWait();
    }

    //message d'erreur par rapport a la taille du plateau
    private void errorSize(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur taille plateau");
        alert.setHeaderText("Une erreur est survenue !");
        alert.setContentText("Veuillez renseigner un nombre pour le plateau avant de lancer le programme.");

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
            if (!(taille.getText().equals("")) && testDigit(taille.getText())) {
                System.out.println("Recuit : " + taille.getText());
            } else {
                errorSize();
            }
        }
        else if(tabou.isSelected()){
            if (!(taille.getText().equals(""))&& testDigit(taille.getText())){
                System.out.println("tabout");
            }
            else{
                errorSize();
            }
        }
        else if(grasp.isSelected()){
            if (!(taille.getText().equals(""))&& testDigit(taille.getText())){
                System.out.println("grasp");
            }
            else{
                errorSize();
            }
        }
        else if(genetique.isSelected()) {
            if (!(taille.getText().equals(""))&& testDigit(taille.getText())) {
                System.out.println("genetique");
            } else {
                errorSize();
            }
        }
        else{
            errorChoice();
        }
    }

    @FXML
    public void quit(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

    public Stage getPrimaryStage(){
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
