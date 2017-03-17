package src.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mel on 13/03/2017.
 */
public class RecuitAlgorithm {
    private ArrayList<Board> neighboursList = new ArrayList<>();
    private Board boardInit;
    private int fitness;
    private double temp;
    private int nbTemperatures;


    public RecuitAlgorithm(Board b, int nbTemp){
        this.boardInit = b;
        temp = -((boardInit.getRowNb()^2)/Math.log(0.8));
        fitness = boardInit.getFitness();
        this.nbTemperatures = nbTemp;

        addAllNeighbours();
        for (Board bo : neighboursList){
            System.out.println("Voisin :");
            bo.showBoard();
        }
        algorithm();


    }

    public void addAllNeighbours() {
        for (int i = boardInit.getQueensList().size()-1; i >= 0 ; i--){                                                  // on parcourt la liste à l'envers car elle est mofifiée
            Board boardClone = boardInit.clone();                                                                        // on crée une nouvelle instance de l'objet boardInit avec le meme damier que le premier
            moveQueenRight(boardClone.getQueensList().get(i), boardClone);
            boardClone = boardInit.clone();
            moveQueenLeft(boardClone.getQueensList().get(i), boardClone);
        }
    }

    public void moveQueenRight(Case q, Board b){
        if (q.getY() + 1 < b.getRowNb()) {
            q.setQueen(false);
            b.update();
            b.getBoard()[q.getX()][q.getY() + 1].setQueen(true);
            b.getQueensList().add(b.getBoard()[q.getX()][q.getY() + 1]);
            //System.out.println("queen right");
            //b.showBoard();
            this.neighboursList.add(b);
        }

    }

    public void moveQueenLeft(Case q, Board b){
        if (q.getY() - 1 >= 0) {
            q.setQueen(false);
            b.update();
            b.getBoard()[q.getX()][q.getY() - 1].setQueen(true);
            b.getQueensList().add(b.getBoard()[q.getX()][q.getY() - 1]);
            //System.out.println("queen left");
            //b.showBoard();
            this.neighboursList.add(b);
        }

    }

    public void algorithm(){
        Board boardTransition = boardInit;

        for(int k = 0; k < nbTemperatures; k++){
            for(int l = 1; l < neighboursList.size(); l++){    //n2 on c pas ce quze c'est
                Random r = new Random();
                int i = r.nextInt(neighboursList.size()); //dans les voisins du bord transition -> deplacer les methodes pour avoir les voisins
                Board y = neighboursList.get(i); //dans bord transition
                double deltaF = y.getFitness() - boardTransition.getFitness();

                if (deltaF <= 0){ //y mieux que board transition
                    if (y.getFitness() < boardInit.getFitness()){
                        boardInit.setFitness(y.getFitness());
                        boardInit = y; //doublons avec ligne du dessus ?
                    }
                } else {
                    float p = r.nextInt(10)/10;
                    if (p <= Math.exp(-deltaF/ temp)) {
                        boardTransition = y;
                    }
                }
                i = i+1; //i l ou k ?????????
            }
            //temp = g(tempPrecedente);
        }
    }








}
