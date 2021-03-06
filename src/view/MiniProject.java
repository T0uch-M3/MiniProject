/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DOA.StatDAO;
import controller.FirstModel;
import controller.LoginScreenController;
import controller.NewLoginController;
import controller.OptionDropDownController;
import controller.SellScreenController;
import controller.StockScreenController;
import enity.Stat;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 *
 * @author Touch-Me
 */
public class MiniProject extends Application {

    /**
     * @param args the command line arguments
     */
    public static Stage stage;
    public static Scene scene2;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double screenWidth = screenSize.getWidth();
    public static double screenHeight = screenSize.getHeight();
    public static FirstModel fm = new FirstModel();

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("SellScreen.fxml"));
//        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("/view/newLogin.fxml"));
        firstLoader.setController(new SellScreenController(fm));
//        firstLoader.setController(new NewLoginController(fm));
        Parent firstUI = firstLoader.load();
        SellScreenController controller = firstLoader.getController();
//        NewLoginController controller = firstLoader.getController();

        Scene sc = new Scene(firstUI);
        primaryStage.setScene(sc);
        sc.setFill(Color.BISQUE);

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(sc);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(720);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setResizable(false);
        
        wDB();
        mDB();
        
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent ev) -> {
            System.exit(0);
        });

    }
    public void wDB(){
        Stat s = null;
        
        try {
                s = StatDAO.getStat("Women");
                 s.getName();
            } catch (NullPointerException ex) {
                s = new Stat("Women", 0);
                StatDAO.addStat(s);
            }
    }
    public void mDB(){
        Stat s = null;
        
        try {
                s = StatDAO.getStat("Men");
                 s.getName();
            } catch (NullPointerException ex) {
                System.out.println("making men");
                s = new Stat("Men", 0);
                StatDAO.addStat(s);
            }
    }

}
