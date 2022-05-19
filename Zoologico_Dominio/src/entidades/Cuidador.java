package entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author A515-52
 */
public class Cuidador extends Empleado{
    
    private List<Especie> especieExpertos, especieBasico;

    /**
     * Constructor por omision 
     */
    public Cuidador() {
    }

    /**
     * Constructor que inicializa las siguientes atributos:
     * @param nombre
     * @param direccion
     * @param telefono
     * @param fechaIngreso 
     */
    public Cuidador(String nombre, String direccion, String telefono, Date fechaIngreso) {
        super(nombre, direccion, telefono, fechaIngreso);
    }

    public List<Especie> getEspecieExpertos() {
        return especieExpertos;
    }

    public void setEspecieExpertos(List<Especie> especieExpertos) {
        this.especieExpertos = especieExpertos;
    }

    public List<Especie> getEspecieBasico() {
        return especieBasico;
    }

    public void setEspecieBasico(List<Especie> especieBasico) {
        this.especieBasico = especieBasico;
    }

    @Override
    public String toString() {
        return super.toString() + "especieExpertos=" + especieExpertos + ", especieBasico=" + especieBasico + '}';
    }
 
}
