package entidades;

import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class Itinerario {
    private ObjectId id;
    private int longitud, maxVisitantes, numEspeciesVisita;
    private String nombre, duracion;
    private List<String> diasSemana;
    private List<ObjectId> zonasRecorridas;

    /**
     * Constructor por omision
     */
    public Itinerario() {
    }

    /**
     * Constructor que inicializa las siguientes atributos
     * @param nombre
     * @param duracion
     * @param longitud
     * @param maxVisitantes
     * @param numEspeciesVisita
     * @param diaSemana
     * @param zonasRecorridas 
     */
    public Itinerario(String nombre, String duracion, int longitud, int maxVisitantes, int numEspeciesVisita, List<String> diaSemana, List<ObjectId> zonasRecorridas) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.longitud = longitud;
        this.maxVisitantes = maxVisitantes;
        this.numEspeciesVisita = numEspeciesVisita;
        this.diasSemana = diaSemana;
        this.zonasRecorridas = zonasRecorridas;
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param id
     * @param duracion
     * @param longitud
     * @param maxVisitantes
     * @param numEspeciesVisita
     * @param diasSemana
     * @param zonasRecorridas 
     */
    public Itinerario(ObjectId id, String duracion, int longitud, int maxVisitantes, int numEspeciesVisita, List<String> diasSemana, List<ObjectId> zonasRecorridas) {
        this.id = id;
        this.duracion = duracion;
        this.longitud = longitud;
        this.maxVisitantes = maxVisitantes;
        this.numEspeciesVisita = numEspeciesVisita;
        this.diasSemana = diasSemana;
        this.zonasRecorridas = zonasRecorridas;
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
    
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getMaxVisitantes() {
        return maxVisitantes;
    }

    public void setMaxVisitantes(int maxVisitantes) {
        this.maxVisitantes = maxVisitantes;
    }

    public int getNumEspeciesVisita() {
        return numEspeciesVisita;
    }

    public void setNumEspeciesVisita(int numEspeciesVisita) {
        this.numEspeciesVisita = numEspeciesVisita;
    }

    public List<String> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }
    
    public List<ObjectId> getZonasRecorridas() {
        return zonasRecorridas;
    }

    public void setZonasRecorridas(List<ObjectId> zonasRecorridas) {
        this.zonasRecorridas = zonasRecorridas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + this.longitud;
        hash = 67 * hash + this.maxVisitantes;
        hash = 67 * hash + this.numEspeciesVisita;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.duracion);
        hash = 67 * hash + Objects.hashCode(this.diasSemana);
        hash = 67 * hash + Objects.hashCode(this.zonasRecorridas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Itinerario other = (Itinerario) obj;
        if (this.longitud != other.longitud) {
            return false;
        }
        if (this.maxVisitantes != other.maxVisitantes) {
            return false;
        }
        if (this.numEspeciesVisita != other.numEspeciesVisita) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.duracion, other.duracion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.diasSemana, other.diasSemana)) {
            return false;
        }
        if (!Objects.equals(this.zonasRecorridas, other.zonasRecorridas)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Itinerario{" + "id=" + id + ", longitud=" + longitud + ", maxVisitantes=" + maxVisitantes + ", numEspeciesVisita=" + numEspeciesVisita + ", nombre=" + nombre + ", duracion=" + duracion + ", diasSemana=" + diasSemana + ", zonasRecorridas=" + zonasRecorridas + '}';
    }
}
