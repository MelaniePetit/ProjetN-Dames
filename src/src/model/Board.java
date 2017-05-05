package src.model;

import java.util.*;

import static java.util.Collections.shuffle;

/**
 * Created by Mel on 13/03/2017.
 */
public class Board {

    //Size of the board
    private int size;

    //Array of rows
    private ArrayList<Integer> rows; //liste ou tableau ?

    //Constructor
    public Board(int boardSize){
        size = boardSize;
        rows = new ArrayList<>();
        initRows();
    }

    public Board(ArrayList<Integer> rowsNeighbour) {
        size = rowsNeighbour.size();
        rows = rowsNeighbour;
    }

    //Initialization of columns and rows - 1 queen by column and by row
    public void initRows(){
        for(int i = 0; i < size ; i++ ){
            rows.add(i);
        }
        shuffle(rows);
    }

    //fitness
    public int fitness(){

        int fitness = 0;

        for(int i = 0; i < size ; i++){

            int columnRef = rows.get(i);

            for(int j = i + 1 ; j < size ; j++){
                int columnTest = rows.get(j);

                if(Math.abs(i-j) == Math.abs(columnRef - columnTest)){

                    fitness++;

                }

            }

        }

        return fitness;
    }

    //Neighbours random
    public Board neighbourRandom(){

        ArrayList<Integer> rowsNeighbour = new ArrayList<>();
        System.arraycopy(rows,0,rowsNeighbour,0,size);

        Random r = new Random();

        int row1 = r.nextInt(size);

        int row2 = r.nextInt(size);

        while (row2 == row1){

            row2 = r.nextInt(size);

        }

        int temp = rows.get(row1); //number of the column
        rowsNeighbour.set(row1, rows.get(row2));
        rowsNeighbour.set(row2, temp);

        return new Board(rowsNeighbour);

    }

    //get neighbours
    public ArrayList<Board> neighbours(){

        ArrayList<Board> neighbours = new ArrayList<Board>();

        ArrayList<Integer> rowsNeighbour = new ArrayList<>();

        for(int i = 0 ; i < size ; i++ ){

            for (int j = i + 1; j < size ; j++){

                System.arraycopy(rows,0,rowsNeighbour,0,size);
                rowsNeighbour.set(i, rows.get(j));
                rowsNeighbour.set(j, rows.get(i));
                neighbours.add(new Board(rowsNeighbour));

            }

        }

        return neighbours;

    }




}
