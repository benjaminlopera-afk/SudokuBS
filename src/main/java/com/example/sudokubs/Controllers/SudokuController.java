package com.example.sudokubs.Controllers;

import com.example.sudokubs.Model.SudokuBoard;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class SudokuController {

    @FXML private GridPane sudokuGrid;
    @FXML private Button btnNuevoJuego;
    @FXML private Button btnAyuda;
    @FXML private Label lblMensaje;

    private final TextField[][] celdas = new TextField[6][6];

    private final SudokuBoard board = new SudokuBoard();

    @FXML
    public void initialize() {
        buildGrid();
        board.generateBoard();
        updateView();
    }

    private void buildGrid() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField celda = new TextField();
                celda.getStyleClass().add("celda");
                celda.setPrefWidth(70);
                celda.setPrefHeight(70);

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

    private void updateView() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField celda = celdas[row][col];
                int value = board.getValue(row, col);

                // Limpiar estilos y texto previos
                celda.getStyleClass().removeAll("celda-fija", "celda-error");
                celda.setText(""); // <- agrega esta línea
                celda.setEditable(true); // <- y esta

                if (board.isFixed(row, col)) {
                    celda.setText(String.valueOf(value));
                    celda.setEditable(false);
                    celda.getStyleClass().add("celda-fija");
                }
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nuevo Juego");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas iniciar un nuevo juego?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                lblMensaje.setText("");
                board.generateBoard();
                updateView();
            }
        });
    }

    @FXML
    private void handleAyuda() {
        lblMensaje.setText("");
        // TODO: HU-5 lógica de ayuda
    }
}