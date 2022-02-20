/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 *
 * @author Dhia
 */
public class ChoiceBoxValidation {
    public static boolean isChoiceBoxNotEmpty(ChoiceBox cb){
        boolean b = false;
        if(cb.getValue() != null || !cb.getSelectionModel().isEmpty())
            b = true;
        return b;
    }
    
    public static boolean isChoiceBoxNotEmpty(ChoiceBox cb, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        lb.getStyleClass().remove("error-lb");
        if(!isChoiceBoxNotEmpty(cb)){
            b=false;
            msg = errorMessage;
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
            
        return b;
    }
}
