/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import kontroler.ProjekatKontroler;
import model.ProjekatBean;
import korisni.utility;

/**
 *
 * @author amel
 */
@RequestScoped
@ManagedBean (name="projekat")
public class webBeanProjekat {
    
    private ProjekatKontroler PK = new ProjekatKontroler();
    private ProjekatBean projekatUnos= new ProjekatBean();
    private ProjekatBean selektovani;
    private int selektovaniId;
    
    private Part file;

    public webBeanProjekat() {
    }

    public void reset(){
        projekatUnos.setId(0);
        projekatUnos.setAmmountOfDonors("");
        projekatUnos.setCost("");
        projekatUnos.setDonorsOfProject("");
        projekatUnos.setFileName("");
        projekatUnos.setLocation("");
        projekatUnos.setObjectives("");
        projekatUnos.setParticipants("");
        projekatUnos.setProjectDuration("");
        projekatUnos.setProjectPath("");
        projekatUnos.setProjectTitle("");
        projekatUnos.setRoleInProject("");
    }
    
    public String snimi(){
        if(projekatUnos == null) return "greska";
        if(!getFile().getSubmittedFileName().isEmpty()){
            projekatUnos.setProjectPath(getFile().getSubmittedFileName());
            try (InputStream input = getFile().getInputStream()) {
                Files.copy(input, new File(utility.putZaProjekte, getFile().getSubmittedFileName()).toPath());
            }
            catch (IOException e) {
            // Show faces message?
            }
        }else{
            projekatUnos.setProjectPath("");            
        }
        if(PK.dodajProjekat(projekatUnos)) {
            reset();
            return "index";
        }
        return "greska";
    }
    
    public String azuriraj (){
        if (PK.azuirirajProjekat(selektovani, getSelektovaniId()))
            return "index";
        return "greska";
    }
    
    public void downloadBaza (){
        download (utility.putZaXML + "baza.xml", "baza.xml");
    }
    public void downloadProjekat(){
        download(utility.putZaProjekte + selektovani.getProjectPath(),
                selektovani.getProjectPath());
    }
    
    
    private void download(String path, String imeFajla) {

    File file = new File(path);
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

    response.setHeader("Content-Disposition", "attachment;filename=" + imeFajla );  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.printStackTrace();  
        }  
    } 
    }
    /**
     * @return the PK
     */
    public ProjekatKontroler getPK() {
        return PK;
    }

    /**
     * @param PK the PK to set
     */
    public void setPK(ProjekatKontroler PK) {
        this.PK = PK;
    }

    /**
     * @return the projekatUnos
     */
    public ProjekatBean getProjekatUnos() {
        return projekatUnos;
    }

    /**
     * @param projekatUnos the projekatUnos to set
     */
    public void setProjekatUnos(ProjekatBean projekatUnos) {
        this.projekatUnos = projekatUnos;
    }

    /**
     * @return the selektovani
     */
    public ProjekatBean getSelektovani() {
        return selektovani;
    }

    /**
     * @param selektovani the selektovani to set
     */
    public void setSelektovani(ProjekatBean selektovani) {
        this.selektovani = selektovani;
    }

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the selektovaniId
     */
    public int getSelektovaniId() {
        return selektovaniId;
    }

    /**
     * @param selektovaniId the selektovaniId to set
     */
    public void setSelektovaniId(int selektovaniId) {
        this.selektovaniId = selektovaniId;
    }
    
}
