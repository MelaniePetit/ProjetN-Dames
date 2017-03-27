package src.model;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mel on 27/03/2017.
 */
public class Tabou {

    private Board xmin;
    private int fmin;
    private ArrayList<Board> t = new ArrayList<>();
    private int sizeT;

    public Tabou(Board b){
        this.xmin = b; //Idem original
        sizeT = xmin.getRowNb()/2;
        fmin = xmin.getFitness();
        algorithm();
    }

    public void algorithm() {
        Board boardTransition = xmin;
        Board y;
        ArrayList<Board> c = new ArrayList<>();
        c = updateC(boardTransition.getNeighboursList());
        int compt = 0;
        while (!c.isEmpty() && xmin.getFitness() != 0 && compt < xmin.getRowNb()*100){                                       // i ?
            y = fitnessMin(c);
            y.addAllNeighbours();
            double deltaF = y.getFitness() - boardTransition.getFitness();
            if (deltaF >= 0){
                addElem(y);
            }
            if (y.getFitness() < fmin){
                xmin = y;
            }
            boardTransition = y;
            c = updateC(boardTransition.getNeighboursList());
            System.out.println(compt);
            compt++;
        }

        System.out.println("Fitness finale: "+xmin.getFitness());
        System.out.println("Tabou: ");
        xmin.showBoard();
    }

    //Liste des voisins non testés
    public ArrayList<Board> updateC(ArrayList<Board> v){
        ArrayList<Board> c = new ArrayList<>();
        for (Board m : v) {
            if (!t.contains(m)){
                c.add(m);
            }
        }
        return c;
    }

    //Choisir le voisin ayant la meilleur fitness
    public Board fitnessMin(ArrayList<Board> c){
        Board bordMin = c.get(0);
        int fitnessMin = bordMin.getFitness();
        for (Board z: c){
            if (z.getFitness() < fitnessMin){
                bordMin = z;
                fitnessMin = bordMin.getFitness();
            }
        }
        return bordMin;
    }

    public void addElem(Board m) {
        if (t.size() == sizeT) {
            t.remove(0);
        }
        t.add(m);
    }
}
