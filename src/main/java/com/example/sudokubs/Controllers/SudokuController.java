package com.example.sudokubs.Controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class SudokuController {
    private final BorderPane root;
    private final GridPane grid;
    private final Button btnNewGame;
    private final Button btnHelp;

    public SudokuController() {

        root = new BorderPane();

        grid = new GridPane();

        btnNewGame = new Button("Nuevo Juego");
        btnHelp = new Button("Ayuda");

        root.setCenter(grid);
    }

    public Parent getRoot() {
        return root;
    }

    public GridPane getGrid() {
        return grid;
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }
}
