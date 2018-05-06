/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Touch-Me
 */
public class testClass {

    public final static testClass instance = new testClass();
    
    public static testClass getInstance(){
        return instance;
    }
    private boolean bool;

    public boolean getChecked() {
        return bool;
    }
    public void setChecked(boolean boo){
        this.bool = boo;
    }
    
}
