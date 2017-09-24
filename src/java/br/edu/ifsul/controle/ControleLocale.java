/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Huelison
 */
@ManagedBean
@SessionScoped
public class ControleLocale implements Serializable{
    private Locale currentLocale = new Locale("pt","BR");

    public void englishLocale(){
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = Locale.US;
        viewRoot.setLocale(currentLocale);
    }
    
    public void portugueseLocale(){
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        currentLocale = new Locale("pt","BR");
        viewRoot.setLocale(currentLocale);
    }
    
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public ControleLocale() {
    }
    
}
