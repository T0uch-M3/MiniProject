/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class StockScreenController implements Initializable {

    @FXML
    BorderPane stockBorderPane;
    @FXML
    Pane modelLikePane, modelBg;
    @FXML
    GridPane gridPane, gridPane2;

    @FXML
    TilePane tilePane1, tilePane2, tilePane3;
    Boolean modelShow = false;
    Object lastSelectedButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent p;
        try {
            p = FXMLLoader.load(getClass().getResource("/view/ModelScreen.fxml"));
            modelLikePane.getChildren().addAll(p.getChildrenUnmodifiable());
        } catch (IOException ex) {
            Logger.getLogger(StockScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // TODO
        modelBg.setDisable(true);
        modelLikePane.setDisable(true);
        modelBg.setVisible(false);
        modelLikePane.setVisible(false);
    }

    @FXML
    public void sendBack(MouseEvent event) throws IOException {
        
        
//        modelBg.setDisable(true);
        modelLikePane.setDisable(true);
        
        modelBg.setVisible(false);
        modelLikePane.setVisible(false);

        modelLikePane.toBack();
        modelBg.toBack();

        modelShow = false;
        tilePane1.setDisable(false);
        tilePane2.setDisable(false);
        tilePane3.setDisable(false);

//        gridPane1.setDisable(false);
//        gridPane2.setDisable(false);
    }

    @FXML
    public void openProp(ActionEvent event) throws IOException {
        
        lastSelectedButton = event.getSource();
        if (tilePane1.getChildren().contains(lastSelectedButton)) {
            tilePane1.getChildren().remove(lastSelectedButton);
        }
        if (tilePane2.getChildren().contains(lastSelectedButton)) {
            tilePane2.getChildren().remove(lastSelectedButton);
        }
        if (tilePane3.getChildren().contains(lastSelectedButton)) {
            tilePane3.getChildren().remove(lastSelectedButton);
        }
        if (modelShow == false) {

            modelBg.setVisible(true);
            modelLikePane.setVisible(true);

            tilePane1.setDisable(true);
            tilePane2.setDisable(true);
            tilePane3.setDisable(true);
//            gridPane1.setDisable(true);
//            gridPane2.setDisable(false);
//            modelBg.setDisable(false);
            modelLikePane.setDisable(false);

            modelLikePane.toFront();
            modelBg.toFront();

            modelShow = true;

        }

    }

}
