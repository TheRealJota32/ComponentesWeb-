package com.edu.controller;

import edu.turismo.model.LugarTuristico;
import edu.turismo.service.GoogleDriveService;
import edu.turismo.service.TurismoLugares;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "LugarTuristicoController")
@SessionScoped
public class LugarTuristicoController implements Serializable {

    private static final long serialVersionUID = 1L;

    TurismoLugares tl = new TurismoLugares();
    GoogleDriveService google = new GoogleDriveService();

    //Variables for searching places by their country 
    private String buscadorPais;
    private List<LugarTuristico> lugares = new ArrayList<>();

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

    //Variables for images
    private LugarTuristico lugarTuristico;
    private File filePath;

    public List<LugarTuristico> lugares() {
        lugares = tl.getLugaresTuristicos(this.buscadorPais);
        return lugares;
    }

    public LugarTuristico addLugar() {
        lugarTuristico = tl.agregarlugares(this.nombrePais, this.nombreCiudad, this.nombreTuristico, this.coordenadas);
        return lugarTuristico;
    }

    public LugarTuristico addLugarPais() {
        lugarTuristico = tl.agregarLugarConPais(this.idPais, this.nombreCiudad, this.nombreTuristico, this.coordenadas);
        return lugarTuristico;
    }

    public LugarTuristico addLugarCiudad() {
        lugarTuristico = tl.agregarLugarConCiudad(this.idCiudad, this.nombreTuristico, this.coordenadas);
        return lugarTuristico;
    }

    public void imageUpload() throws GeneralSecurityException, IOException {
        google.uploadImage(this.lugarTuristico, this.filePath);
    }

    public void imageDownload() throws IOException, GeneralSecurityException {
        google.getImage(this.lugarTuristico);
    }

    public void updateLugar() {
        tl.modificarLugarTuristico(this.idLugarTuristico, this.updateNombreTuristico);
    }

    public void deleteLugar() {
        tl.borrarLugarTuristico(this.idLugarTuristico);
    }

    public String getBuscadorPais() {
        return buscadorPais;
    }

    public void setBuscadorPais(String buscadorPais) {
        this.buscadorPais = buscadorPais;
    }

    public List<LugarTuristico> getLugares() {
        return lugares;
    }

    public void setLugares(List<LugarTuristico> lugares) {
        this.lugares = lugares;
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

    public File getFilePath() {
        return filePath;
    }

    public void setFilePath(File filePath) {
        this.filePath = filePath;
    }

}
