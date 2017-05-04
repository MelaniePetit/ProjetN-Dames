package src.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mel on 27/03/2017.
 */
public class BoardV2 {

    private int nbRow;
    private ArrayList<Queen> queensList = new ArrayList<>();
    private int fitness;

    public BoardV2(int nbRow){
        this.nbRow = nbRow;
        Queen queen;
        for (int i = 0; i < nbRow ; i++) {
            Random r = new Random();
            int index = r.nextInt(nbRow);
            queen = new Queen(i, index);
            queensList.add(queen);
        }
        fitness = countConflicts();
        showBoard();
//        moveQueenRandom();
//        fitness = countConflicts();
//        showBoard();
    }

    //constructeur prenant une liste de reine deja existante en argument
    public BoardV2(int nbRow, ArrayList<Queen> queensList){
        this.nbRow = nbRow;
        for (Queen q : queensList) {
            this.queensList.add(new Queen(q.getRow(), q.getColumn()));
        }
    }

    //calcul de la fitness => nombre de conflit du plateau
    public int countConflicts() {
        int fit = 0;
        for (Queen ref : queensList) {
            for (Queen test : queensList) {
                if (ref != test) {
                    if (ref.getColumn() == test.getColumn()) {                                           //colonne
                        fit++;
                    }
                    if (ref.getColumn() + ref.getRow() == test.getColumn() + test.getRow()) {           //diagonale montante
                        fit++;
                    }
                    if (ref.getColumn() - ref.getRow() == test.getColumn() - test.getRow()){            //diagonale descendante
                        fit++;
                    }
                }
            }
        }
        return fit/2;
    }


    //affiche le placement des reines sur le plateau
    public void showBoard() {
        for (Queen q: queensList) {
            System.out.println(q.getRow() + "x" + q.getColumn());
        }
        System.out.println("Fitness: " + fitness);
        System.out.println("_________________");
    }

    //deplace une reine aléatoirement
    public void moveQueenRandom(){
        int index = (new Random()).nextInt(nbRow);              //choisi la ligne ou bouger la reine
        int move = (new Random()).nextInt(nbRow);               //choisi le nouvel emplacement de la reine
        while (queensList.get(index).getColumn() == move) {       //vérifie que l'on ne remet pas la reine au meme endroit
            index = (new Random()).nextInt(nbRow);              //choisi la ligne ou bouger la reine
            move = (new Random()).nextInt(nbRow);
        }
        queensList.get(index).setColumn(move);
    }

    //deplace la reine i sur la droite
    public void moveQueenRight(int i){
        if (queensList.get(i).getColumn() < 3){
            queensList.get(i).setColumn(queensList.get(i).getColumn() + 1);
        }
    }

    //deplace la reine i sur la gauche
    public void moveQueenLeft(int i){
        if (queensList.get(i).getColumn() > 1){
            queensList.get(i).setColumn(queensList.get(i).getColumn() - 1);
        }
    }

    //GETTERS AND SETTERS
    public int getNbRow() {
        return nbRow;
    }

    public void setNbRow(int nbRow) {
        this.nbRow = nbRow;
    }

    public ArrayList<Queen> getQueensList() {
        return queensList;
    }

    public void setQueensList(ArrayList<Queen> queensList) {
        this.queensList = queensList;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
