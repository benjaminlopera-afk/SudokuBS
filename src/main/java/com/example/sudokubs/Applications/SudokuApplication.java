package com.example.sudokubs.Applications;

import com.example.sudokubs.Utils.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Clase principal de la aplicación Sudoku.
 * Gestiona la ventana principal y el cambio de escenas.
 */
public class SudokuApplication extends Application {

    /** Ventana principal de la aplicación. */
    private static Stage stageWindow;

    /**
     * Inicializa y muestra la ventana principal de la aplicación.
     *
     * @param stage escenario principal proporcionado por JavaFX
     */
    @Override
    public void start(Stage stage) {
        stageWindow = stage;
        stageWindow.setTitle("Sudoku 6x6");
        stageWindow.setResizable(false);
        setScene(Paths.INICIO);
    }

    /**
     * Cambia la escena actual de la ventana principal.
     *
     * @param path ruta del archivo FXML a cargar
     * @throws RuntimeException si no se puede cargar la vista
     */
    public static void setScene(String path) {
        FXMLLoader loader = new FXMLLoader(SudokuApplication.class.getResource(path));
        try {
            AnchorPane pane = loader.load();
            stageWindow.setScene(new Scene(pane));
            stageWindow.show();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la vista: " + path, e);
        }
    }
}