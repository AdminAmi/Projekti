/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.security.NoSuchAlgorithmException;
import model.utility;

/**
 *
 * @author amel
 */
public class inicijalizacija {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        login korisnik = new login("ami", "mepifiki0309975", "Amel", "Džanić", "admin");
        login korisnik1 = new login("g", "g", "Gost", "Gost", "guest");
        login korisnik2 = new login("a", "a", "Test", "Test", "admin");
        zaXML xml = new zaXML();                
        loginKontroler lk = new loginKontroler();
//        lk.getKorisnici().add(korisnik);
//        lk.getKorisnici().add(korisnik1);
//        lk.getKorisnici().add(korisnik2);
//        xml.smjesti(lk.getKorisnici());
//        xml.smjestiUXML();
        lk.dodajOsobu(korisnik);
        lk.dodajOsobu(korisnik1);
        lk.dodajOsobu(korisnik2);
        
        
    }
    
}
