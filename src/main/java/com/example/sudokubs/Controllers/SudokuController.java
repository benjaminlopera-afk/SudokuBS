package com.example.sudokubs.Controllers;

import com.example.sudokubs.Model.SudokuBoard;
import com.example.sudokubs.Model.SudokuBoardValidator;
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

    private TextField celdaHint = null;

    private final SudokuBoardValidator validator = new SudokuBoardValidator(board);

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

                final int r = row, c = col;
                celda.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        int value = Integer.parseInt(newValue);
                        board.setValue(r, c, value);
                        if (!validator.isValidMove(r, c, value)) {
                            celda.getStyleClass().removeAll("celda-error");
                            celda.getStyleClass().add("celda-error");
                            lblMensaje.setText("Número inválido en fila, columna o bloque.");
                        } else {
                            celda.getStyleClass().remove("celda-error");
                            lblMensaje.setText("");
                            if (validator.isBoardComplete()) {
                                lblMensaje.setText("¡Felicidades! ¡Completaste el Sudoku!");
                                lblMensaje.setStyle("-fx-text-fill: lightgreen; -fx-font-size: 18px; -fx-font-weight: bold;");
                                btnAyuda.setDisable(true);
                            }
                        }
                    } else {
                        board.setValue(r, c, 0);
                        celda.getStyleClass().remove("celda-error");
                        lblMensaje.setText("");
                    }
                });

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

                celda.getStyleClass().removeAll("celda-fija", "celda-error");
                celda.setText("");
                celda.setEditable(true);

                if (board.isFixed(row, col)) {
                    celda.setText(String.valueOf(value));
                    celda.setEditable(false);
                    celda.getStyleClass().add("celda-fija");
                }
            }
        }
        lblMensaje.setText("");
        lblMensaje.setStyle("");
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
                btnAyuda.setDisable(false);
            }
        });
    }

    @FXML
    private void handleAyuda() {
        // Limpiar sugerencia anterior
        if (celdaHint != null) {
            celdaHint.getStyleClass().remove("celda-hint");
            celdaHint = null;
        }

        int[] hint = board.getHint(validator);

        if (hint == null) {
            lblMensaje.setText("No hay sugerencias disponibles.");
            btnAyuda.setDisable(true);
            return;
        }

        int row = hint[0];
        int col = hint[1];
        int value = hint[2];

        // Primero asignar, después usar
        celdaHint = celdas[row][col];
        celdaHint.setText(String.valueOf(value));
        celdaHint.getStyleClass().add("celda-hint");
        lblMensaje.setText("Sugerencia aplicada. ¡Puedes modificarla!");
    }

}