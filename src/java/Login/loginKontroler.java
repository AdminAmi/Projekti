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
    private login noviKorisnik = new login();
    private zaXML xml = new zaXML();
    
    public loginKontroler(){
        try {
        if (korisnici.isEmpty()) this.setKorisnici(xml.procitajIzXMLa());        
    } catch (JAXBException ex) { }
        
    }
    public int generateId(){
        int  temp=-1;  
        if(korisnici.isEmpty()) return 0;
        for (login a1 : korisnici) {        
        if (a1.getId()>temp) temp=a1.getId();
    }
    return temp+1;    
    }
    public boolean dodajOsobu(login o) throws NoSuchAlgorithmException{
        int i = korisnici.size();
        login kor = new login(o.getUser(),utility.sha1(o.getPass()) , o.getIme(), o.getPrezime(), o.getRola());
        kor.setId(generateId());
        this.korisnici.add(kor);
        xml.smjesti(korisnici);     
        return (i!=korisnici.size() && xml.smjestiUXML());
    }
    
    public boolean azurirajOsobu(login log) throws NoSuchAlgorithmException{
        korisnici.get(log.getId()).setIme(log.getIme());
        korisnici.get(log.getId()).setPrezime(log.getPrezime());
        korisnici.get(log.getId()).setRola(log.getRola());
        korisnici.get(log.getId()).setUser(log.getUser());
        korisnici.get(log.getId()).setPass(utility.sha1(log.getPass()));
        xml.smjesti(korisnici); 
        return xml.smjestiUXML();
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

    /**
     * @return the noviKorisnik
     */
    public login getNoviKorisnik() {
        return noviKorisnik;
    }

    /**
     * @param noviKorisnik the noviKorisnik to set
     */
    public void setNoviKorisnik(login noviKorisnik) {
        this.noviKorisnik = noviKorisnik;
    }
    
}
