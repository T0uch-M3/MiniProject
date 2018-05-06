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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.MiniProject;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class StockScreenController implements Initializable {

    @FXML
    BorderPane stockBorderPane;
    @FXML
    GridPane gridPane, gridPane2, modelBg;
    @FXML
    VBox modelLikePane;
    @FXML
    public TilePane tilePane1, tilePane2, tilePane3;
    @FXML
    public JFXDrawer optionDrawer;
    @FXML
    VBox optionVB;
    @FXML
    CheckBox cbTest;
    @FXML
    Pane dropDownBg;
    @FXML
    JFXButton btnFullScreen, dropDownBtn;
    @FXML
    HBox initialPos, initialPosFSBtn, topHbox;
    boolean modelShow = false;
    boolean dropDownMenu = false;
    public Object lastSelectedButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        optionDrawer.setVisible(false);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(400), ev -> {
            optionDrawer.setVisible(true);
//            System.out.println(dropDownBtn.getTranslateX());
//            System.out.println(dropDownBtn.getLayoutY());
        }));
        timeline.play();
        Parent p;

        // TODO
        modelBg.setDisable(true);
        modelLikePane.setDisable(true);
        modelBg.setVisible(false);
        modelLikePane.setVisible(false);

//        optionDrawer.close();
        try {
            optionVB = FXMLLoader.load(getClass().getResource("/view/optionDropDown.fxml"));
            optionDrawer.setSidePane(optionVB);
            optionDrawer.open();
        } catch (IOException ex) {
            Logger.getLogger(StockScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MiniProject.stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            optionDrawer.setPrefSize(180, 42);
            optionDrawer.setMaxSize(180, 42);
            
            optionDrawer.setLayoutX(995);
            
             MiniProject.stage.setFullScreen(false);
            NewLoginController.fullScreen = false;
            resizeUp(tilePane1, false);
            resizeUp(tilePane2, false);
            resizeUp(tilePane3, false);

            Insets ins = new Insets(0, 10, 0, 0);
            initialPos.setPadding(ins);
        });
    }

    @FXML
    public void sendBackAction(MouseEvent event) throws IOException {

        sendBack();
//        modelBg.setDisable(true);

//        gridPane1.setDisable(false);
//        gridPane2.setDisable(false);
    }

    public void sendBack() {
        //*****All of this just for the main motion*****
        modelLikePane.setDisable(true);

        modelBg.setVisible(false);
        modelLikePane.setVisible(false);

        modelLikePane.toBack();
        modelBg.toBack();

        modelShow = false;
        tilePane1.setDisable(false);
        tilePane2.setDisable(false);
        tilePane3.setDisable(false);
        //************what come after**************
        modelLikePane.getChildren().remove(lastSelectedButton);
    }

    @FXML
    public void removeButton(ActionEvent event) {
        if (tilePane1.getChildren().contains(lastSelectedButton)) {
            tilePane1.getChildren().remove(lastSelectedButton);
        }
        if (tilePane2.getChildren().contains(lastSelectedButton)) {
            tilePane2.getChildren().remove(lastSelectedButton);
        }
        if (tilePane3.getChildren().contains(lastSelectedButton)) {
            tilePane3.getChildren().remove(lastSelectedButton);
        }
        sendBack();
    }

    @FXML
    public void addMen(ActionEvent event) {
        System.out.println("aaaa");
        JFXButton men = new JFXButton("new btn");
        Image img = new Image("/image/picV1.png");
        ImageView imv = new ImageView(img);
        imv.setFitWidth(94);
        imv.setFitHeight(65);
//        men.getChildrenUnmodifiable().
//        tilePane2.getChildren().add(men);
//        tilePane2.getChildren().add(imv);
        men.setGraphic(imv);
        men.setContentDisplay(ContentDisplay.TOP);
        tilePane2.getChildren().add(men);
    }

    @FXML
    public void goFullScreen(ActionEvent event) {
        if (NewLoginController.fullScreen) {
            MiniProject.stage.setFullScreen(false);
            NewLoginController.fullScreen = false;
            resizeUp(tilePane1, false);
            resizeUp(tilePane2, false);
            resizeUp(tilePane3, false);

            Insets ins = new Insets(0, 10, 0, 0);
            initialPos.setPadding(ins);
            
            optionDrawer.setPrefSize(180, 42);
            optionDrawer.setMaxSize(180, 42);
            
            optionDrawer.setLayoutX(995);

        } else {//what heppen when u go FULLSCREEN
            MiniProject.stage.setFullScreen(true);
            NewLoginController.fullScreen = true;
            resizeUp(tilePane1, true);
            resizeUp(tilePane2, true);
            resizeUp(tilePane3, true);

            Insets ins = new Insets(0, 30, 0, 0);
            initialPos.setPadding(ins);
            dropDownBtn.setLayoutX(1700);

            optionDrawer.setPrefSize(230, 90);
            optionDrawer.setMaxSize(230, 90);
            
            optionDrawer.setLayoutX(1670);
        }
    }

    @FXML
    public void sendBackOptions(MouseEvent event){
        handleHiding();
    }
    public void handleHiding(){
        optionDrawer.open();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(250), ev->{
            dropDownBg.toBack();
            
        }));
        timeline2.play();
        dropDownMenu=false;
        
    }
    public void resizeUp(TilePane tp, boolean bool) {
        if (bool) {
            tp.setPrefWidth(590);
            Insets ins = new Insets(20, 10, 0, 12);
            tp.setPadding(ins);
        } else {
            tp.setPrefWidth(350);
            Insets ins = new Insets(20, 5, 0, 6);
            tp.setPadding(ins);
        }
    }

    @FXML
    public void openProp(ActionEvent event) throws IOException {

        lastSelectedButton = event.getSource();
        JFXButton btn2 = new JFXButton();

        JFXButton btnSelected = (JFXButton) lastSelectedButton;
        btn2 = btnSelected;
//        btn2.getChildrenUnmodifiable().remove(0);
        System.out.println(btnSelected.getText());
//***********the same old codes zzz********************
        if (modelShow == false) {

            modelBg.setVisible(true);
            modelLikePane.setVisible(true);

            tilePane1.setDisable(true);
            tilePane2.setDisable(true);
            tilePane3.setDisable(true);

            modelLikePane.setDisable(false);
            modelBg.setDisable(false);

            modelLikePane.toFront();
            modelBg.toFront();

            modelShow = true;
        }
        //*********What come next******************
//        Node n = tilePane1.getChildren().get(0);
//        Node n2 = btnSelected.getClip();
        modelLikePane.getChildren().add(btnSelected);
        btnSelected.toBack();
//        n2.toBack();
//        n.setDisable(true);
    }

    @FXML
    public void openOption(ActionEvent event) {
        //because of some code limitation, i can't move a node forward by one step, so i had to switsh between different nodes and bring each of them forward/backward alone

        if(dropDownMenu==false){
            stockBorderPane.toBack();
            dropDownMenu=true;
        }
        else{
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(250), ev->{
            dropDownBg.toBack();
        }));
        timeline2.play();
        dropDownMenu=false;
        }
//        if (inOldPostion) {//pressing the button while in normal screen
//            dropDownBg.getChildren().add(dropDownBtn);
//            dropDownBtn.setLayoutX(1129);
//            inOldPostion = false;
//            dropDownBg.toFront();
//            topHbox.toFront();
//            //******the fullscreen button new position
//            dropDownBg.getChildren().add(btnFullScreen);
//            btnFullScreen.setLayoutX(1160);
//            btnFullScreen.setLayoutY(665);
//            //*******************************************
//        } else {
//            initialPos.getChildren().add(dropDownBtn);
//            inOldPostion = true;
//            dropDownBg.toBack();
//            initialPosFSBtn.getChildren().add(btnFullScreen);
//        }
//        if(NewLoginController.fullScreen){//pressing the button while on full screen
//            dropDownBtn.setLayoutX(1845);
//            btnFullScreen.setLayoutX(1880);
//            btnFullScreen.setLayoutY(1060);
//            
//            
//            
//            
//            
//            
//            
//        }
        if (optionDrawer.isHidden()) {
            optionDrawer.open();
        } else {
            optionDrawer.close();
        }
    }

}
