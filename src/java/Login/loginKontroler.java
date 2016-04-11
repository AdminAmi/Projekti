/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import model.utility;

/**
 *
 * @author amel
 */
public class loginKontroler {
    private ArrayList<login> korisnici = new ArrayList<>();
    private login korisnik = new login();
    private zaXML xml = new zaXML();
    
    public loginKontroler(){
        try {
        if (korisnici.isEmpty()) this.setKorisnici(xml.procitajIzXMLa());        
    } catch (JAXBException ex) { }
        
    }

    /**
     * @return the korisnici
     */
    public ArrayList<login> getKorisnici() {
        return korisnici;
    }

    /**
     * @param korisnici the korisnici to set
     */
    public void setKorisnici(ArrayList<login> korisnici) {
        this.korisnici = korisnici;
    }
    
    public boolean LogIN(String user, String pass){
        if (user == null || user.length()==0 || pass==null || pass.length()==0) {
            return false;
        } else {
            for (login a1 : korisnici) {
                try {
                    if (a1.getUser().contains(user) && a1.getPass().contains(utility.sha1(pass))){
                        setKorisnik(a1);
                        return true;
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(loginKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;                
    }

    /**
     * @return the korisnik
     */
    public login getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(login korisnik) {
        this.korisnik = korisnik;
    }
    
}