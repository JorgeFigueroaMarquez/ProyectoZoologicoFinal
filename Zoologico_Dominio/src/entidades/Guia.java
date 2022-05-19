package entidades;

import java.util.Date;

/**
 *
 * @author A515-52
 */
public class Guia extends Empleado{
    
    private Itinerario ItinerarioActual;

    /**
     * Constructor por omision 
     */
    public Guia() {
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param nombre
     * @param direccion
     * @param telefono
     * @param fechaIngreso 
     */
    public Guia(String nombre, String direccion, String telefono, Date fechaIngreso) {
        super(nombre, direccion, telefono, fechaIngreso);
    }

    public Itinerario getItinerarioActual() {
        return ItinerarioActual;
    }

    public void setItinerarioActual(Itinerario ItinerarioActual) {
        this.ItinerarioActual = ItinerarioActual;
    }

    @Override
    public String toString() {
        return super.toString() + "ItinerarioActual=" + ItinerarioActual + '}';
    }
  
}
