/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.MiniProject;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class StatisticScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    JFXDrawer optionDrawer;
    @FXML
    JFXButton statBtn;
    @FXML
    BorderPane borderPane;
    @FXML
    VBox optionVB;
    @FXML
    HBox initialPos, initialPosFSBtn, topHbox;
    @FXML
    JFXButton btnFullScreen, dropDownBtn;
    @FXML
    Pane dropDownBg;

    boolean dropDownMenu = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        statBtn.setStyle("-fx-background-color:#262d46");
        try {
            optionVB = FXMLLoader.load(getClass().getResource("/view/optionDropDown.fxml"));
            optionDrawer.setSidePane(optionVB);
            optionDrawer.open();
        } catch (IOException ex) {
            Logger.getLogger(StockScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void goFullScreen(ActionEvent event) {
        if (NewLoginController.fullScreen) {
            MiniProject.stage.setFullScreen(false);
            NewLoginController.fullScreen = false;

            Insets ins = new Insets(0, 10, 0, 0);
            initialPos.setPadding(ins);

            optionDrawer.setPrefSize(180, 42);
            optionDrawer.setMaxSize(180, 42);

            optionDrawer.setLayoutX(995);

        } else {//what heppen when u go FULLSCREEN
            MiniProject.stage.setFullScreen(true);
            NewLoginController.fullScreen = true;

            Insets ins = new Insets(0, 30, 0, 0);
            initialPos.setPadding(ins);
            dropDownBtn.setLayoutX(1700);

            optionDrawer.setPrefSize(230, 90);
            optionDrawer.setMaxSize(230, 90);

            optionDrawer.setLayoutX(1670);
        }
    }

    @FXML
    public void sendBackOptions(MouseEvent event) {
        handleHiding();
    }

    public void handleHiding() {
        optionDrawer.open();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(250), ev -> {
            dropDownBg.toBack();

        }));
        timeline2.play();
        dropDownMenu = false;

    }

    @FXML
    public void openOption(ActionEvent event) {
        //because of some code limitation, i can't move a node forward by one step, so i had to switsh between different nodes and bring each of them forward/backward alone

        if (dropDownMenu == false) {
            borderPane.toBack();
            dropDownMenu = true;
        } else {
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(250), ev -> {
                dropDownBg.toBack();
            }));
            timeline2.play();
            dropDownMenu = false;
        }
        if (optionDrawer.isHidden()) {
            optionDrawer.open();
        } else {
            optionDrawer.close();
        }
    }

    @FXML
    public void goToStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/StockScreen.fxml"));
        MiniProject.stage.setScene(new Scene(root));
    }

}
