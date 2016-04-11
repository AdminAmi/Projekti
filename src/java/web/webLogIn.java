/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import Login.loginKontroler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author amel
 */
@SessionScoped
@ManagedBean (name="LogIn")
public class webLogIn {
    private Login.loginKontroler lk = new loginKontroler();
    //private Login.login korisnik;
    private String user, pass;
    private boolean testRegistracije;
    

    public webLogIn() {
         setTestRegistracije(false);
    }
    
    public String registracija(){
        if(lk.LogIN(user, pass)) {
            setTestRegistracije(true);
            return "main";
        }
        else {
            setTestRegistracije(false);
            FacesMessage message = new FacesMessage("   Neuspješna prijava na sistem !!!   ");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("loginForm:myButton", message);
        }
        return null;
    }
    
    public String logOff(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();        
        lk.setKorisnici(null);        
        setTestRegistracije(false);
        return "index";        
    }
    public String noviProjekat(){ return "noviProjekat";  }
    
   
    /**
     * @return the lk
     */
    public Login.loginKontroler getLk() {
        return lk;
    }

    /**
     * @param lk the lk to set
     */
    public void setLk(Login.loginKontroler lk) {
        this.lk = lk;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the testRegistracije
     */
    public boolean isTestRegistracije() {
        return testRegistracije;
    }

    /**
     * @param testRegistracije the testRegistracije to set
     */
    public void setTestRegistracije(boolean testRegistracije) {
        this.testRegistracije = testRegistracije;
    }
    
    
}
