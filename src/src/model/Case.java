package src.model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by Mel on 13/03/2017.
 */
public class Case {
    private String name;
    private Board board;
    private int x; // row
    private int y; // column
    private ImageView imageCase;
    private ArrayList<Case> neighboursList = new ArrayList<>();
    private boolean queen = false;
    private int conflit = 0;


    public Case(int x, int y, Board board){
        this.x = x;
        this.y = y;
        name = x + "x" + y;
        this.board = board;

    }

    public Case(int x, int y, Board board, boolean queen){
        this.x = x;
        this.y = y;
        this.queen = queen;
        name = x + "x" + y;
        this.board = board;

    }

    public void addNeighbours() {
        for (int i = 0; i < board.getRowNb(); i++) {
            Object neighbours;
            if (y != i) {
                neighbours = board.getBoard()[x][i];                                 //case in same row
                neighboursList.add((Case) neighbours);
            }
            if (x != i) {
                neighbours = board.getBoard()[i][y];                                 //case in same column
                neighboursList.add((Case) neighbours);
            }
        }
        for (int i = 1; i < board.getRowNb(); i++){
            Object neighbours;
            if ( (x + i) < board.getRowNb() && (y + i) < board.getRowNb()){
                neighbours = board.getBoard()[x + i][y + i];                        //case in diagonal top-right
                neighboursList.add((Case) neighbours);
            }
            if ((x + i) < board.getRowNb() &&  (y - i) >= 0 ){
                neighbours = board.getBoard()[x + i][y - i];                        //case in diagonal top-left
                neighboursList.add((Case) neighbours);
            }
            if ((x - i) >= 0 && (y + i) < board.getRowNb()){
                neighbours = board.getBoard()[x - i][y + i];                        //case in diagonal bottom-right
                neighboursList.add((Case) neighbours);
            }
            if((x - i) >= 0 &&  (y - i) >= 0){
                neighbours = board.getBoard()[x - i][y - i];                        //case in diagonal bottom-left
                neighboursList.add((Case) neighbours);

            }

        }
    }

//    public void countConflict()



    //Getter & Setter
    public ArrayList<Case> getNeighboursList(){
        return neighboursList;
    }

    public String getName(){
        return name;
    }

    public boolean isQueen() {
        return queen;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageView getImageCase() {
        return imageCase;
    }

    public void setImageCase(ImageView imageCase) {
        this.imageCase = imageCase;
    }

    public void setNeighboursList(ArrayList<Case> neighboursList) {
        this.neighboursList = neighboursList;
    }

    public int getConflit() {
        return conflit;
    }

    public void setConflit(int conflit) {
        this.conflit = conflit;
    }
}
