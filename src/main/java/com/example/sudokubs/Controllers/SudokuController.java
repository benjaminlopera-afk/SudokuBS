package com.example.sudokubs.Controllers;

import com.example.sudokubs.Model.SudokuBoard;
import com.example.sudokubs.Model.SudokuBoardValidator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Controlador principal del tablero de Sudoku.
 * Gestiona la construcción de la cuadrícula, la interacción con las celdas
 * y los eventos de los botones.
 */
public class SudokuController {

    /** Cuadrícula principal del tablero. */
    @FXML private GridPane sudokuGrid;

    /** Botón para iniciar un nuevo juego. */
    @FXML private Button btnNuevoJuego;

    /** Botón para solicitar una sugerencia. */
    @FXML private Button btnAyuda;

    /** Etiqueta para mostrar mensajes al jugador. */
    @FXML private Label lblMensaje;

    /** Matriz de campos de texto que representan las celdas del tablero. */
    private final TextField[][] celdas = new TextField[6][6];

    /** Modelo del tablero de Sudoku. */
    private final SudokuBoard board = new SudokuBoard();

    /** Celda actualmente resaltada como sugerencia. */
    private TextField celdaHint = null;

    /** Validador de movimientos del tablero. */
    private final SudokuBoardValidator validator = new SudokuBoardValidator(board);

    /**
     * Inicializa el controlador, construye la cuadrícula y genera un tablero inicial.
     * Es llamado automáticamente después de cargar el FXML.
     */
    @FXML
    public void initialize() {
        buildGrid();
        board.generateBoard();
        updateView();
    }

    /**
     * Construye la cuadrícula 6x6 de TextFields y los agrega al GridPane.
     * Configura el formateador de texto, los estilos de borde y el listener
     * de validación en tiempo real para cada celda.
     */
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

    /**
     * Actualiza la vista con los valores actuales del modelo.
     * Las celdas fijas se muestran en rojo y no son editables.
     * Limpia mensajes y estilos previos.
     */
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

    /**
     * Genera el estilo de borde de una celda según su posición.
     * Aplica bordes más gruesos para separar visualmente los bloques 2x3.
     *
     * @param row índice de fila (0-5)
     * @param col índice de columna (0-5)
     * @return cadena CSS con el estilo de borde
     */
    private String getCellBorderStyle(int row, int col) {
        String top    = "1px";
        String right  = (col == 2) ? "3px" : "1px";
        String bottom = (row == 1 || row == 3) ? "3px" : "1px";
        String left   = "1px";
        return "-fx-border-width: " + top + " " + right + " " + bottom + " " + left + ";";
    }

    /**
     * Maneja el evento del botón "Nuevo Juego".
     * Muestra una alerta de confirmación y genera un nuevo tablero si el usuario acepta.
     */
    @FXML
    private void handleNuevoJuego() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Nuevo Juego");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas iniciar un nuevo juego?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                board.generateBoard();
                updateView();
                btnAyuda.setDisable(false);
            }
        });
    }

    /**
     * Maneja el evento del botón "Ayuda".
     * Sugiere un número válido para una celda vacía aleatoria sin completar el tablero.
     * Deshabilita el botón cuando solo queda una celda vacía.
     */
    @FXML
    private void handleAyuda() {
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

        celdaHint = celdas[row][col];
        celdaHint.setText(String.valueOf(value));
        celdaHint.getStyleClass().add("celda-hint");
        lblMensaje.setText("Sugerencia aplicada. ¡Puedes modificarla!");
    }
}