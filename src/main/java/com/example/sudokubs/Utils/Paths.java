package com.example.sudokubs.Utils;

/**
 * Clase utilitaria que centraliza las rutas de los archivos FXML de la aplicación.
 * No puede ser instanciada ya que su uso es exclusivamente estático.
 */
public class Paths {

    /** Ruta de la pantalla de inicio. */
    public static final String INICIO  = "/com/example/sudokubs/Start.fxml";

    /** Ruta de la pantalla del tablero de Sudoku. */
    public static final String TABLERO = "/com/example/sudokubs/Sudoku.fxml";

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private Paths() {}
}