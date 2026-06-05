package com.example.sudokubs.Controllers;

import com.example.sudokubs.Applications.SudokuApplication;
import com.example.sudokubs.Utils.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML private GridPane sudokuGrid;
    @FXML private Button btnNuevoJuego;
    @FXML private Button btnAyuda;
    @FXML private Label lblMensaje;

    private TextField[][] celdas = new TextField[6][6];

    @FXML
    public void initialize() {
        buildGrid();
    }

    private void buildGrid() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField celda = new TextField();
                celda.getStyleClass().add("celda");
                celda.setPrefWidth(70);
                celda.setPrefHeight(70);

                // Solo acepta un número del 1 al 6
                celda.setTextFormatter(new TextFormatter<>(change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("[1-6]?")) {
                        return change;
                    }
                    return null;
                }));

                celda.setStyle(getCellBorderStyle(row, col));

                celdas[row][col] = celda;
                sudokuGrid.add(celda, col, row);
            }
        }
    }

    private String getCellBorderStyle(int row, int col) {
        String top    = "1px";
        String right  = (col == 2) ? "3px" : "1px";
        String bottom = (row == 1 || row == 3) ? "3px" : "1px";
        String left   = "1px";
        return "-fx-border-width: " + top + " " + right + " " + bottom + " " + left + ";";
    }

    @FXML
    private void handleNuevoJuego() {
        lblMensaje.setText("");
        // TODO: HU-2 lógica de generación del tablero
    }

    @FXML
    private void handleAyuda() {
        lblMensaje.setText("");
        // TODO: HU-5 lógica de ayuda
    }
}