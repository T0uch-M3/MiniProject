/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DOA.MenDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import enity.Men;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
    VBox modelLikePane, addPane;
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
    JFXButton btnFullScreen, dropDownBtn, stockBtn, btnIncrease, btnDecrease, btnClone;
    @FXML
    HBox initialPos, initialPosFSBtn, topHbox;
    @FXML
    Label qte, addQte;
    @FXML
    public JFXButton testLabel;//the language button
    @FXML
    JFXTextField tfName, tfPrice;
    @FXML
    JFXComboBox<String> cbColor;
    @FXML
    JFXComboBox<String> cbPeriod;
    @FXML
    ImageView shoesPicture;
    boolean modelShow = false, stayPut = false, add = false;
    boolean dropDownMenu = false;
    public Object lastSelectedButton;

    private final FirstModel fm;

    /**
     * Initializes the controller class.
     */
    public StockScreenController(FirstModel fm) {
        this.fm = fm;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("file:/C:/Users/adolf/OneDrive/Documents/NetBeansProjects/MiniProject/src/image/icons8_Women%60s_Shoe_64px.png");
        shoesPicture.setImage(img);
        stockBtn.requestFocus();//just for having more control over things

        //this for options drawer values
        fm.textProperty().addListener((obs, oldText, newText)
                -> testLabel.setText(newText));
        //the less u see the longer u live
        optionDrawer.setVisible(false);
        //u can't live blind forever
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(400), ev -> {
            optionDrawer.setVisible(true);
        }));
        timeline.play();

        Parent p;

        fillComboBox();//fill the two comboboxes

        // TODO
        modelBg.setDisable(true);
        modelLikePane.setDisable(true);
        modelBg.setVisible(false);
        modelLikePane.setVisible(false);

        try {
            FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/view/OptionDropDown.fxml"));
            secondLoader.setController(new OptionDropDownController(fm));
            optionVB = secondLoader.load();
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

    public void fillComboBox() {
        cbColor.getItems().addAll("Dark", "Bright", "Unusual", "Unspecified");

        cbPeriod.getItems().addAll("Summer", "Winter", "Seasonal");
    }

    @FXML
    public void sendBackAction(MouseEvent event) throws IOException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), av -> {
            if (stayPut) {
                event.consume();
                stayPut = false;
            } else {
                sendBack();
                if (add) {
                    addBack();
                }
            }
        }));
        timeline.play();
    }

    public void addBack() {
        modelLikePane.setDisable(true);

        modelBg.setVisible(false);
        addPane.setVisible(false);

        addPane.toBack();
        modelBg.toBack();

        modelShow = false;
        tilePane1.setDisable(false);
        tilePane2.setDisable(false);
        tilePane3.setDisable(false);
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
//        modelLikePane.getChildren().remove(lastSelectedButton);
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
        add = true;
        //***********the same old codes zzz********************
        if (modelShow == false) {

            modelBg.setVisible(true);
            addPane.setVisible(true);
            addPane.toFront();

            tilePane1.setDisable(true);
            tilePane2.setDisable(true);
            tilePane3.setDisable(true);

            modelBg.setDisable(false);

            modelBg.toFront();

            modelShow = true;
        }

//        System.out.println("aaaa");
//        JFXButton men = new JFXButton("new btn");
//        Image img = new Image("/image/picV1.png");
//        ImageView imv = new ImageView(img);
//        imv.setFitWidth(94);
//        imv.setFitHeight(65);
//        men.getChildrenUnmodifiable().
//        tilePane2.getChildren().add(men);
//        tilePane2.getChildren().add(imv);
//        men.setGraphic(imv);
//        men.setContentDisplay(ContentDisplay.TOP);
//        tilePane2.getChildren().add(men);
    }

    @FXML
    public void addWomen(ActionEvent event) {

    }

    @FXML
    public void addKids(ActionEvent event) {

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
        JFXButton btnReal = new JFXButton();

        JFXButton btnSelected = (JFXButton) lastSelectedButton;
        btnReal = btnSelected;
        btnClone.graphicProperty().set(btnReal.getGraphic());
        btnClone.setContentDisplay(ContentDisplay.TOP);
        btnClone.setText(btnReal.getText());
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
//        modelLikePane.getChildren().add(btnClone);
//        btnClone.toBack();
//        btnClone.setStyle("-fx-background-color:red");

    }

    @FXML
    public void openOption(ActionEvent event) {
        //because of some code limitation, i can't move a node forward by one step, so i had to switsh between different nodes and bring each of them forward/backward alone

        if (dropDownMenu == false) {
            stockBorderPane.toBack();
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
    public void goToStats(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/StatisticScreen.fxml"));
        MiniProject.stage.setScene(new Scene(root));
    }

    @FXML
    public void increaseAction(ActionEvent event) {
        Integer i = Integer.parseInt(qte.getText().toString());
        i = i + 100;
        qte.setText(String.valueOf(i));
    }

    @FXML
    public void decreaseAction(ActionEvent event) {
        Integer i = Integer.parseInt(qte.getText());
        if (i > 0) {
            i = i - 100;
        }
        qte.setText(String.valueOf(i));
    }

    @FXML
    public void addMAneDB(ActionEvent event) {
        System.err.println("add to database");
    }

    @FXML
    public void increaseAdd(ActionEvent event) {
        Integer i = Integer.parseInt(addQte.getText().toString());
        i = i + 100;
        addQte.setText(String.valueOf(i));
    }

    @FXML
    public void decreaseAdd(ActionEvent event) {
        Integer i = Integer.parseInt(addQte.getText());
        if (i > 0) {
            i = i - 100;
        }
        addQte.setText(String.valueOf(i));
    }

    @FXML
    public void stayPut(MouseEvent event) {
        //this to disable leaving the modal when pressing inside
        stayPut = true;
    }

    @FXML
    public void addMenDB(ActionEvent event) {
        Men m = new Men(tfName.getText(), Integer.valueOf(addQte.getText()), Float.valueOf(tfPrice.getText()), cbColor.getValue().toString(), cbPeriod.getValue().toString(), "url");
        MenDAO.addMen(m);
    }

    @FXML
    public void addPicture(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Shoes Picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        File selectedFileInput = selectedFile;

        if(selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString(),300,250,true,true,true);
            shoesPicture.setImage(image);
            String s = selectedFile.toURI().toURL().toExternalForm();
            System.out.println(s);
//            selectedFileOutput.setText("File selected: " + selectedFile.getName());
//            previewPicture.setImage();
        } else {
//            selectedFileOutput.setText("Please select a profile picture...");
        }
    }
}
