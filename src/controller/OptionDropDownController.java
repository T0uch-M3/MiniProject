/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Touch-Me
 */
public class OptionDropDownController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static boolean languageEng = true;
    private final FirstModel fm;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void checkIt(ActionEvent event){
        
    }
    @FXML
    public void toArabic(ActionEvent event){
//        System.out.println("ARRRRR");
//        StockScreenController p = new StockScreenController();
//        p.testLabel.setText("AAAAAA");
        fm.setText("اختبار");
    }
    @FXML
    public void toEnglish(ActionEvent event){
        fm.setText("Test");
    }
    public OptionDropDownController(FirstModel fm){
        this.fm = fm;
    }
    
}
