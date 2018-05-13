/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Touch-Me
 */
public class FirstModel {
     private final StringProperty text = new SimpleStringProperty();
     private final BooleanProperty stat = new SimpleBooleanProperty();

    public StringProperty textProperty() {
        return text ;
    }

    public final String getText() {
        return textProperty().get();
    }

    public final void setText(String text) {
        textProperty().set(text);
    }
    public BooleanProperty statProperty() {
        return stat ;
    }

    public final boolean getStat() {
        return statProperty().get();
    }

    public final void setStat(boolean stat) {
        statProperty().set(stat);
    }
}
