/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Dhia
 */
public class TextFieldValidation {
    
    public static boolean isTextFieldTypeString(TextField tf){
        boolean b = false;
        if(tf.getText().matches("^[a-zA-ZÀ-Ÿ, ]*$") && (tf.getText().length() != 0 || !tf.getText().isEmpty()))
            b = true;
        return b;
    }
    
    public static boolean isTextFieldTypeString(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldTypeString(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isTextFieldPhoneNumber(TextField tf){
        boolean b = false;
        if(tf.getText().matches("^\\d{8}$") && (tf.getText().length() != 0 || !tf.getText().isEmpty()))
            b = true;
        return b;
    }
    
    public static boolean isTextFieldPhoneNumber(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldPhoneNumber(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isTextFieldEmail(TextField tf){
        boolean b = false;
        if(tf.getText().matches("^(.+)@(.+)$") && (tf.getText().length() != 0 || !tf.getText().isEmpty()))
            b = true;
        return b;
    }
    
       public static boolean isTextFieldEmail(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldEmail(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
   
    
    public static boolean isTextFieldEmpty(TextField tf){
        boolean b = false;
        if(tf.getText().length() != 0 || !tf.getText().isEmpty())
            b = true;
        return b;
    }
    
    public static boolean isTextFieldEmpty(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldEmpty(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
    
    public static boolean isTextFieldTypeNumber(TextField tf){
        boolean b = false;
        if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+") && (tf.getText().length() != 0 || !tf.getText().isEmpty()))
            b = true;
        return b;
    }
    
    public static boolean isTextFieldTypeNumber(TextField tf, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        tf.getStyleClass().remove("error-tf");
        lb.getStyleClass().remove("error-lb");
        if(!isTextFieldTypeNumber(tf)){
            b=false;
            msg = errorMessage;
            tf.getStyleClass().add("error-tf");
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
    
}
