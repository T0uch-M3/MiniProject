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
import javafx.scene.image.ImageView;
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
    @FXML
    ImageView stocksImg, sellImg;
    boolean openStat = false;
    public static boolean fullScreen = false;
    double angle;
    Double lastX, lastY;
    private final FirstModel fm;

    /**
     * Initializes the controller class.
     */
    public NewLoginController(FirstModel fm) {
        this.fm = fm;
    }

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
            //********************Image view*********************
            resizingIVUp(true);
        } else {
            MiniProject.stage.setFullScreen(false);
            fullScreen = false;
            resizingIVUp(false);

        }
    }

    public void resizingIVUp(boolean bool) {
        if (bool) {
            sellImg.setLayoutX(910);
            sellImg.setLayoutY(-320);
            sellImg.setFitWidth(400);
            sellImg.setFitHeight(900);

            stocksImg.setLayoutX(330);
            stocksImg.setLayoutY(510);
            stocksImg.setFitWidth(400);
            stocksImg.setFitHeight(900);
        } else {
            sellImg.setLayoutX(639);
            sellImg.setLayoutY(-206);
            sellImg.setFitWidth(250);
            sellImg.setFitHeight(600);

            stocksImg.setLayoutX(227);
            stocksImg.setLayoutY(330);
            stocksImg.setFitWidth(250);
            stocksImg.setFitHeight(600);
        }
    }

    @FXML
    public void goToStock(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            FXMLLoader stockLoader = new FXMLLoader(getClass().getResource("/view/StockScreen.fxml"));
            stockLoader.setController(new StockScreenController(fm));
            Parent root = stockLoader.load();
            MiniProject.stage.setScene(new Scene(root));
        }

    }

    @FXML
    public void goToSell(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            FXMLLoader sellLoader = new FXMLLoader(getClass().getResource("/view/SellScreen.fxml"));
            sellLoader.setController(new SellScreenController(fm));
            Parent root = sellLoader.load();
            MiniProject.stage.setScene(new Scene(root));
        }

    }

    @FXML
    public void cord(MouseEvent event) {

//        System.out.println("fuck it");
    }
}
