/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DOA.MenDAO;
import DOA.StatDAO;
import DOA.UserDAO;
import DOA.WomenDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import static controller.NewLoginController.fullScreen;
import enity.Men;
import enity.Stat;
import enity.Women;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
public class SellScreenController implements Initializable {

    private final FirstModel fm;
    /**
     * Initializes the controller class.
     */
    @FXML
    BorderPane sellBorderPane;
    @FXML
    GridPane modelBg;
    @FXML
    public JFXDrawer optionDrawer;
    boolean dropDownMenu = false;
    @FXML
    JFXButton sellBtn;
    @FXML
    Pane dropDownBg;
    
    @FXML
    ImageView sellImv;
    ObservableList<Men> shoesList = FXCollections.observableArrayList();
    ObservableList<Women> shoesList2 = FXCollections.observableArrayList();
    ObservableList<Men> shoesList3 = FXCollections.observableArrayList();
    ObservableList<Women> shoesList4 = FXCollections.observableArrayList();
    ObservableList<Men> shoesList5 = FXCollections.observableArrayList();
    ObservableList<Women> shoesList6 = FXCollections.observableArrayList();

    ObservableList<Object> test = FXCollections.observableArrayList();
    @FXML
    TableView sellTable;
    @FXML
    VBox vBox1, vBox2, vBox3, vBox4, optionVB, modelLikePane;
    @FXML
    HBox initialPos;
    @FXML
    JFXTextField priceTf, searchTf;
    @FXML
    JFXPasswordField oldPwd, newPwd, repeatPwd;
    @FXML
    TableColumn<String, String> nameCol;
    @FXML
    TableColumn<String, Integer> qteCol;
    @FXML
    TableColumn<String, Float> priceCol;
    @FXML
    JFXRadioButton menRb, womenRb, kidsRb, boysRb, girlsRb, summerRb, winterRb, seasonalRb, darkRb, brightRb, unusualRb, unspecifiedRb;
    @FXML
    Label availableQte, neededQte;
    @FXML
    JFXButton dropDownBtn;
    @FXML
    ToggleGroup tg1 = new ToggleGroup();
    ToggleGroup tg2 = new ToggleGroup();
    ToggleGroup tg3 = new ToggleGroup();
    ToggleGroup tg4 = new ToggleGroup();
    JFXRadioButton clone = null;
    boolean mbool = false, wBool = false, kBool = false, modelShow = false, stayPut = false;
    Men m = new Men();//this should contain the current selected instancem.
    Women w = new Women();
    Image sellImg = null;
    int nQte = 0;
    int nbrUnit = 0;
    float selectedPrice = 0;

    public SellScreenController(FirstModel fm) {
        this.fm = fm;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (NewLoginController.fullScreen) {
            fullScreenPart2();
        }

//        fullScreen(true);
        fm.statProperty().addListener((obs, oldStat, newStat) -> {
            if (newStat) {
                changePwdAction();
            } else {
                sendBack();
            }
        });

        vBox2.setDisable(true);
        vBox3.setDisable(true);
        vBox4.setDisable(true);

        sellImg = new Image("/image/add-image.png");
        sellImv.setImage(sellImg);

        sellBorderPane.requestFocus();
        sellBtn.setFocusTraversable(true);
        sellBtn.requestFocus();
//        menRb.setFocusTraversable(false);
        // TODO
        menRb.setToggleGroup(tg1);
        womenRb.setToggleGroup(tg1);
        kidsRb.setToggleGroup(tg1);

        boysRb.setToggleGroup(tg2);
        girlsRb.setToggleGroup(tg2);

        summerRb.setToggleGroup(tg3);
        winterRb.setToggleGroup(tg3);
        seasonalRb.setToggleGroup(tg3);

        darkRb.setToggleGroup(tg4);
        brightRb.setToggleGroup(tg4);
        unspecifiedRb.setToggleGroup(tg4);
        unusualRb.setToggleGroup(tg4);

        shoesList2.addAll(WomenDAO.listWomen());

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("qte"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        sellTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            neededQte.setText("0");
            priceTf.setText("0");
            nbrUnit = 0;
            if (mbool) {
                m = (Men) newValue;
            }
            if (wBool) {
                w = (Women) newValue;
            }
            selectionMeth();

        });
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
            if (NewLoginController.fullScreen == true) {
                MiniProject.stage.setFullScreen(false);
                fullScreen(false);
            }
        });

    }

    @FXML
    public void openOption(ActionEvent event) {
        //because of some code limitation, i can't move a node forward by one step, so i had to switsh between different nodes and bring each of them forward/backward alone

        if (dropDownMenu == false) {
            sellBorderPane.toBack();
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
    public void filterSelection(ActionEvent event) {
        clone = (JFXRadioButton) event.getSource();
        startFilter();
    }

    public void startFilter() {

        if (menRb.isSelected()) {
            mbool = true;
            wBool = false;
            kBool = false;
            shoesList.clear();
            shoesList.addAll(MenDAO.listMen());
            unselectRb(clone.getText());
            sellTable.setItems(shoesList);

            if (summerRb.isSelected()) {
                shoesList3.clear();

                for (Men m : shoesList) {
                    if (m.getPeriod().equals("Summer")) {
                        shoesList3.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList3);
                //*************summer start********
                if (darkRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Dark")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (brightRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Bright")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unusualRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                //************summer end************
            }
            if (winterRb.isSelected()) {
                shoesList3.clear();

                for (Men m : shoesList) {
                    if (m.getPeriod().equals("Winter")) {
                        shoesList3.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList3);

                //***************inside Winter******************
                if (darkRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Dark")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (brightRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Bright")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unusualRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                //************end winter****************

            }
            if (seasonalRb.isSelected()) {
                shoesList3.clear();

                for (Men m : shoesList) {
                    if (m.getPeriod().equals("Seasonal")) {
                        shoesList3.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems((shoesList3));

                //***********Seasonal start*************
                if (darkRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Dark")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (brightRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Bright")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unusualRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList5.clear();

                    for (Men m : shoesList3) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList5.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList5);
                }
                //*************seasonal end*********************************
            }
        }
        if (womenRb.isSelected()) {
            wBool = true;
            mbool = false;
            kBool = false;
            shoesList2.clear();
            shoesList2.addAll(WomenDAO.listWomen());
            unselectRb(clone.getText());
            sellTable.setItems(shoesList2);

            if (summerRb.isSelected()) {
                shoesList4.clear();

                for (Women m : shoesList2) {
                    if (m.getPeriod().equals("Summer")) {
                        shoesList4.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList4);

                //***********Summer start*********
                if (darkRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Dark")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (brightRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Bright")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unusualRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }

                //*******Summer End*************************
            }
            if (winterRb.isSelected()) {
                shoesList4.clear();

                for (Women m : shoesList2) {
                    if (m.getPeriod().equals("Winter")) {
                        shoesList4.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList4);
                //***********winter start********************
                if (darkRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Dark")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (brightRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Bright")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unusualRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                //************Winter end*****************

            }
            if (seasonalRb.isSelected()) {
                shoesList4.clear();

                for (Women m : shoesList2) {
                    if (m.getPeriod().equals("Seasonal")) {
                        shoesList4.add(m);
                    }
                }
                unselectRb(clone.getText());
                sellTable.setItems(shoesList4);

                //****************Seasonal start********
                if (darkRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Dark")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (brightRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Bright")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unusualRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unusual")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                if (unspecifiedRb.isSelected()) {
                    shoesList6.clear();

                    for (Women m : shoesList4) {
                        if (m.getColor().equals("Unspecified")) {
                            shoesList6.add(m);
                        }
                    }
                    unselectRb(clone.getText());
                    sellTable.setItems(shoesList6);
                }
                //***************Seasonal End************

            }
        }
    }

    public void unselectRb(String text) {
        if (text.equals("Men") || text.equals("Women")) {
            vBox3.setDisable(false);
            vBox4.setDisable(true);

            boysRb.setSelected(false);
            girlsRb.setSelected(false);
            summerRb.setSelected(false);
            winterRb.setSelected(false);
            seasonalRb.setSelected(false);
            brightRb.setSelected(false);
            darkRb.setSelected(false);
            unspecifiedRb.setSelected(false);
            unusualRb.setSelected(false);
        }
        if (text.equals("Boys") || text.equals("Girls")) {
            summerRb.setSelected(false);
            winterRb.setSelected(false);
            seasonalRb.setSelected(false);
            brightRb.setSelected(false);
            darkRb.setSelected(false);
            unspecifiedRb.setSelected(false);
            unusualRb.setSelected(false);
        }
        if (text.equals("Summer") || text.equals("Winter") || text.equals("Seasonal")) {
            vBox4.setDisable(false);

            brightRb.setSelected(false);
            darkRb.setSelected(false);
            unspecifiedRb.setSelected(false);
            unusualRb.setSelected(false);
        }
    }

    public void selectionMeth() {
        System.out.println("test selection started");
        if (mbool && m != null) {
            sellImg = new Image(m.getPictureUrl());
            sellImv.setImage(sellImg);
            availableQte.setText(String.valueOf(m.getQte()));
            selectedPrice = m.getPrice();
            priceTf.setText(String.valueOf(m.getPrice()));
        }
        if (wBool && w != null) {
            sellImg = new Image(w.getPictureUrl());
            sellImv.setImage(sellImg);
            availableQte.setText(String.valueOf(w.getQte()));
            selectedPrice = w.getPrice();
            priceTf.setText(String.valueOf(w.getPrice()));
        }
    }

    @FXML
    public void increaseQte(ActionEvent event) {
        if (Integer.valueOf(availableQte.getText()) > nQte) {
            nQte = nQte + 10;
            nbrUnit = nbrUnit + 1;
            neededQte.setText(String.valueOf(nQte));
            priceTf.setText(String.valueOf(selectedPrice * nbrUnit));
        }
    }

    @FXML
    public void decreaseQte(ActionEvent event) {
        if (0 < nQte) {
            nQte = nQte - 10;
            nbrUnit = nbrUnit - 1;
            neededQte.setText(String.valueOf(nQte));
            priceTf.setText(String.valueOf(selectedPrice * nbrUnit));
        }
    }

    @FXML
    public void searchActionK(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            searchResult();
        }
    }

    @FXML
    public void searchActionM(ActionEvent event) {
        searchResult();
    }

    public void searchResult() {
        Women w2 = WomenDAO.getWomen(searchTf.getText());
        Men m2 = MenDAO.getMen(searchTf.getText());
        if (w2 != null) {
            wBool = true;
            mbool = false;
            kBool = false;
            shoesList2.clear();
            shoesList2.add(w2);
            sellTable.setItems(shoesList2);
        }
        if (m2 != null) {
            mbool = true;
            wBool = false;
            kBool = false;
            shoesList.clear();
            shoesList.add(m2);
            sellTable.setItems(shoesList);
        }
    }

    public void changePwdAction() {

        if (modelShow == false) {

            modelBg.setVisible(true);
            modelLikePane.setVisible(true);

            modelLikePane.setDisable(false);
            modelBg.setDisable(false);

            modelLikePane.toFront();
            modelBg.toFront();

            modelShow = true;
        }
    }

    @FXML
    public void stayPut(MouseEvent event) {
        //this to disable leaving the modal when pressing inside
        stayPut = true;
    }

    @FXML
    public void sendBackAction(MouseEvent event) throws IOException {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), av -> {
            if (stayPut) {
                event.consume();
                stayPut = false;
            } else {
                sendBack();
                fm.setStat(false);
            }
        }));
        timeline.play();
    }

    public void sendBack() {
        initPwd();
        //*****All of this just for the main motion*****
        modelLikePane.setDisable(true);

        modelBg.setVisible(false);
        modelLikePane.setVisible(false);

        modelLikePane.toBack();
        modelBg.toBack();

        modelShow = false;
    }

    public void initPwd() {
        oldPwd.setText(null);
        newPwd.setText(null);
        repeatPwd.setText(null);
    }

    @FXML
    public void savePwd(ActionEvent event) {
        if (newPwd.getText().equals(repeatPwd.getText()) && oldPwd.getText().equals(NewLoginController.lastPwd)) {
            UserDAO.changePwd(NewLoginController.lastId, newPwd.getText());
            initPwd();
            sendBack();
            fm.setStat(false);
        } else {
            if (!newPwd.getText().equals(repeatPwd.getText())) {
                System.out.println("not the same ");
            }
        }

    }

    @FXML
    public void cancelAction(ActionEvent event) {
        initPwd();
        sendBack();
        fm.setStat(false);
    }

    @FXML
    public void goFullScreen(ActionEvent event) {
        fullScreen(false);
    }
   
    public void fullScreenPart2() {
        Insets ins = new Insets(0, 30, 0, 0);
        initialPos.setPadding(ins);
        dropDownBtn.setLayoutX(1700);

        optionDrawer.setPrefSize(230, 90);
        optionDrawer.setMaxSize(230, 90);

        optionDrawer.setLayoutX(1670);

     
        sellTable.setPrefHeight(800);
        nameCol.setPrefWidth(313);
        qteCol.setPrefWidth(313);
        priceCol.setPrefWidth(313);
        sellImv.setFitHeight(330);
        sellImv.setFitWidth(370);
        //******addPane*************
        Insets insAdd = new Insets(150, 25, 180, 25);

    }

    public void fullScreen(boolean old) {//old value will indicate when it was fullscreen before or no
        if (!old) {
            if (NewLoginController.fullScreen) {
                MiniProject.stage.setFullScreen(false);
                NewLoginController.fullScreen = false;

                Insets ins = new Insets(0, 10, 0, 0);
                initialPos.setPadding(ins);

                optionDrawer.setPrefSize(180, 42);
                optionDrawer.setMaxSize(180, 42);

                optionDrawer.setLayoutX(995);

                sellTable.setPrefHeight(461);
                nameCol.setPrefWidth(200);
                qteCol.setPrefWidth(190);
                priceCol.setPrefWidth(224);
                sellImv.setFitHeight(166);
                sellImv.setFitWidth(259);

            } else {//what heppen when u go FULLSCREEN
                MiniProject.stage.setFullScreen(true);
                NewLoginController.fullScreen = true;

                Insets ins = new Insets(0, 30, 0, 0);
                initialPos.setPadding(ins);
                dropDownBtn.setLayoutX(1700);

                optionDrawer.setPrefSize(230, 90);
                optionDrawer.setMaxSize(230, 90);

                optionDrawer.setLayoutX(1670);

                sellTable.setPrefHeight(800);
                nameCol.setPrefWidth(313);
                qteCol.setPrefWidth(313);
                priceCol.setPrefWidth(313);
                sellImv.setFitHeight(330);
                sellImv.setFitWidth(370);
                //******addPane*************
                Insets insAdd = new Insets(150, 25, 180, 25);
            }
        } else {
            if (NewLoginController.fullScreen) {
                System.out.println("go full");
                MiniProject.stage.setFullScreen(true);
                Insets ins = new Insets(0, 10, 0, 0);
                initialPos.setPadding(ins);

                optionDrawer.setPrefSize(180, 42);
                optionDrawer.setMaxSize(180, 42);

                optionDrawer.setLayoutX(995);
            } else {
                MiniProject.stage.setFullScreen(false);
            }
        }
    }

    @FXML
    public void confirmSell(ActionEvent event) {
        int result, newQte;
        Stat s = null;
        Stat s2;
        if (mbool) {
            try {
                s = StatDAO.getStat(m.getName());
                updateStat();
                newQte = s.getQte() + Integer.valueOf(neededQte.getText());
                StatDAO.updateStat(m.getName(), newQte);

            } catch (NullPointerException ex) {
                s2 = new Stat(m.getName(), Integer.valueOf(neededQte.getText()));
                StatDAO.addStat(s2);
            }

            result = m.getQte() - Integer.valueOf(neededQte.getText());
            MenDAO.saveChange(m.getName(), result, Float.valueOf(m.getPrice()));
            startFilter();
        }

        if (wBool) {
            try {
                s = StatDAO.getStat(w.getName());
                updateStat();
                newQte = s.getQte() + Integer.valueOf(neededQte.getText());
                StatDAO.updateStat(w.getName(), newQte);

            } catch (NullPointerException ex) {
                s2 = new Stat(w.getName(), Integer.valueOf(neededQte.getText()));
                StatDAO.addStat(s2);
            }
            result = w.getQte() - Integer.valueOf(neededQte.getText());
            WomenDAO.saveChange(w.getName(), result, Float.valueOf(w.getPrice()));
            startFilter();
        }
        initSell();
    }

    public void initSell() {
        sellImg = new Image("/image/add-image.png");
        sellImv.setImage(sellImg);
        nQte = 0;
        neededQte.setText("0");
        availableQte.setText("0");
        priceTf.setText("0");
    }

    @FXML
    public void goToStat(ActionEvent event) throws IOException {
        FXMLLoader statLoader = new FXMLLoader(getClass().getResource("/view/StatisticScreen.fxml"));
        statLoader.setController(new StatisticScreenController(fm));
        Parent root = statLoader.load();
        MiniProject.stage.getScene().setRoot(root);
    }

    public void updateStat() {
        Stat s = null;
        if (wBool) {
            s = StatDAO.getStat("Women");
            StatDAO.updateStat("Women", s.getQte() + 1);
        }
        if (mbool) {
            System.out.println("inside men");
            s = StatDAO.getStat("Men");
            StatDAO.updateStat("Men", s.getQte() + 1);
        }
    }

}
