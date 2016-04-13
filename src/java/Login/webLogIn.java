/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Login.loginKontroler;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import korisni.utility;

/**
 *
 * @author amel
 */
@SessionScoped
@ManagedBean (name="LogIn")
public class webLogIn {
    private Login.loginKontroler lk = new loginKontroler();    
    private String user, pass;
    private boolean testRegistracije;
    private String newPass, confirmPass;
    

    public webLogIn() {
         setTestRegistracije(false);
    }
    
    private void reset(){
        setUser(""); setPass(""); setNewPass(""); setConfirmPass("");
    }
    
    public String promjenaPass() throws NoSuchAlgorithmException{
        if(lk.getKorisnik().getPass().contains(utility.sha1(getPass()))
                && getNewPass().contains(getConfirmPass())){
            lk.getKorisnik().setPass(newPass);
            lk.azurirajOsobu(lk.getKorisnik());
            utility.poruka("AzuriranjeKorisnika:promjenaPass", 
                    "Uspješna promjena zaporke!!!");
            reset();
            } else {
            utility.poruka("AzuriranjeKorisnika:promjenaPass", 
                    "Neuspješna promjena zaporke!!!"); 
            reset();
        }            
        return null;
    }
    
    /**
     * Unosi novog korisnika
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String unesiNovogKorisnika() throws NoSuchAlgorithmException {
        if(getNewPass().contains(getConfirmPass()) && getNewPass().length()>0){
            lk.getNoviKorisnik().setPass(getNewPass());
            lk.dodajOsobu(lk.getNoviKorisnik());            
            utility.poruka("unosNovogKorisnika:btnNoviKorisnik",
                    "Uspješno dodavanje novog korisnika!!!");
            reset();
            
        } else {            
            utility.poruka("unosNovogKorisnika:btnNoviKorisnik",
                    "Unešene vrijednosti za zaporke nisu iste!!!");
            reset();
        }
        return null;
    }
    
    public String registracija(){
        if(lk.LogIN(user, pass)) {
            setTestRegistracije(true);
            reset();
            return "main";
        }
        else {
            setTestRegistracije(false);
            reset();
            utility.poruka("loginForm:myButton","Neuspješna prijava na sistem!!!");           
        }
        return null;
    }
    
    public String logOff(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();        
        lk=null;        
        reset();
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

    /**
     * @return the newPass
     */
    public String getNewPass() {
        return newPass;
    }

    /**
     * @param newPass the newPass to set
     */
    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    /**
     * @return the confirmPass
     */
    public String getConfirmPass() {
        return confirmPass;
    }

    /**
     * @param confirmPass the confirmPass to set
     */
    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    
    
}
