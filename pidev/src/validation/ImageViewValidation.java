/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Dhia
 */
public class ImageViewValidation {
    public static boolean isImageViewEmpty(ImageView iv){
        boolean b = false;
        if(iv.getImage()!=null)
            b = true;
        return b;
    }
    
    public static boolean isImageViewEmpty(ImageView iv, Label lb, String errorMessage){
        boolean b = true;
        String msg= null;
        lb.getStyleClass().remove("error-lb");
        if(!isImageViewEmpty(iv)){
            b=false;
            msg = errorMessage;
            lb.getStyleClass().add("error-lb");
        }
        lb.setText(msg);
        return b;
    }
    
}
