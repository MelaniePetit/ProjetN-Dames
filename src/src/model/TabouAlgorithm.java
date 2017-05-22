package src.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Mel on 27/03/2017.
 */
public class TabouAlgorithm extends Algorithm {

    private ArrayList<Board> t ; //a renommer
    private int sizeT;

    public TabouAlgorithm(Board b, int size){
        super();

        //Board initial
        bestBoard = b;

        //Fitness initiale
        fitInit = bestBoard.fitness();
        bestFit = fitInit;

        sizeT = size ;
        t = new ArrayList<>(sizeT);

        System.out.println(" --- Start TabouAlgorithm : --- ");
        System.out.println("Fitness init : "  + bestFit);

        algorithm();
    }

    public void algorithm(){

        Board lastBoard = bestBoard ;
        ArrayList<Board> neighbours;
        Board bestNeigh ;

        int cpt = 0;

        do{

            //Initialisation de la liste avec les voisins du board initial
            neighbours = lastBoard.selectNeighbours(100);

            //Enlever les boards de T si ils sont présents dans V
            ArrayList<Board> temp = neighbours;
            for (int i = 0; i < t.size() ; i++) {
                for (int j =  neighbours.size() -1; j > 0 ; j--){

                    if (compareBoard(t.get(i), neighbours.get(j))) {
                        neighbours.remove(j);
                    }
                }
            }
            neighbours = temp;

            if (!neighbours.isEmpty()) {
                bestNeigh = selectBestBoard(neighbours); // Choix de y tel que ce soit la meilleure fitness de selectNeighbours
                int delta = bestNeigh.fitness() - lastBoard.fitness(); //Calcul de delta

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
                    System.out.println(bestFit);
                }

                lastBoard = bestNeigh ;
            }

            cpt ++ ;
            System.out.println(cpt);

        }while( cpt < 1000 && !neighbours.isEmpty() && bestFit > 0 );

        System.out.println("t = " + t.size());
        nbItera = cpt;
        duree = (System.nanoTime()-duree)/Math.pow(10,9);
//        bestBoard.showBoard();

    }

    public Board selectBestBoard(ArrayList<Board> boards){

        Board bestB = boards.get(0);
        if (bestBoard.getSize() > 1) {
            for (int i = 0; i < 100; i++) {
                Random r = new Random();
                int choice = r.nextInt(boards.size());
                HashSet cpt = new HashSet();
                if (cpt.add(choice)) {
                    if (boards.get(choice).fitness() < bestB.fitness()) {
                        bestB = boards.get(choice);
                    }
                }
                else{
                    i--;
                }

            }
        }
        else{
            for (Board b : boards){
                if (b.fitness() < bestB.fitness()) {
                    bestB = b;
                }
            }
        }

        return bestB;
    }

    //Comperer deux borads
    public boolean compareBoard(Board b1, Board b2){
        for (int i = 0 ; i < b1.getSize() ; i++){
            if (b1.getRows().get(i) != b2.getRows().get(i)){
                return false;
            }
        }
        return true;
    }

}
