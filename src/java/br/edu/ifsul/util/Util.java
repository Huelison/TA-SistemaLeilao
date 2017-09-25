/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.util;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huelison
 */
public class Util {
    public static String getMensagemInternacionalizada(String messageID) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        Locale locale;
        if (contexto != null) {
            locale = contexto.getViewRoot().getLocale();
        }else{
            locale = new Locale("pt","BR");
        }
        ResourceBundle bundle = ResourceBundle.getBundle("messages",locale);
        try{
            msg = bundle.getString(messageID);
        }catch(Exception e){
                msg = messageID;
        }
        return msg;
    }
}
