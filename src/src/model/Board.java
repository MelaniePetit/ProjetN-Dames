package src.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mel on 13/03/2017.
 */
public class Board {

    private int rowNb;
    private Case[][] board ;
    private int fitness;
    private ArrayList<Case> queensList = new ArrayList<>();
    private ArrayList<Board> neighboursList = new ArrayList<>();

    public Board(int nb) {
        rowNb = nb;
        board = new Case[rowNb][rowNb];
        for (int i = 0; i < rowNb; i++) { //i ligne, j colonne
            for (int j = 0; j < rowNb; j++) {
                board[i][j] = new Case(i,j, this); //mettre l'instance en 3eme argument ou l'attribut board ?

            }
        }
        randomBoard();
        listNeighbours();

        System.out.println("Original :");
        showBoard();
        fitness = countConflict();

//        showNeighbours();
//        for (Case c : queensList){
//            System.out.print(c.getName());
//
//        }
        addAllNeighbours();
//        for (Board bo : neighboursList){
//            System.out.println("Voisin :");
//            bo.showBoard();
//        }


    }

    public Board (int i, Case[][] old){                                             //Constructeur permmettant la création d'une nouvelle instance en copiant un damier existant
        rowNb = i;
        board = new Case[i][i];
        for (int k = 0; k < rowNb; k++) {                                           //k ligne, j colonne
            for (int j = 0; j < rowNb; j++) {
                board[k][j] = new Case(old[k][j].getX(), old[k][j].getY(), this, old[k][j].isQueen()) ;
            }

        }
        creatListQueen();
        listNeighbours();

//        System.out.println("Copie à partir d'existant :");
//        showBoard();

//        showNeighbours();
//        for (Case c : queensList){
//            System.out.print(c.getName());
//
//        }

    }

    private void listNeighbours() {
        for (int i = 0; i < rowNb; i++) { //i ligne, j colonne
            for (int j = 0; j < rowNb; j++) {
                board[i][j].addNeighbours();
            }
        }
    }


    public void randomBoard(){
        for (int k = 0; k < rowNb; k++){
            Random r = new Random();
            int i = r.nextInt(rowNb);
            if (board[k][i].isQueen()){
                k--;
            }
            else{
                board[k][i].setQueen(true);
                queensList.add(board[k][i]);
            }

        }
    }


    public void showBoard() {
        for (int i = 0; i < rowNb; i++) {
            for (int j = 0; j < rowNb; j++) {
                if (board[i][j].isQueen())
                    System.out.print(1 + " ");
                else{
                    System.out.print(0 + " ");

                }
            }
            System.out.println("\n");

        }

    }

    public void showNeighbours(){
        for (int i = 0; i < rowNb; i++) {
            for (int j = 0; j < rowNb; j++) {
                System.out.println(board[i][j].getName() + " a pour voisin :");
                for (Case c: board[i][j].getNeighboursList()) {
                    System.out.println(c.getName());
                }
            }
        }
    }

    public int countConflict(){
        int conflicts = 0;
        for (Case c : queensList){
            for (Case n : c.getNeighboursList()){
                if (n.isQueen()){
                    conflicts++;
                }

            }
        }
        return conflicts/2;
    }

    public void update(){
        for (int i = queensList.size()-1; i > 0 ; i--){
            if (!queensList.get(i).isQueen()){
                queensList.remove(i);
            }
        }
    }

    public Board clone(){
        Board newBoard = new Board(rowNb, board);
        return newBoard;
    }

    public void creatListQueen(){
        queensList = new ArrayList<>();
        for (int i = 0; i < rowNb; i++) {
            for (int j = 0; j < rowNb; j++) {
                if (board[i][j].isQueen())
                    queensList.add(board[i][j]);
            }
        }
    }

    public void addAllNeighbours() {
        for (int i = this.getQueensList().size()-1; i >= 0 ; i--){                                                  // on parcourt la liste à l'envers car elle est mofifiée
            Board boardClone = this.clone();                                                                        // on crée une nouvelle instance de l'objet boardInit avec le meme damier que le premier
            moveQueenRight(boardClone.getQueensList().get(i), boardClone);
            boardClone = this.clone();
            moveQueenLeft(boardClone.getQueensList().get(i), boardClone);
        }
    }

    public void moveQueenRight(Case q, Board b){
        if (q.getY() + 1 < b.getRowNb()) {
            q.setQueen(false);
            b.update();
            b.getBoard()[q.getX()][q.getY() + 1].setQueen(true);
            b.getQueensList().add(b.getBoard()[q.getX()][q.getY() + 1]);
            b.creatListQueen();
            b.listNeighbours();
            b.setFitness(b.countConflict());

//            System.out.println("queen right");
//            b.showBoard();
            this.neighboursList.add(b);
        }

    }

    public void moveQueenLeft(Case q, Board b){
        if (q.getY() - 1 >= 0) {
            q.setQueen(false);
            b.update();
            b.getBoard()[q.getX()][q.getY() - 1].setQueen(true);
            b.getQueensList().add(b.getBoard()[q.getX()][q.getY() - 1]);
            b.creatListQueen();
            b.listNeighbours();
            b.setFitness(b.countConflict());
            //System.out.println("queen left");
            //b.showBoard();
            this.neighboursList.add(b);
        }

    }
    //Getter and Setter
    public Case[][] getBoard(){
        return board;
    }

    public void setBoard(Case[][] board){
        this.board = board;
    }

    public void setRowNb(int i){
        rowNb = i;
    }

    public int getRowNb(){
        return rowNb;
    }

    public int getFitness(){
        return fitness;
    }

    public ArrayList<Case> getQueensList() {
        return queensList;
    }

    public void setQueensList(ArrayList<Case> queensList) {
        this.queensList = queensList;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public ArrayList<Board> getNeighboursList() {
        return neighboursList;
    }

    public void setNeighboursList(ArrayList<Board> neighboursList) {
        this.neighboursList = neighboursList;
    }
}
