/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import view.MiniProject;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class LoginScreenController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    JFXDrawer drawMiddle, drawLeft, drawRight;
    @FXML
    VBox vBoxM, loginMiddle;
    @FXML
    VBox vBoxL;
    @FXML
    VBox loginLeft;
    @FXML
    VBox vBoxR, loginRight;
    @FXML
    public JFXButton btnFullScreen;
//    StackPane drawVb;
    boolean fullScreen = false;
    boolean visibleL = false;
    boolean visibleR = false;
    boolean visibleM = false;
    BoxBlur bb = new BoxBlur();
    BoxBlur bb2 = new BoxBlur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnFullScreen.setTranslateX(180);
        btnFullScreen.setTranslateY(3);
        Paint p = new Color(1, 0, 0, 1);
        btnFullScreen.setTextFill(p);
        // TODO
        try {
            vBoxM = FXMLLoader.load(getClass().getResource("/view/MiddleDrawer.fxml"));
            drawMiddle.setSidePane(vBoxM);
            vBoxL = FXMLLoader.load(getClass().getResource("/view/LeftDrawer.fxml"));
            drawLeft.setSidePane(vBoxL);
            vBoxR = FXMLLoader.load(getClass().getResource("/view/RightDrawer.fxml"));
            drawRight.setSidePane(vBoxR);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        MiniProject.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (MiniProject.stage.widthProperty().doubleValue() != MiniProject.screenWidth) {
                MiniProject.stage.setFullScreen(false);
                fullScreen = false;
                drawerState(drawLeft);
                drawerState(drawRight);
                drawerState(drawMiddle);
            }
        });
    }

    @FXML
    public void drawerStatL(MouseEvent event) {

        visibleL = hideShowDrawer(drawLeft);
    }

    @FXML
    public void drawerStatR(MouseEvent event) {
        visibleR = hideShowDrawer(drawRight);
    }

    @FXML
    public void drawerStatM(MouseEvent event) {
        visibleM = hideShowDrawer(drawMiddle);
    }

    public boolean hideShowDrawer(JFXDrawer drawer) {
        Timeline timeline11 = new Timeline(new KeyFrame(Duration.millis(400), ae -> {
            loginLeft.setStyle("-fx-background-image:url(\"/image/advance2Effect.png\")");
        }));
        Timeline timeline12 = new Timeline(new KeyFrame(Duration.millis(400), ae -> {
            loginLeft.setStyle("-fx-background-image:url(\"/image/advance2.png\")");
        }));
        Timeline timeline21 = new Timeline(new KeyFrame(Duration.millis(400), ae -> {
            loginMiddle.setStyle("-fx-background-image:url(\"/image/sell2.png\")");
        }));
        Timeline timeline22 = new Timeline(new KeyFrame(Duration.millis(400), ae -> {
            loginMiddle.setStyle("-fx-background-image:url(\"/image/sell2Effect.png\")");
        }));
        Timeline timeline31 = new Timeline(new KeyFrame(Duration.millis(400), ae -> {
            loginRight.setStyle("-fx-background-image:url(\"/image/stocks2.png\")");
        }));
        Timeline timeline32 = new Timeline(new KeyFrame(Duration.millis(400), ae -> {
            loginRight.setStyle("-fx-background-image:url(\"/image/stocks2Effect.png\")");
        }));
        if (drawer.isHidden()) {
            drawer.open();
            if (drawer.equals(drawLeft)) {
                timeline11.play();
                
            }
            if (drawer.equals(drawMiddle)) {
                timeline22.play();
            }
            if(drawer.equals(drawRight))
                timeline32.play();
            return true;
        } else {
            drawer.close();
            if (drawer.equals(drawLeft)) {
                timeline12.play();
            }
            if (drawer.equals(drawMiddle)) {
                timeline21.play();
            }
            if(drawer.equals(drawRight))
                timeline31.play();
            return false;
        }
    }

    //*********************************************************************
    public void drawerState(JFXDrawer drawer) {
        if (fullScreen == true) {
            drawer.setPrefHeight(681);
            drawer.setMinHeight(681);
            drawer.setMinHeight(681);
//            drawer.setDefaultDrawerSize(900);
            if (visibleL) {
                drawLeft.open();
            }
            if (visibleR) {
                drawRight.open();
            }
            if (visibleM) {
                drawMiddle.open();
            }
        }
        if (fullScreen == false) {
            drawer.setPrefHeight(421);
            drawer.setMinHeight(421);
            drawer.setMinHeight(421);
//            drawer.setDefaultDrawerSize(700);
            if (visibleL) {
                drawLeft.open();
            }
            if (visibleR) {
                drawRight.open();
            }
            if (visibleM) {
                drawMiddle.open();
            }
        }
    }

    public void colorMeth() {

//        drawVb.setStyle("-fx-background-color: rgba(0,0,0,0)");
//        drawVb.setBackground(Background.EMPTY);
//        drawMiddle.setStyle("-fx-background-color: rgba(0,100,100,0.8)");
    }

    @FXML
    public void goFullScreen(ActionEvent event) {

        if (!fullScreen) {
            btnFullScreen.setTranslateX(300);
            btnFullScreen.setTranslateY(60);
            MiniProject.stage.setFullScreen(true);
            fullScreen = true;
            drawerState(drawLeft);
            drawerState(drawRight);
            drawerState(drawMiddle);
        } else {
            btnFullScreen.setTranslateX(180);
            btnFullScreen.setTranslateY(3);
            MiniProject.stage.setFullScreen(false);
            fullScreen = false;
            drawerState(drawLeft);
            drawerState(drawRight);
            drawerState(drawMiddle);
        }
//        drawMiddle.setStyle(value);
    }

    @FXML
    public void drawClick(MouseEvent event) {
        if (drawMiddle.isHidden()) {
            drawMiddle.open();
        } else {
            drawMiddle.close();
        }

    }

}
