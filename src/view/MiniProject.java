/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LoginScreenController;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public static void main(String[] args) {
        launch();
    }
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StockScreen.fxml"));
//      loader.setController(new LoginScreenController());
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
//        scene.setFill(Color.BISQUE);
        scene2 = scene;
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(720);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setResizable(false);
        primaryStage.show();
        
        
        
    }
    
}
