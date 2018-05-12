/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DOA.UserDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import enity.User;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
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
    JFXButton btnFullScreen, confirmAdminBtn, confirmUserBtn, newAdminBtn, newUserBtn;
    @FXML
    Pane akai, tori;
    @FXML
    VBox upVbox, downVbox;
    @FXML
    ImageView stocksImg, sellImg;
    @FXML
    Label adminLab, userLab;
    @FXML
    JFXTextField tfIdAdmin, tfIdUser;
    @FXML
    JFXPasswordField pfPwdAdmin, pfPwdUser;
    boolean openStat = false, loginA = true, loginN = true;
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

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ev -> {
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
                upVbox.setLayoutX(606);
                upVbox.setLayoutY(182);

                downVbox.setLayoutX(355);
                downVbox.setLayoutY(353);

                upVbox.setPrefSize(240, 150);
                downVbox.setPrefSize(240, 150);
                //********************************************
            }

        });
        adminLab.setText("Admin Login");
        userLab.setText(("User login"));
        List<User> l = UserDAO.getAdmin('A');
        List<User> l2 = UserDAO.getAdmin('N');
        if (l.isEmpty()) {
            newAdminBtn.setText("New?");
        } else {
            newAdminBtn.setText(" ");
            newAdminBtn.setDisable(true);
        }
        if (l2.isEmpty()) {
            newUserBtn.setText("New?");
        } else {
            newUserBtn.setText(" ");
            newUserBtn.setDisable(true);
        }

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
            toStock();
        }

    }

    @FXML
    public void toStock() throws IOException {
        FXMLLoader stockLoader = new FXMLLoader(getClass().getResource("/view/StockScreen.fxml"));
        stockLoader.setController(new StockScreenController(fm));
        Parent root = stockLoader.load();
        MiniProject.stage.setScene(new Scene(root));
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

    @FXML
    public void newAdmin(ActionEvent event) {
        if (loginA) {
            tfIdAdmin.setText(null);
            pfPwdAdmin.setText(null);
            adminLab.setText("New Admin!");
            newAdminBtn.setText("Cancel");
            confirmAdminBtn.setText("Save");
            loginA = false;
        } else {
            adminLab.setText("Admin login");
            newAdminBtn.setText("New?");
            confirmAdminBtn.setText("Confirm");
            loginA = true;
        }
    }

    @FXML
    public void newUser(ActionEvent event) {
        if (loginN) {
            tfIdUser.setText(null);
            pfPwdUser.setText(null);
            userLab.setText("New User!");
            newUserBtn.setText("Cancel");
            confirmUserBtn.setText("Save");
            loginN = false;
        } else {
            userLab.setText("User login");
            newUserBtn.setText("New?");
            confirmUserBtn.setText("Confirm");
            loginN = true;
        }
    }

    @FXML
    public void confirmAdmin(ActionEvent event) throws IOException {
        if (!loginA) {
            User u = new User(tfIdAdmin.getText(), pfPwdAdmin.getText(), 'A');
            UserDAO.addUser(u);
            newAdminBtn.setText("");
            tfIdAdmin.setText(null);
            pfPwdAdmin.setText(null);
            adminLab.setText("Admin Login");
        } else {
            try {
                User u = UserDAO.getUser(tfIdAdmin.getText(), pfPwdAdmin.getText());
                System.out.println(u.getId());
                toStock();
            } catch (NullPointerException ex) {
                adminLab.setText("Wrong Info or not saved");
                adminLab.setTextFill(Paint.valueOf("RED"));
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ev -> {
                    adminLab.setText("Admin Login");
                    adminLab.setTextFill(Paint.valueOf("Black"));
                }));
                timeline.play();
            }
        }
    }

    @FXML
    public void confirmUser(ActionEvent event) {
        if (!loginN) {
            User u = new User(tfIdUser.getText(), pfPwdUser.getText(), 'N');
            UserDAO.addUser(u);
            newUserBtn.setText("");
            tfIdUser.setText(null);
            pfPwdUser.setText(null);
            userLab.setText("User Login");
        } else {
            try {
                User u = UserDAO.getUser(tfIdUser.getText(), pfPwdUser.getText());
                System.out.println(u.getId());

            } catch (NullPointerException ex) {
                userLab.setText("Wrong Info or not saved");
                userLab.setTextFill(Paint.valueOf("RED"));
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ev -> {
                    userLab.setText("User Login");
                    userLab.setTextFill(Paint.valueOf("Black"));
                }));
                timeline.play();
            }
        }
    }
}
