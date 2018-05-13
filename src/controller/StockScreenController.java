/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DOA.MenDAO;
import DOA.WomenDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import enity.Kids;
import enity.Men;
import enity.Women;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
    GridPane gridPane, gridPane2, modelBg, insideAdd;
    @FXML
    VBox modelLikePane, addPane;
    @FXML
    public TilePane tilePane1, tilePane2, tilePane3;
    @FXML
    public JFXDrawer optionDrawer;
    @FXML
    VBox optionVB, addContainer;
    @FXML
    CheckBox cbTest;
    @FXML
    Pane dropDownBg;
    @FXML
    JFXButton btnFullScreen, dropDownBtn, stockBtn, btnIncrease, btnDecrease, btnClone, btnStat, btnAdvance;
    @FXML
    HBox initialPos, initialPosFSBtn, topHbox, anHbox;
    @FXML
    Label qteProp, addQte, messageLab, genderLab, menLab, womenLab, kidsLab;
    @FXML
    public JFXButton testLabel;//the language button
    @FXML
    JFXTextField tfName, tfPrice, priceProp;
    @FXML
    JFXComboBox<String> cbColor;
    @FXML
    JFXComboBox<String> cbPeriod;
    @FXML
    ImageView shoesPicture;
    boolean modelShow = false, stayPut = false, add = false, removeAction = false, saveAction = false, wBool = false, mBool = false, kBool = false;
    boolean dropDownMenu = false;
    public Object lastSelectedButton;
    String lastImgPath;
    private final FirstModel fm;
    ObservableList<Men> shoesArrayM = FXCollections.observableArrayList();
    ObservableList<Women> shoesArrayW = FXCollections.observableArrayList();
    ObservableList<Kids> shoesArrayK = FXCollections.observableArrayList();
    Men _MenDB;
    Women _WomenDB;
    Kids _KidsDB;

    /**
     * Initializes the controller class.
     */
    public StockScreenController(FirstModel fm) {
        this.fm = fm;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (NewLoginController.fullScreen) {
            fullScreenPart2();
        }
        //get shoes from db and show them as buttons
        displayShoes();
//        stockBtn.setFocusTraversable(true);
        stockBtn.requestFocus();//just for having more control over things

        //this for options drawer values
        fm.textProperty().addListener((obs, oldText, newText)
                -> {
            changeLanguage(newText);
        });
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
        addPane.setDisable(true);
        modelBg.setVisible(false);
        modelLikePane.setVisible(false);
        addPane.setVisible(false);

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
    public void changeLanguage(String lan){
        if(lan.equals("AR")){
            stockBtn.setText("مخازن");
            btnAdvance.setText("إعدادات");
            btnStat.setText("إحصائيات");
            menLab.setText("رجال");
            womenLab.setText("نساء");
            kidsLab.setText("أطفال");
        }
        if(lan.equals("EN")){
            stockBtn.setText("Stock");
            btnAdvance.setText("Advance");
            btnStat.setText("Stats");
            menLab.setText("MEM");
            womenLab.setText("WOMEN");
            kidsLab.setText("KIDS");
        }
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
        initAdd();
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
        initProp();
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
        mBool = true;
        wBool = false;
        kBool = false;
        insideAdd.setStyle("-fx-background-color: #e3e0ff ");
        genderLab.setText("M   E   N");
        addPane.setDisable(false);
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
    }

    @FXML
    public void addWomen(ActionEvent event) {
        add = true;
        wBool = true;
        mBool = false;
        kBool = false;
        insideAdd.setStyle("-fx-background-color:  #ffe0ed");
        genderLab.setText("W   O   M   E   N");
        addPane.setDisable(false);

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
    }

    @FXML
    public void addKids(ActionEvent event) {

    }

    @FXML
    public void goFullScreen(ActionEvent event) {
        handleFullScreen();
    }

    public void handleFullScreen() {
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
            //********addPane***********
            Insets insAdd = new Insets(120, 20, 120, 20);
            addPane.setPadding(insAdd);
            //******Image in addPane*****
            shoesPicture.setFitHeight(96);
            shoesPicture.setFitWidth(137);

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
            //******addPane*************
            Insets insAdd = new Insets(150, 25, 180, 25);
            addPane.setPadding(insAdd);
            addContainer.setSpacing(10);
            //******Image in addPane*****
            shoesPicture.setFitHeight(200);
            shoesPicture.setFitWidth(300);
        }
    }

    public void fullScreenPart2() {
        resizeUp(tilePane1, true);
        resizeUp(tilePane2, true);
        resizeUp(tilePane3, true);

        Insets ins = new Insets(0, 30, 0, 0);
        initialPos.setPadding(ins);
        dropDownBtn.setLayoutX(1700);

        optionDrawer.setPrefSize(230, 90);
        optionDrawer.setMaxSize(230, 90);

        optionDrawer.setLayoutX(1670);
        //******addPane*************
        Insets insAdd = new Insets(150, 25, 180, 25);
        addPane.setPadding(insAdd);
        addContainer.setSpacing(10);
        //******Image in addPane*****
        shoesPicture.setFitHeight(200);
        shoesPicture.setFitWidth(300);
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
    public void openProp(String path, String name, int quantity, float price) {
        Image img = new Image(path);
        ImageView imv = new ImageView(img);
        imv.setFitWidth(230);
        imv.setFitHeight(150);
        btnClone.setGraphic(imv);
        btnClone.setText(name);
        qteProp.setText(String.valueOf(quantity));
        priceProp.setText(String.valueOf(price));
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
        Integer i = Integer.parseInt(qteProp.getText().toString());
        i = i + 100;
        qteProp.setText(String.valueOf(i));
    }

    @FXML
    public void decreaseAction(ActionEvent event) {
        Integer i = Integer.parseInt(qteProp.getText());
        if (i > 0) {
            i = i - 100;
        }
        qteProp.setText(String.valueOf(i));
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
    public void addToDB(ActionEvent event) {
        if (mBool) {
            Men m = new Men(tfName.getText(), Integer.valueOf(addQte.getText()), Float.valueOf(tfPrice.getText()), cbColor.getValue().toString(), cbPeriod.getValue().toString(), lastImgPath);
            MenDAO.addMen(m);
            initAdd();
            displayShoes();
            addBack();
        }
        if (wBool) {
            Women w = new Women(tfName.getText(), Integer.valueOf(addQte.getText()), Float.valueOf(tfPrice.getText()), cbColor.getValue().toString(), cbPeriod.getValue().toString(), lastImgPath);
            WomenDAO.addWomen(w);
            initAdd();
            displayShoes();
            addBack();
        }
    }

    @FXML
    public void addPicture(MouseEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Shoes Picture");
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        File selectedFileInput = selectedFile;

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            shoesPicture.setImage(image);
            if (NewLoginController.fullScreen == false) {
                shoesPicture.setFitWidth(180);
            }
            lastImgPath = selectedFile.toURI().toURL().toExternalForm();
//            selectedFileOutput.setText("File selected: " + selectedFile.getName());
//            previewPicture.setImage();
        } else {
            messageLab.setText("Please select a picture");
        }
    }

    public void initAdd() {
        tfName.setText(null);
        addQte.setText("0");
        tfPrice.setText(null);
        cbColor.setValue(null);
        cbPeriod.setValue(null);
        Image imgPH = new Image("/Image/imgPlaceHolder.png");
        shoesPicture.setImage(imgPH);
        if (NewLoginController.fullScreen == false) {
            shoesPicture.setFitWidth(137);
        }
    }

    public void initProp() {
        qteProp.setText(null);
        priceProp.setText(null);
        anHbox.setVisible(false);
    }
//add ripple fill to the buttons

    public void displayShoes() {
        //Magic trick part one
        shoesArrayM.clear();
        shoesArrayW.clear();
        shoesArrayK.clear();
        tilePane1.getChildren().clear();
        tilePane2.getChildren().clear();
        tilePane3.getChildren().clear();
        shoesArrayM.addAll(MenDAO.listMen());
        for (Men m : shoesArrayM) {
            JFXButton newMen = new JFXButton();
            newMen.setText(m.getName());
            newMen.setPrefWidth(109);
            Image img = new Image(m.getPictureUrl());
            ImageView imv = new ImageView(img);
            imv.setFitWidth(92);
            imv.setFitHeight(65);
            newMen.setGraphic(imv);
            newMen.setContentDisplay(ContentDisplay.TOP);
            newMen.getStyleClass().add("buttons");
            newMen.setOnAction((ActionEvent event) -> {
                JFXButton thisBtn = (JFXButton) event.getSource();
                _MenDB = MenDAO.getMen(thisBtn.getText());
                openProp(_MenDB.getPictureUrl(), _MenDB.getName(), _MenDB.getQte(), _MenDB.getPrice());
                wBool = false;
                mBool = true;
                kBool = false;
            });
            tilePane1.getChildren().add(newMen);
        }

        shoesArrayW.addAll(WomenDAO.listWomen());
        for (Women w : shoesArrayW) {
            JFXButton newWomen = new JFXButton();
            newWomen.setText(w.getName());
            newWomen.setPrefWidth(109);
            Image img = new Image(w.getPictureUrl());
            ImageView imv = new ImageView(img);
            imv.setFitWidth(92);
            imv.setFitHeight(65);
            newWomen.setGraphic(imv);
            newWomen.setContentDisplay(ContentDisplay.TOP);
            newWomen.getStyleClass().add("buttons");
            newWomen.setOnAction((ActionEvent event) -> {
                JFXButton thisBtn = (JFXButton) event.getSource();
                _WomenDB = WomenDAO.getWomen(thisBtn.getText());
                openProp(_WomenDB.getPictureUrl(), _WomenDB.getName(), _WomenDB.getQte(), _WomenDB.getPrice());
                wBool = true;
                mBool = false;
                kBool = false;
            });
            tilePane2.getChildren().add(newWomen);
        }

    }

    @FXML
    public void saveModification(ActionEvent event) {
        saveAction = true;
        anHbox.setVisible(true);
    }

    @FXML
    public void removeFromDB(ActionEvent event) {
        removeAction = true;
        anHbox.setVisible(true);
    }

    @FXML
    public void AyeAction(ActionEvent event) {
        //warpdancer
        if (removeAction) {
            if (wBool) {
                WomenDAO.removeWomen(_WomenDB);
                sendBack();
                initProp();
                displayShoes();
                removeAction = false;
            }
            if (mBool) {
                MenDAO.removeMen(_MenDB);
                sendBack();
                initProp();
                displayShoes();
                removeAction = false;
            }
        }
        if (saveAction) {
            if (wBool) {
                WomenDAO.saveChange(_WomenDB.getName(), Integer.valueOf(qteProp.getText()), Float.valueOf(priceProp.getText()));
                sendBack();
                initProp();
                saveAction = false;
            }
            if (mBool) {
                MenDAO.saveChange(_MenDB.getName(), Integer.valueOf(qteProp.getText()), Float.valueOf(priceProp.getText()));
                sendBack();
                initProp();
                saveAction = false;
            }
        }
    }

    @FXML
    public void NayAction(ActionEvent event) {
        if (!removeAction) {
            removeAction = false;
            anHbox.setVisible(false);
        }
        if (!saveAction) {
            saveAction = false;
            anHbox.setVisible(false);
        }
    }

    //the next two meth deal with the price text field in prop tab
    @FXML
    public void unlockAction(ActionEvent event) {
        priceProp.setDisable(false);
    }

    @FXML
    public void resetAction(ActionEvent evetn) {
        if (mBool) {
            priceProp.setText(String.valueOf(_MenDB.getPrice()));
        }
        if (wBool) {
            priceProp.setText(String.valueOf(_WomenDB.getPrice()));
        }
    }
}
