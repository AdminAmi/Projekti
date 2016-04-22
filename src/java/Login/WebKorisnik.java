package Login;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



/**
 *
 * @author amel
 */
@RequestScoped
@ManagedBean 
public class WebKorisnik {
    private Login.loginKontroler lk = new loginKontroler();
    private login selektovaniKorisnik = new login();
    private int selektovaniID;
    private String imePretraga;
    private List<String> tipovi = new ArrayList<>();   

    public WebKorisnik() {
    }
    
    @PostConstruct
    public void init() {       
    //inicijalizacijski kod za objekat bolje nego konstruiktor
    }
    @PreDestroy
    public void shutdown() {
    // shutdown code
    }
    public void ucitajOsobu(){
        selektovaniKorisnik=lk.getUserFromID(getSelektovaniID());
        getTipovi().add(0,"admin");
        getTipovi().add(1,"korisnik");
        getTipovi().add(2,"guest");       
    }
    
    public void pretraga(){
        resetLista();
        lk.pretragaPoImenu(getImePretraga());
        setImePretraga("");
    }
    public void resetLista(){lk.getPretraga().clear();}
    
    public void azurirajKorisnika() throws NoSuchAlgorithmException{        
        lk.azurirajOsobu(selektovaniKorisnik);        
    }
    
    public boolean obrisiKorisnika(){
        
    
       return true;
    
    
    }

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
     * @return the imePretraga
     */
    public String getImePretraga() {
        return imePretraga;
    }

    /**
     * @param imePretraga the imePretraga to set
     */
    public void setImePretraga(String imePretraga) {
        this.imePretraga = imePretraga;
    }

    /**
     * @return the selektovaniKorisnik
     */
    public login getSelektovaniKorisnik() {
        return selektovaniKorisnik;
    }

    /**
     * @param selektovaniKorisnik the selektovaniKorisnik to set
     */
    public void setSelektovaniKorisnik(login selektovaniKorisnik) {
        this.selektovaniKorisnik = selektovaniKorisnik;
    }

    /**
     * @return the selektovaniID
     */
    public int getSelektovaniID() {
        return selektovaniID;
    }

    /**
     * @param selektovaniID the selektovaniID to set
     */
    public void setSelektovaniID(int selektovaniID) {
        this.selektovaniID = selektovaniID;
    }

    /**
     * @return the tipovi
     */
    public List<String> getTipovi() {
        return tipovi;
    }

    /**
     * @param tipovi the tipovi to set
     */
    public void setTipovi(List<String> tipovi) {
        this.tipovi = tipovi;
    }
    
    

    

}
