/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import model.ProjekatBean;
import zaXML.zaXML;

/**
 *
 * @author amel
 */
public class ProjekatKontroler {
    private zaXML xml = new zaXML();
    private ArrayList<ProjekatBean> projekti = new ArrayList<>();

    public ProjekatKontroler() {
        if(projekti.isEmpty()) try {
            this.setProjekti(xml.procitajIzXMLa());
        } catch (JAXBException ex) {
            Logger.getLogger(ProjekatKontroler.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
    
    private int generateId(){
        int  temp=-1;  
        if(projekti.isEmpty()) return 0;
        for (ProjekatBean a1 : projekti) {        
            if (a1.getId()>temp) temp=a1.getId();
        }
    return temp+1;    
    }
    
    /**
     *
     * @param pb
     * @return
     */
    public boolean dodajProjekat(ProjekatBean pb){
        int i= projekti.size();
        ProjekatBean smjesti = new ProjekatBean();
        smjesti.setId(generateId());
        smjesti.setAmmountOfDonors(pb.getAmmountOfDonors());
        smjesti.setCost(pb.getCost());
        smjesti.setDonorsOfProject(pb.getDonorsOfProject());
        smjesti.setFileName(pb.getFileName());
        smjesti.setLocation(pb.getLocation());
        smjesti.setParticipants(pb.getParticipants());
        smjesti.setProjectDuration(pb.getProjectDuration());
        smjesti.setProjectPath(pb.getProjectPath());
        smjesti.setProjectTitle(pb.getProjectTitle());
        smjesti.setRoleInProject(pb.getRoleInProject());
        this.projekti.add(pb);
        xml.smjesti(projekti);        
        return (xml.smjestiUXML()&& i!=projekti.size());
    }
    
    /**
     *
     * @param pb
     * @return
     */
    public boolean azuirirajProjekat(ProjekatBean selektovaniProjekat, int id){
        projekti.get(id).setAmmountOfDonors(selektovaniProjekat.getAmmountOfDonors());
        projekti.get(id).setCost(selektovaniProjekat.getCost());
        projekti.get(id).setDonorsOfProject(selektovaniProjekat.getDonorsOfProject());
        projekti.get(id).setFileName(selektovaniProjekat.getFileName());
        projekti.get(id).setLocation(selektovaniProjekat.getLocation());
        projekti.get(id).setObjectives(selektovaniProjekat.getObjectives());
        projekti.get(id).setParticipants(selektovaniProjekat.getParticipants());
        projekti.get(id).setProjectDuration(selektovaniProjekat.getProjectDuration());
        projekti.get(id).setProjectPath(selektovaniProjekat.getProjectPath());
        projekti.get(id).setProjectTitle(selektovaniProjekat.getProjectTitle());
        projekti.get(id).setRoleInProject(selektovaniProjekat.getRoleInProject());
        xml.smjesti(projekti);        
        return xml.smjestiUXML();       
        
    }
    
    /**
     *
     * @param k
     * @return
     */
    public boolean obrisiProjekat(ProjekatBean k){
        int pogodak=-1;
        for (ProjekatBean a1 : projekti) {        
            if (a1.getId()==k.getId()) pogodak=a1.getId();        
        }
        if(pogodak!=-1)projekti.remove(pogodak); 
        xml.smjesti(projekti);        
        return (pogodak!=-1 && xml.smjestiUXML());
}
    
    
    

    /**
     * @return the projekti
     */
    public ArrayList<ProjekatBean> getProjekti() {
        return projekti;
    }

    /**
     * @param projekti the projekti to set
     */
    public void setProjekti(ArrayList<ProjekatBean> projekti) {
        this.projekti = projekti;
    }
     
     
     
    
}
