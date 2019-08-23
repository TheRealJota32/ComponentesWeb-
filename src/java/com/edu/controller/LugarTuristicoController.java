package com.edu.controller;

import edu.turismo.model.LugarTuristico;
import edu.turismo.service.GoogleDriveService;
import edu.turismo.service.TurismoLugares;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "lugarTuristicoController")
@SessionScoped
public class LugarTuristicoController implements Serializable {

    private static final long serialVersionUID = 1L;

    TurismoLugares tl = new TurismoLugares();
    GoogleDriveService google = new GoogleDriveService();

    //Variables for searching places by their country 
    private String buscadorPais;
    private List<LugarTuristico> places = new ArrayList<>();

    //Variables for adding places
    private int idPais;
    private int idCiudad;
    private String nombrePais;
    private String nombreCiudad;
    private String nombreTuristico;
    private String coordenadas;

    //Variables for deletes and updates
    private int idLugarTuristico;
    private String updateNombreTuristico;
    private LugarTuristico selectedLugarTuristico;

    //Variables for images
    private LugarTuristico lugarTuristico;
    private UploadedFile file;
    private StreamedContent image;

    //Variable for all places
    private List<LugarTuristico> allPlaces = new ArrayList<>();

    public String lugares() {
        places = tl.getLugaresTuristicos(this.buscadorPais);
        for (LugarTuristico l : places) {
            System.out.println(l.getNombre());
        }
        this.buscadorPais = "";
        return "searchResults?faces-redirect=true";
    }

    public LugarTuristico addLugar() {
        lugarTuristico = tl.agregarLugar(this.nombrePais, this.nombreCiudad, this.nombreTuristico, this.coordenadas);
        this.allLugares();
        return lugarTuristico;
    }

    public void imageUpload() throws GeneralSecurityException, IOException {
        google.uploadImage(this.lugarTuristico, this.file.getInputstream());
    }

    public void imageDownload(LugarTuristico id) throws IOException, GeneralSecurityException {
        InputStream googleImage = google.getImage(id);
        this.image = new DefaultStreamedContent(googleImage, "image/jpeg");
    }

    public void lugarUpload() throws GeneralSecurityException, IOException {
        this.addLugar();
        this.imageUpload();
    }

    public void updateLugar(int id) {
        tl.modificarLugarTuristico(id, this.selectedLugarTuristico.getNombre(), this.selectedLugarTuristico.getGeoCorde());
        FacesContext.getCurrentInstance().addMessage("places", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modified Place"));
    }

    public void deleteLugar(int id) {
        tl.borrarLugarTuristico(id);
        for (LugarTuristico l : this.allPlaces) {
            if (l.getId() == id) {
                this.allPlaces.remove(l);
                break;
            }
        }
        FacesContext.getCurrentInstance().addMessage("places", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modified Place"));
    }

    public void handleFileUpload(FileUploadEvent event) throws GeneralSecurityException, IOException {
        file = event.getFile();
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            this.lugarTuristico = tl.buscarLugar(this.nombreTuristico);
            GoogleDriveService.uploadImage(this.lugarTuristico, event.getFile().getInputstream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.lugarTuristico = null;
    }

    public LugarTuristico placeView() {
        this.lugarTuristico = tl.buscarLugar(this.nombreTuristico);
        return lugarTuristico;
    }

    public String redirectAfterPlace() {
        return "placesList?faces-redirect=true";
    }

    @PostConstruct
    public void allLugares() {
        this.allPlaces = tl.getLugaresTuristicos();
    }

    public String getBuscadorPais() {
        return buscadorPais;
    }

    public void setBuscadorPais(String buscadorPais) {
        this.buscadorPais = buscadorPais;
    }

    public List<LugarTuristico> getPlaces() {
        return places;
    }

    public void setPlaces(List<LugarTuristico> places) {
        this.places = places;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreTuristico() {
        return nombreTuristico;
    }

    public void setNombreTuristico(String nombreTuristico) {
        this.nombreTuristico = nombreTuristico;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getIdLugarTuristico() {
        return idLugarTuristico;
    }

    public void setIdLugarTuristico(int idLugarTuristico) {
        this.idLugarTuristico = idLugarTuristico;
    }

    public String getUpdateNombreTuristico() {
        return updateNombreTuristico;
    }

    public void setUpdateNombreTuristico(String updateNombreTuristico) {
        this.updateNombreTuristico = updateNombreTuristico;
    }

    public LugarTuristico getLugarTuristico() {
        return lugarTuristico;
    }

    public void setLugarTuristico(LugarTuristico lugarTuristico) {
        this.lugarTuristico = lugarTuristico;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public StreamedContent getImage() {
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public List<LugarTuristico> getAllPlaces() {
        return allPlaces;
    }

    public void setAllPlaces(List<LugarTuristico> allPlaces) {
        this.allPlaces = allPlaces;
    }

    public LugarTuristico getSelectedLugarTuristico() {
        return selectedLugarTuristico;
    }

    public void setSelectedLugarTuristico(LugarTuristico selectedLugarTuristico) {
        this.selectedLugarTuristico = selectedLugarTuristico;
    }

}
