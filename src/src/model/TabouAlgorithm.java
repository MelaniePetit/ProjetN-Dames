package src.model;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mel on 27/03/2017.
 */
public class TabouAlgorithm {

    private Board bestBoard;
    private int bestFit;
    private ArrayList<Board> t ; //a renommer

    public TabouAlgorithm(Board b){

        //Board initial
        this.bestBoard = b;

        //Fitness initiale
        this.bestFit = bestBoard.fitness();

        //Board qui sert a quoi?
        this.t = new ArrayList<>();

        algorithm();
    }

    public void algorithm(){

        ArrayList<Board> neighbours = new ArrayList<>();

        int cpt = 0;

        while( cpt < 30000 || neighbours.isEmpty()) {

            //Initialisation de la liste avec les voisins du board initial
            neighbours = bestBoard.neighbours();

            //Enlever les boards dans T si ils sont présents dans V
            for ( Board m : t ) {

                if ( neighbours.contains(m) ) {

                    neighbours.remove(m);

                }
            }

            if (!neighbours.isEmpty()) {

                Board bestB = bestBoard(neighbours); // Choix de y tel que ce soit la meilleure fitness de neighboors

                //On prend la meilleure fitness parmi les voisins de y

                //si cette fitness est moins bonne que celle de y on met le board dans T

                //si la fitness de y est inferieur a fbest, fbest = fy et best = y

            }



        }


    }

    public Board bestBoard(ArrayList<Board> boards){

        Board bestB = boards.get(0);

        for ( Board b : boards ) {

            if ( b.fitness() < bestB.getFitness() ) {

                bestB = b;

            }

        }

        return bestB;

    }

}
