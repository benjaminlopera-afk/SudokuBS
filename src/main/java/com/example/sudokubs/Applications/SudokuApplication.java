package com.example.sudokubs.Applications;

import com.example.sudokubs.Utils.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class SudokuApplication extends Application {

    private static Stage stageWindow;

    @Override
    public void start(Stage stage) {
        stageWindow = stage;
        stageWindow.setTitle("Sudoku 6x6");
        stageWindow.setResizable(false);
        setScene(Paths.INICIO);
    }

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