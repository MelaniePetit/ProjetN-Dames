package src.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Mel on 10/05/2017.
 */
public class GeneticAlgorithm extends Algorithm {

    private ArrayList<Board> population;        //utiliser un tableau ? (permttrait de stocker le board, la fitess, f(fitness), ...)
    private ArrayList<Board> reproduction;
    private ArrayList<Integer> fit;
    private ArrayList<Double> portion;
    private int taillePopInit;

    public GeneticAlgorithm(int taillePopInit, int tailleBoard) {
        super();

        this.taillePopInit = taillePopInit;

        population = new ArrayList<>();

        //Population courante
        for (int i = 0 ; i < taillePopInit ; i++){
            population.add(new Board(tailleBoard));
        }

        bestFit = population.get(0).fitness();
        bestBoard = population.get(0);

        algorithm();


    }

    public void algorithm(){

        int cpt = 0;

        while (bestFit != 0 && cpt < 100000){
            System.out.println("---------- " + cpt + " ----------");
            System.out.println("bestFit : " + bestFit);
            trouverBestBoard();
            System.out.println("reproduction");
            reproduction();
            System.out.println("croisement");
            croisementUnPoint();
            System.out.println("mutation");
            mutation();

            cpt ++;
        }

        nbItera = cpt;
        duree = (System.nanoTime()-duree)/Math.pow(10,9);



    }

    //REPRODUCTION
    public void reproduction(){

        /*----- Nouvelle population -----*/
        reproduction = new ArrayList<>();

        /*----- Fitness de chaque individu -----*/
        fit = new ArrayList<>();
        double totalFit = 0;
        for (Board b: population) {
            fit.add(b.fitness());
            totalFit += Math.pow(b.fitness(),2);
        }

        /*----- Pourcentage de chaque individu sur la roue -----*/
        portion = new ArrayList<>();

        //portion proportionnelle
        double test = 0;
        for (int i = 0 ; i < fit.size() ; i++) {
            double valeur = (double) Math.pow(fit.get(i),2) / totalFit;         //ne pas enlever le cast !!
            double arrondi = (double) Math.round(valeur * 100)/100;
            portion.add(arrondi);
            test += arrondi;
        }


        //roue biaisé
        for (Double d : portion) {
            Random r = new Random();
            float choix = (float) r.nextInt(100) / 100;


            //bornes permettant de savoir dans quelle partie de la roue on se trouve
            float bornSup = 0;
            float bornInf = 0;

            for (int j = 0; j < fit.size(); j++) {
                bornSup += portion.get(j);

                if (bornInf < choix && choix <= bornSup) {
                    reproduction.add(population.get(j));
                    break;
                }

                //gestion des effets de bords
                if (choix == 0){
                    reproduction.add(population.get(0));
                    break;
                }
                if (j == fit.size()-1){
                    reproduction.add(population.get(population.size()-1));
                    break;
                }

                bornInf += portion.get(j);

            }
        }
        population = new ArrayList<>(reproduction);
    }

    //CROISEMENT
    public void croisementUnPoint(){

        int coupure = bestBoard.getSize()/2;

        //On croise deux par deux, si la pop est de taille impaire, on ne touche pas le dernier individu
        if (bestBoard.getSize()%2 == 0){

            for (int i = 0 ; i < population.size() -1 ; i++){

                int temp;
                for (int j = 0 ; j < coupure ; j ++) {
                    temp = population.get(i).getRows().get(j);
                    population.get(i).getRows().set(j, population.get(i+1).getRows().get(j));
                    population.get(i+1).getRows().set(j, temp);

                }

                i++;
            }
        }
        else {
            for (int i = 0 ; i < population.size() - 2 ; i++){

                int temp;
                for (int j = 0 ; j < coupure ; j ++) {
                    temp = population.get(i).getRows().get(j);
                    population.get(i).getRows().set(j, population.get(i+1).getRows().get(j));
                    population.get(i+1).getRows().set(j, temp);

                }

                i++;
            }
        }
    }

    //MUTATION
    public void mutation(){

        //Un des individu aleatoir mute
        Random r = new Random();
        int numBoard = r.nextInt(population.size());

        //La mutation est definit par la changement de place d'une des reine sans que les autres ne bougent, on prend donc un des ces voisins
        Board boardSelect = population.get(numBoard);
        population.set(numBoard, boardSelect.neighbourRandom());


    }

    //Trouve le meilleur board parmis la pop
    public void trouverBestBoard(){
        for (Board b : population) {

            if (b.fitness() < bestFit){
                bestFit = b.fitness();
                bestBoard = b;
            }

        }
    }
}
