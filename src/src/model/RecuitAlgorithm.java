package src.model;

import java.util.Random;
import java.util.Timer;

/**
 * Created by Mel on 13/03/2017.
 */
public class RecuitAlgorithm extends Algorithm{

    private double temp;

    public RecuitAlgorithm(Board b) {
        super();

        this.bestBoard = b;
        //temp = 100*bestBoard.getSize();
        temp = 500;
        fitInit = bestBoard.fitness();
        bestFit = fitInit;

        System.out.println(" --- Start RecuitAlgorithm : --- ");
        System.out.println("Fitness init : "  + bestFit);

        algorithm();

    }

    public void algorithm() {

        Board board = this.bestBoard;
        int fitness = board.fitness();
        int cpt = 0;

        while (bestBoard.fitness() > 0 && cpt < 100000) {           //arret quand temp min atteinte, fitness = 0 ou après un certain nombre d'itération

            //Creer un voisin
            Board neighBoard = board.neighbourRandom();
            int neighFit = neighBoard.fitness();

            //écart des fitness
            double deltaF = neighFit - fitness;

            //si le voisin généré est meilleur que le plateau courant
            if (deltaF <= 0) {
                board = neighBoard;
                fitness = board.fitness();

                //si voisin est meilleur que le meilleur plateau connu
                if (fitness < bestFit) {
                    bestBoard = board;
                    bestFit = fitness;
                }
            }

            //si le voisin est moins bon que le plateau courant
            else {
                Random r = new Random();
                float p = r.nextFloat();

                //détermine si on remplace le plateau courant par son voisin
                if (p <= Math.exp(-deltaF / temp)) {
                    board = neighBoard;
                    fitness = board.fitness();
                }

            }

            cpt ++;
            double mu = 1/cpt;
            temp = 0.4*temp;
            //temp = mu*temp;

        }

        nbItera = cpt;
        duree = (System.nanoTime()-duree)/Math.pow(10,9);
//        bestBoard.showBoard();

    }


}
