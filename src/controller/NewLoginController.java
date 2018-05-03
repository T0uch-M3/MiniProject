/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.MiniProject;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class NewLoginController implements Initializable {

    @FXML
    JFXDrawer upDrawer, downDrawer;
    @FXML
    JFXButton btnFullScreen;
    @FXML
    Pane akai, tori;
    @FXML
    VBox upVbox, downVbox;
    boolean openStat = false, fullScreen = false;
    double angle;
    Double lastX, lastY;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        upVbox.setVisible(false);
        downVbox.setVisible(false);

        downDrawer.setSidePane(akai);
        upDrawer.setSidePane(tori);
        upDrawer.setDefaultDrawerSize(1050);
        downDrawer.setDefaultDrawerSize(1050);
        upDrawer.open();
        downDrawer.open();

        lastX = btnFullScreen.getLayoutX();
        lastY = btnFullScreen.getLayoutY();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
            upVbox.setVisible(true);
            downVbox.setVisible(true);
        }));
        timeline.play();

        upVbox.toBack();
        downVbox.toBack();

        Double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        Double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double size = height / width;
        double DEG = 180 / Math.PI;
        angle = Math.atan(size) * DEG;
        double a, b, c;
        a = Math.atan(0.75);
        b = 180 / Math.PI;
        System.out.println(angle);
        MiniProject.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
//            if (MiniProject.stage.widthProperty().doubleValue() != MiniProject.screenWidth) {
            if (fullScreen == true) {
                MiniProject.stage.setFullScreen(false);
                fullScreen = false;
                btnFullScreen.setTranslateX(btnFullScreen.getTranslateX() - 720);
                btnFullScreen.setTranslateY(btnFullScreen.getTranslateY() - 380);

                //***************the login boxes**************
                upVbox.setLayoutX(602);
                upVbox.setLayoutY(192);

                downVbox.setLayoutX(358);
                downVbox.setLayoutY(346);

                upVbox.setPrefSize(240, 150);
                downVbox.setPrefSize(240, 150);
                //********************************************
            }

        });

    }

    @FXML
    public void startTheShow(ActionEvent event) {
        if (openStat == false) {
            upDrawer.open();
            downDrawer.open();
            openStat = true;
        } else {
            upDrawer.close();
            downDrawer.close();
            openStat = false;
        }

    }

    @FXML
    public void goFull(ActionEvent event) {
        MiniProject.stage.setFullScreen(true);
    }

    @FXML
    public void changeSize(ActionEvent event) {
    }

    @FXML
    public void upDrawerReaction(MouseEvent event) {
        if (upDrawer.isHidden()) {
            upDrawer.open();//bring it down
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ae -> upVbox.toBack()));
            timeline.play();

        } else {
            upDrawer.close();//push it up
            if (downDrawer.isHidden()) {
                downDrawer.open();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ae -> downVbox.toBack()));
                timeline.play();
            }
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> upVbox.toFront()));
            timeline.play();
        }
    }

    @FXML
    public void downDrawerReaction(MouseEvent event) {
        if (downDrawer.isShown()) {
            downDrawer.close();
            if (upDrawer.isHidden()) {
                upDrawer.open();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ae -> upVbox.toBack()));
                timeline.play();
            }
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> downVbox.toFront()));
            timeline.play();

        } else {
            downDrawer.open();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), ae -> downVbox.toBack()));
            timeline.play();
        }
    }

    @FXML
    public void goFullScreen(ActionEvent event) {

        if (!fullScreen) {
            btnFullScreen.setTranslateX(720);
            btnFullScreen.setTranslateY(380);
            MiniProject.stage.setFullScreen(true);
            fullScreen = true;
            //***************the login boxes**************
            upVbox.setLayoutX(900);
            upVbox.setLayoutY(340);

            downVbox.setLayoutX(580);
            downVbox.setLayoutY(520);

            upVbox.setPrefSize(320, 165);
            downVbox.setPrefSize(320, 165);
            //**********************************************
        } else {
            MiniProject.stage.setFullScreen(false);
            fullScreen = false;
        }
    }

    @FXML
    public void nextScreen(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Parent root = FXMLLoader.load(getClass().getResource("/view/StockScreen.fxml"));
            MiniProject.stage.setScene(new Scene(root));
        }

    }

    @FXML
    public void cord(MouseEvent event) {

//        System.out.println("fuck it");
    }
}
