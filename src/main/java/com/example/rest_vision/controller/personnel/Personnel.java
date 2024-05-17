package com.example.rest_vision.controller.personnel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Personnel {
    @FXML
    private Pane contentArea;

    @FXML
    private void handlePage1Button() {
        loadPage("/com/example/rest_vision/view/personnel/employes.fxml");
    }

    @FXML
    private void handlePage2Button() {
        loadPage("/com/example/rest_vision/view/personnel/demandeComge.fxml");
    }

    @FXML
    private void handlePage3Button() {
        loadPage("/com/example/rest_vision/view/personnel/role.fxml");
    }


    private void loadPage(String pageFXML) {
        try {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(FXMLLoader.load(getClass().getResource(pageFXML)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
