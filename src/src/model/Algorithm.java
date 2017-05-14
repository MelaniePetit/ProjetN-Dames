package src.model;

import src.controller.ResultController;

/**
 * Created by Mel on 09/03/2017.
 */
public abstract class Algorithm {

    protected int fitInit;
    protected Board bestBoard;
    protected int bestFit;
    protected double duree;
    protected int nbItera;

    public Algorithm() {
        duree = System.nanoTime();
    }

    public abstract void algorithm();

    public int getFitInit() {
        return fitInit;
    }

    public Board getBestBoard() {
        return bestBoard;
    }

    public int getBestFit() {
        return bestFit;
    }

    public double getDuree() {
        return duree;
    }

    public int getNbItera() {
        return nbItera;
    }
}
