package entidades;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class Habitat {
    private ObjectId id;
    private String nombre, clima, vegetacionPredominate;
    private List<String> continente;

    /**
     * Constructor por omision
     */
    public Habitat() {
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param id
     * @param nombre
     * @param clima
     * @param vegetacionPredominate
     * @param continente 
     */
    public Habitat(ObjectId id, String nombre, String clima, String vegetacionPredominate, List<String> continente) {
        this.id = id;
        this.nombre = nombre;
        this.clima = clima;
        this.vegetacionPredominate = vegetacionPredominate;
        this.continente = continente;
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param nombre
     * @param clima
     * @param vegetacionPredominate
     * @param continente 
     */
    public Habitat(String nombre, String clima, String vegetacionPredominate, List<String> continente) {
        this.nombre = nombre;
        this.clima = clima;
        this.vegetacionPredominate = vegetacionPredominate;
        this.continente = continente;
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getVegetacionPredominate() {
        return vegetacionPredominate;
    }

    public void setVegetacionPredominate(String vegetacionPredominate) {
        this.vegetacionPredominate = vegetacionPredominate;
    }

    public List<String> getContinente() {
        return continente;
    }

    public void setContinente(List<String> continente) {
        this.continente = continente;
    }

    @Override
    public String toString() {
        return "Habitat{" + "id=" + id + ", nombre=" + nombre + ", clima=" + clima + ", vegetacionPredominate=" + vegetacionPredominate + ", continente=" + continente + '}';
    }
    
}
