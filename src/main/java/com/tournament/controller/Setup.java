package com.tournament.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.tournament.db.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import java.net.URL;
import java.util.ResourceBundle;


public class Setup implements Initializable {
    DatabaseHandler databaseHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseHandler = new DatabaseHandler();
    }

    @FXML
    private Tab teams_tab;

    @FXML
    private JFXTextField teamID_fld;

    @FXML
    private JFXTextField teamName_fld;

    @FXML
    private Tab players_tab;

    @FXML
    private JFXTextField playerID_fld;

    @FXML
    private JFXTextField playerName_fld;

    @FXML
    private JFXTextField teamID_fld2;

    @FXML
    private JFXDatePicker birth_pck;

    @FXML
    private JFXButton save_btn;

    @FXML
    private JFXButton cancel_btn;

    @FXML
    void addPlayer(ActionEvent event) {
        if (players_tab.isSelected()){
            System.out.println("Save button pressed -> players tab selected");
            String playerID = null;

            String playerName = playerName_fld.getText();
            String birth = birth_pck.getValue().toString();     //TO-DO: Exception -> date not picked!
            String teamID = teamID_fld2.getText();
            System.out.println(playerName+birth+teamID);

            if (playerName.isEmpty()|| teamID.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please don't live the fields empty");
                alert.showAndWait();
                return;

            }

            String sqlQuery = "INSERT INTO PLAYER VALUES ("
                                    +  null + ","
                                    + "'" + playerName + "'" + ","
                                    + birth + ","
                                    + Integer.parseInt(teamID)
                                    + ")";
            if (databaseHandler.executeAction(sqlQuery))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Succesfully inserted data");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Data insertion failed");
                alert.showAndWait();
            }

        }



        if (teams_tab.isSelected())
            System.out.println("Save button pressed -> teams tab selected");
    }

    @FXML
    void cancel(ActionEvent event) {

    }


}
