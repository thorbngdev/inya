/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inya;


/**
 *
 * @author THOR
 */
public class Inya {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setResizable(false);
        loginWindow.setVisible(true);
        loginWindow.setLocation(640, 360);        
    }
    
}
