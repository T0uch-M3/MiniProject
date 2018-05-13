/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import view.MiniProject;

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
    boolean firsTime = true, currentStat = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fm.statProperty().addListener((obs, oldStat, newStat) -> {
            if (newStat) {
                currentStat = true;
            } else {
                currentStat = false;
            }
        });
    }

    public void checkIt(ActionEvent event) {

    }

    @FXML
    public void toArabic(ActionEvent event) {
//        System.out.println("ARRRRR");
//        StockScreenController p = new StockScreenController();
//        p.testLabel.setText("AAAAAA");
        fm.setText("AR");
    }

    @FXML
    public void toEnglish(ActionEvent event) {
        fm.setText("EN");
    }

    public OptionDropDownController(FirstModel fm) {
        this.fm = fm;
    }

    @FXML
    public void changePwd(ActionEvent event) {
//        if (firsTime) {
//            System.out.println("first time");
//            fm.setStat(true);
//            firsTime = false;
//        }
        if (currentStat) {
            fm.setStat(false);
        } else {
            fm.setStat(true);
        }
    }

    public void changeStat() {

    }

    @FXML
    public void logoutAction(ActionEvent event) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/newLogin.fxml"));
        loginLoader.setController(new NewLoginController(fm));
        Parent root = loginLoader.load();
        MiniProject.stage.getScene().setRoot(root);
    }

}
