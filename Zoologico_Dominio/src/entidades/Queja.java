/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class Queja {
    
    private ObjectId id;
    private String textoQueja, correo, telefono, nombre, dia, hora;
    private Itinerario itinerario;
    private Guia guia;
    
    /**
     * Constructor 
     */
    public Queja() {
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param textoQueja
     * @param correo
     * @param telefono
     * @param nombre
     * @param dia
     * @param hora
     * @param itinerario
     * @param guia 
     */
    public Queja(String textoQueja, String correo, String telefono, String nombre, String dia, String hora, Itinerario itinerario, Guia guia) {
        this.textoQueja = textoQueja;
        this.correo = correo;
        this.telefono = telefono;
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
        this.itinerario = itinerario;
        this.guia = guia;
    }
    
    /**
     * Constructor que inicializa las siguientes atributos:
     * @param id
     * @param textoQueja
     * @param correo
     * @param telefono
     * @param nombre
     * @param dia
     * @param hora
     * @param itinerario
     * @param guia 
     */
    public Queja(ObjectId id, String textoQueja, String correo, String telefono, String nombre, String dia, String hora, Itinerario itinerario, Guia guia) {
        this.id = id;
        this.textoQueja = textoQueja;
        this.correo = correo;
        this.telefono = telefono;
        this.nombre = nombre;
        this.dia = dia;
        this.hora = hora;
        this.itinerario = itinerario;
        this.guia = guia;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public void generarId(){
        this.id = new ObjectId();
    }
    
    public String getTextoQueja() {
        return textoQueja;
    }

    public void setTextoQueja(String textoQueja) {
        this.textoQueja = textoQueja;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerario itinerario) {
        this.itinerario = itinerario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Queja other = (Queja) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Queja{" + "id=" + id + ", textoQueja=" + textoQueja + ", correo=" + correo + ", telefono=" + telefono + ", nombre=" + nombre + ", dia=" + dia + ", hora=" + hora + ", itinerario=" + itinerario + ", guia=" + guia + '}';
    }

    

    
    
}
