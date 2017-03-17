package src.model;

import java.util.ArrayList;

/**
 * Created by Mel on 13/03/2017.
 */
public class RecuitAlgorithm {
    private ArrayList<Board> neighboursList = new ArrayList<>();
    private Board board;
    private int fitness;
    private double tempInit;


    public RecuitAlgorithm(Board b){
        this.board = b;
        tempInit = -((board.getRowNb()^2)/Math.log(0.8));
        fitness = board.getFitness();

        addAllNeighbours();
//        for (Board bo : neighboursList){
//            System.out.println("Voisin :");
//            bo.showBoard();
//        }


    }

    public void addAllNeighbours() {
        for (int i = board.getQueensList().size()-1; i >= 0 ; i--){                                                  // on parcourt la liste à l'envers car elle est mofifiée
            Board boardClone = board.clone();                                                                        // on crée une nouvelle instance de l'objet board avec le meme damier que le premier
            moveQueenRight(boardClone.getQueensList().get(i), boardClone);
            neighboursList.add(boardClone);
            boardClone = board.clone();
            moveQueenLeft(boardClone.getQueensList().get(i), boardClone);
        }
    }

    public void moveQueenRight(Case q, Board b){
        if (q.getY() + 1 < b.getRowNb()) {
            q.setQueen(false);
            b.update();
            b.getBoard()[q.getX()][q.getY() + 1].setQueen(true);
            b.getQueensList().add(b.getBoard()[q.getX()][q.getY() + 1]);
            System.out.println("queen right");
            b.showBoard();

        }

    }

    public void moveQueenLeft(Case q, Board b){
        if (q.getY() - 1 >= 0) {
            q.setQueen(false);
            b.update();
            b.getBoard()[q.getX()][q.getY() - 1].setQueen(true);
            b.getQueensList().add(b.getBoard()[q.getX()][q.getY() - 1]);
            System.out.println("queen left");
            b.showBoard();
        }

    }








}
