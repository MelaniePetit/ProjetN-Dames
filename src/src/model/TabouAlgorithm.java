package src.model;

import java.util.ArrayList;

/**
 * Created by Mel on 27/03/2017.
 */
public class TabouAlgorithm {

    private Board bestBoard;

    private int bestFit;

    private ArrayList<Board> t ; //a renommer

    private int sizeT ;

    public TabouAlgorithm(Board b){

        //Board initial
        this.bestBoard = b;

        //Fitness initiale
        this.bestFit = bestBoard.fitness();

        //Board qui sert a quoi?
        this.sizeT = 1 ;
        this.t = new ArrayList<>(sizeT);

        System.out.println(" --- Start TabouAlgorithm : --- ");
        System.out.println("Fitness init : "  + bestFit);

        algorithm();
    }

    public void algorithm(){

        ArrayList<Board> neighbours = new ArrayList<>();

        Board lastBoard = bestBoard ;

        Board bestNeigh ;

        int cpt = 0;

        while( cpt < 30000 || neighbours.isEmpty() || bestFit == 0 ) {

            //Initialisation de la liste avec les voisins du board initial
            neighbours = lastBoard.selectNeighbours();

            //Enlever les boards de T si ils sont présents dans V
            for ( Board m : t ) {

                if ( neighbours.contains(m) ) {

                    neighbours.remove(m);

                }
            }

            if (!neighbours.isEmpty()) {

                bestNeigh = selectBestBoard(neighbours); // Choix de y tel que ce soit la meilleure fitness de selectNeighbours

                int delta = bestNeigh.fitness() - lastBoard.fitness(); //calcul de delta

                //si cette fitness est moins bonne que celle de y on met le board dans T
                if ( delta >= 0 ) {
                    if ( t.size() == sizeT ) {

                        t.remove(t.get(0));

                    }

                    t.add(lastBoard);

                }

                //si la fitness de y est inferieur a fbest, fbest = fy et best = y
                if ( bestNeigh.fitness() < bestFit ) {

                    bestBoard = bestNeigh;
                    bestFit = bestBoard.fitness();
                }

                lastBoard = bestNeigh ;
            }

            cpt ++ ;

        }

        System.out.println("compteur : " + cpt);
        System.out.println("Fitness finale: " + bestFit);


    }

    public Board selectBestBoard(ArrayList<Board> boards){

        Board bestB = boards.get(0);

        for ( Board b : boards ) {

            if ( b.fitness() < bestB.fitness() ) {

                bestB = b;

            }

        }

        return bestB;

    }

}
