package src.model;

import java.util.Random;

/**
 * Created by Mel on 13/03/2017.
 */
public class RecuitAlgorithm {

    private Board xmin;
    private int fmin;
    private double temp;
    private int nbTemperatures;
    private double mu;

    public RecuitAlgorithm(Board b, int nbTemp){
        this.xmin = b; //Idem original
        temp = -((xmin.getNbRow()^2)/Math.log(0.8));
        fmin = xmin.getFitness();
        this.nbTemperatures = nbTemp;
        mu = nbTemp/100;
        algorithm();


    }

    public void algorithm(){

        Board bestBoard = xmin;

        while(xmin.getFitness() > 0){
            for(int l = 1; l < 10*boardTransition.getNbRow(); l++){
                BoardV2 y = new BoardV2(boardTransition.getNbRow(), boardTransition.getQueensList());
                y.moveQueenRandom();
                y.setFitness(y.countConflicts());
//                System.out.println("Y: ");
//                y.showBoard();
                double deltaF = y.getFitness() - boardTransition.getFitness();

                if (deltaF <= 0){ //y mieux que board initial
                    boardTransition = y;
                    if (boardTransition.getFitness() < xmin.getFitness()){
                        xmin = boardTransition;
                    }
                } else {
                    Random r = new Random();
                    float p = r.nextInt(10)/10;
                    if (p <= Math.exp(-deltaF/ temp)) {
                        boardTransition = y;
                    }

                }
                int k = 0;
                mu = k/nbTemperatures;
                temp = mu*temp;
                k++;
            }


        }
        System.out.println("Fitness finale: "+xmin.getFitness());
        System.out.println("Recuit simulé: ");
        xmin.showBoard();
    }

//    public Board randomNeighbour(Board b){
//
//    }








}
