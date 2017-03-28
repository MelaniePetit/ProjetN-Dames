package src.model;

import java.util.ArrayList;

/**
 * Created by Mel on 13/03/2017.
 */
public class Queen {

    private int row;
    private int column;

    public Queen(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
