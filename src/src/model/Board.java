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

    public Board(ArrayList<Integer> rowsNeighboor) {
        size = rowsNeighboor.size();
        rows = rowsNeighboor;
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

    //Neighboors random
    public Board neighboorRandom(){

        ArrayList<Integer> rowsNeighboor = new ArrayList<>();
        System.arraycopy(rows,0,rowsNeighboor,0,size);

        Random r = new Random();

        int row1 = r.nextInt(size);

        int row2 = r.nextInt(size);

        while (row2 == row1){

            row2 = r.nextInt(size);

        }

        int temp = rows.get(row1); //number of the column
        rowsNeighboor.set(row1, rows.get(row2));
        rowsNeighboor.set(row2, temp);

        return new Board(rowsNeighboor);

    }



}
