package src.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import src.Main;

import java.util.Scanner;

/**
 * Created by Mel on 08/03/2017.
 */
public class resultController {
    private Main main;

    @FXML
    private GridPane grid;

    public resultController() {
        GridPane grid = new GridPane();
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez la taille du plateau : ");
        int i = sc.nextInt();

        for (int row = 0; row < (i + 1); row++) {
            for (int col = 0; col < (i + 1); col++) {
                if (col == 0 && row != i) {
                    textDisplay(grid, Integer.toString(row), row, col);
                } else if (row != i) {
                    Rectangle rec = new Rectangle();
                    rec.setWidth(50);
                    rec.setHeight(50);
                    if ((row % 2 == 0 && col % 2 == 0) || (row % 2 != 0 && col % 2 != 0)) {
                        rec.setFill(Color.WHITE);
                    } else {
                        rec.setFill(Color.BLACK);
                    }
                    GridPane.setRowIndex(rec, row);
                    GridPane.setColumnIndex(rec, col);
                    grid.getChildren().addAll(rec);
                }
            }
            if (row == i) {
                for (int col = 0; col < (i + 1); col++) {
                    if (col == 0) {
                        textDisplay(grid, "/", row, col);
                    } else {
                        textDisplay(grid, Integer.toString(col - 1), row, col);
                    }
                }
            }
        }
    }

    @FXML
    public void quit(){
        main.getPrimaryStage().close();
    }

    private void textDisplay(GridPane grid, String theText, int row, int col) {
        Text text = new Text();
        text.setWrappingWidth(50);
        text.setText(theText);
        text.setTextAlignment(TextAlignment.CENTER);
        GridPane.setRowIndex(text, row);
        GridPane.setColumnIndex(text, col);
        grid.getChildren().addAll(text);
    }
}
