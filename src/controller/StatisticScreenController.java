/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DOA.MenDAO;
import DOA.StatDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import enity.Men;
import enity.Stat;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.Label;
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

    private final FirstModel fm;
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
    Label lab1, lab2, lab3, lab4;
    @FXML
    JFXButton btnFullScreen, dropDownBtn, topPos, middlePos, lowPos, topSell1, topSell2, topSell3, topStock1, topStock2, topStock3, lowStock1, lowStock2, lowStock3, sellBtn, advanceBtn;
    @FXML
    Pane dropDownBg;
    List<Integer> qteList = new ArrayList<>();
    List<Integer> qteListRight = new ArrayList<>();

    boolean dropDownMenu = false;

    public StatisticScreenController(FirstModel fm) {
        this.fm = fm;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fm.textProperty().addListener((obs, oldText, newText)
                -> {
            changeLanguage(newText);
        });

        if (NewLoginController.fullScreen) {
            fullScreenPart2();
        }
        // TODO
        Stat s1 = StatDAO.getStat("Women");
        Stat s2 = StatDAO.getStat("Men");
        int i, i2;
        i = s1.getQte();
        i2 = s2.getQte();
        if (i > i2) {
            topPos.setText(s1.getName());
            middlePos.setText(s2.getName());
        }
        if (i < i2) {
            topPos.setText(s2.getName());
            middlePos.setText(s1.getName());
        }
        List<Stat> l = StatDAO.getListStat2();
//        List<Integer> qteList = null;

        for (Stat s : l) {
            qteList.add(s.getQte());
        }
        Collections.sort(qteList);
        Collections.reverse(qteList);
        try {
            topSell1.setText(StatDAO.getStatByQte(qteList.get(0)).getName());
            topSell2.setText(StatDAO.getStatByQte(qteList.get(1)).getName());
            topSell3.setText(StatDAO.getStatByQte(qteList.get(2)).getName());

            List<Men> stockList = MenDAO.listMenStat();
            lowStock1.setText(stockList.get(0).getName());
            lowStock2.setText(stockList.get(1).getName());
            lowStock3.setText(stockList.get(2).getName());
            Collections.reverse(stockList);
            topStock1.setText(stockList.get(0).getName());
            topStock2.setText(stockList.get(2).getName());
            topStock3.setText(stockList.get(3).getName());

            System.out.println(stockList.get(0).getQte());
            System.out.println(stockList.get(1).getQte());
            System.out.println(stockList.get(2).getQte());
            System.out.println(stockList.get(3).getQte());
        } catch (IndexOutOfBoundsException e) {

        }
        statBtn.setStyle("-fx-background-color:#262d46");
        try {
            FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/view/OptionDropDown.fxml"));
            secondLoader.setController(new OptionDropDownController(fm));
            optionVB = secondLoader.load();
            optionDrawer.setSidePane(optionVB);
            optionDrawer.open();
        } catch (IOException ex) {
            Logger.getLogger(StockScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fullScreenPart2() {
        Insets ins = new Insets(0, 30, 0, 0);
        initialPos.setPadding(ins);
        dropDownBtn.setLayoutX(1700);

        optionDrawer.setPrefSize(230, 90);
        optionDrawer.setMaxSize(230, 90);

        optionDrawer.setLayoutX(1670);
    }

    public void changeLanguage(String lan) {
        if (lan.equals("AR")) {
            sellBtn.setText("مبيعات");
            statBtn.setText("إحصائيات");
            advanceBtn.setText("إحصائيات");
            lab1.setText("الاحذية اكثر مبيعا الى هذا اليوم");
            lab2.setText("ترتيب الزبائن");
            lab3.setText("مخزونات منخفضة");
            lab4.setText("مخزونات عالية");
        }
        if (lan.equals("EN")) {
            sellBtn.setText("Sell");
            statBtn.setText("Stat");
            advanceBtn.setText("Advance");
            lab1.setText("Most selling Shoes to this date");
            lab2.setText("Highest Target seller");
            lab3.setText("Low Stocks");
            lab4.setText("High Stocks");
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

    @FXML
    public void goToSell(ActionEvent event) throws IOException {
        FXMLLoader sellLoader = new FXMLLoader(getClass().getResource("/view/SellScreen.fxml"));
        sellLoader.setController(new SellScreenController(fm));
        Parent root = sellLoader.load();
        MiniProject.stage.getScene().setRoot(root);
    }

}
