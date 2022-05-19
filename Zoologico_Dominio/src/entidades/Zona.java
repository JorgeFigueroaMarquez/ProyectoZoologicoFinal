package entidades;

import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class Zona {
    private ObjectId id;
    private String nombre;
    private float extension;

    /**
     * Constructor por omision
     */
    public Zona() {
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param nombre
     * @param extension 
     */
    public Zona(String nombre, float extension) {
        this.nombre = nombre;
        this.extension = extension;
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

    public float getExtension() {
        return extension;
    }

    public void setExtension(float extension) {
        this.extension = extension;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Float.floatToIntBits(this.extension);
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
        final Zona other = (Zona) obj;
        if (Float.floatToIntBits(this.extension) != Float.floatToIntBits(other.extension)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Zona{" + "id=" + id + ", nombre=" + nombre + ", extension=" + extension + '}';
    }
}
