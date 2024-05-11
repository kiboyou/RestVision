package com.example.rest_vision.controller.report;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Report implements Initializable {
    @FXML
    private Label dashboard_nc;

    @FXML
    private AnchorPane dashboard_rj;

    @FXML
    private Label dashboard_tc;

    @FXML
    private AnchorPane dashboard_tv;

    @FXML
    private BarChart<?, ?> diagram_tc;

    @FXML
    private BarChart<?, ?> diagram_tv;

    @FXML
    private AnchorPane report;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
