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
        this.xmin = b;
        temp = -((xmin.getRowNb()^2)/Math.log(0.8));
        fmin = xmin.getFitness();
        this.nbTemperatures = nbTemp;
        mu = nbTemp/100;
        algorithm();


    }

    //FAIRE UN EXEMPLE SUR PAPIER, IL Y A SUREMENT UN SOUCIS ENTRE BORDTRANS ET XMIN ET Y: cf fitness sur l'affichage
    public void algorithm(){
        Board boardTransition = xmin;

        for(int k = 0; k < nbTemperatures; k++){
            for(int l = 1; l < 10*boardTransition.getRowNb(); l++){
                Random r = new Random();
                int i = r.nextInt(boardTransition.getNeighboursList().size());
                Board y = boardTransition.getNeighboursList().get(i);
                y.addAllNeighbours();
                System.out.println("Y: ");
                y.showBoard(); //Problème de fitness...
                double deltaF = y.getFitness() - boardTransition.getFitness();

                if (deltaF <= 0){ //y mieux que board initial
                    if (y.getFitness() < xmin.getFitness()){
                        xmin.setFitness(y.getFitness());
                        xmin = y;
                    }
                } else {
                    float p = r.nextInt(10)/10;
                    if (p <= Math.exp(-deltaF/ temp)) {
                        boardTransition = y;
                    }

                }
            }
            mu = k/nbTemperatures;
            temp = mu*temp;

        }
        System.out.println("Fitness finale: "+xmin.getFitness());
        System.out.println("Recuit simulé: ");
        boardTransition.showBoard();
    }








}
