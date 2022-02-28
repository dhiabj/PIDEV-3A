/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 *
 * @author zacha
 */
public class DatePickerValidation {
    public static boolean isDatePickerNotEmpty(DatePicker d){
        boolean b = false;
        if(d.getValue() != null)
            b = true;
        return b;
    }
    
    public static boolean isDatePickerNotEmpty(DatePicker d, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        lb.getStyleClass().remove("error-lb");
        if(!isDatePickerNotEmpty(d)){
            b=false;
            msg = errorMessage;
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
            
        return b;
    }
    
}
