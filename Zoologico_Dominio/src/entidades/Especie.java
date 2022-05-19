package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class Especie {
    private ObjectId id;
    private String nombreEspañol, nombreCientifico, descripcionGeneral;
    private List<ObjectId> habitats;
    private Zona zonaVive;
    private List<ObjectId> idCuidadores;
    
    /**
     * Constructor por omision
     */
    public Especie() {
    }
    
    
    /**
     * Constructor que inicializa las siguientes atributos:
     * @param nombreEspañol
     * @param nombreCientifico
     * @param descripcionGeneral
     * @param habitats
     * @param zonaVive
     * @param idCuidadores 
     */
    public Especie(String nombreEspañol, String nombreCientifico, String descripcionGeneral, List<ObjectId> habitats, Zona zonaVive, List<ObjectId> idCuidadores) {
        this.nombreEspañol = nombreEspañol;
        this.nombreCientifico = nombreCientifico;
        this.descripcionGeneral = descripcionGeneral;
        this.habitats = habitats;
        this.zonaVive = zonaVive;
        this.idCuidadores = idCuidadores;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreEspañol() {
        return nombreEspañol;
    }

    public void setNombreEspañol(String nombreEspañol) {
        this.nombreEspañol = nombreEspañol;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public List<ObjectId> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<ObjectId> habitats) {
        this.habitats = habitats;
    }

    public Zona getZonaVive() {
        return zonaVive;
    }

    public void setZonaVive(Zona zonaVive) {
        this.zonaVive = zonaVive;
    }

    public List<ObjectId> getIdCuidadores() {
        return idCuidadores;
    }

    public void setIdCuidadores(List<ObjectId> idCuidadores) {
        this.idCuidadores = idCuidadores;
    }

    @Override
    public String toString() {
        return "Especie{" + "id=" + id + ", nombreEspa\u00f1ol=" + nombreEspañol + ", nombreCientifico=" + nombreCientifico + ", descripcionGeneral=" + descripcionGeneral + ", habitats=" + habitats + ", zonaVive=" + zonaVive + '}';
    }
    
    
}
