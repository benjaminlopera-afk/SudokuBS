package com.example.sudokubs.Controllers;

import com.example.sudokubs.Applications.SudokuApplication;
import com.example.sudokubs.Utils.Paths;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button btnJugar;

    @FXML
    void inicioJugar(ActionEvent event) {

        SudokuApplication.setScene(Paths.TABLERO);
    }

}
