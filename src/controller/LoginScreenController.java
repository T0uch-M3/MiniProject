/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDrawer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.MiniProject;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class LoginScreenController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @FXML
    JFXDrawer drawMiddle, drawLeft;
    
    VBox drawVb;
    VBox vBoxL;
//    StackPane drawVb;
    boolean fullScreen = false;
    boolean visible = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            drawVb = FXMLLoader.load(getClass().getResource("/view/MiddleDrawer.fxml"));
            drawMiddle.setSidePane(drawVb);
            vBoxL = FXMLLoader.load(getClass().getResource("/view/LeftDrawer.fxml"));
            drawLeft.setSidePane(vBoxL);
            
        }
        catch(IOException ex){
            
            ex.printStackTrace();
        }
        MiniProject.stage.widthProperty().addListener((obs,oldVal, newVal) ->{
            if(MiniProject.stage.widthProperty().doubleValue()!=MiniProject.screenWidth){
                MiniProject.stage.setFullScreen(false);
            
            fullScreen=false;
            drawerState();
            }
        });
        
        
        
    }
    public void vBoxLShow(){
        System.out.println(drawLeft.getHeight());
        if(drawLeft.isHidden()){
            drawLeft.open();
            visible = true;
        }
        else{
            drawLeft.close();
            visible = false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //*********************************************************************
    public void drawerState(){
        if(fullScreen==true&&drawMiddle.isShown()){
            drawVb.setPrefHeight(MiniProject.screenHeight);
            drawVb.setMinHeight(MiniProject.screenHeight);
            drawVb.setMinHeight(MiniProject.screenHeight);
            }
        if(fullScreen==false&&drawMiddle.isShown()){
            drawVb.setPrefHeight(681);
            drawVb.setMinHeight(681);
            drawVb.setMinHeight(681);
            }
        if(fullScreen==true){
            drawLeft.setPrefHeight(681);
            drawLeft.setMinHeight(681);
            drawLeft.setMinHeight(681);
            drawLeft.setDefaultDrawerSize(900);
            if(visible)
                drawLeft.open();
        }
        if(fullScreen==false){
            drawLeft.setPrefHeight(421);
            drawLeft.setMinHeight(421);
            drawLeft.setMinHeight(421);
            drawLeft.setDefaultDrawerSize(700);
            if(visible)
                drawLeft.open();
            }
    }
    public void colorMeth(){
        
        drawVb.setStyle("-fx-background-color: rgba(0,0,0,0)");
//        drawVb.setBackground(Background.EMPTY);
//        drawMiddle.setStyle("-fx-background-color: rgba(0,100,100,0.8)");
        
    }
    @FXML
    public void goFullScreen(ActionEvent event){
   
        if(!fullScreen){
            MiniProject.stage.setFullScreen(true);
            fullScreen = true;
            drawerState();
        }
        else{
            MiniProject.stage.setFullScreen(false);
            fullScreen=false;
            drawerState();
        }
//        drawMiddle.setStyle(value);
    }
    @FXML
    public void drawClick(MouseEvent event){
        if(drawMiddle.isHidden()){
            drawMiddle.open();
//            System.out.println("drawer H"+drawMiddle.getHeight());
//            System.out.println("vbox H"+drawVb.getHeight());
            if(fullScreen){
                drawVb.setPrefHeight(MiniProject.screenHeight);
                drawVb.setMinHeight(MiniProject.screenHeight);
                drawVb.setMinHeight(MiniProject.screenHeight);
            }
        }
            
        else{
            drawMiddle.close();
            if(fullScreen){
                drawVb.setPrefHeight(681);
                drawVb.setMinHeight(681);
                drawVb.setMinHeight(681);
            }
//            drawVb.setPrefHeight(MiniProject.stage.heightProperty().doubleValue());
//            System.out.println("drawer H"+drawMiddle.getHeight());
//            System.out.println("vbox H"+drawVb.getHeight());
        }
            
    }
    
    
}
