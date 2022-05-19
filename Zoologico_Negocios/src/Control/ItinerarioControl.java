/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.ItinerarioDAO;
import Interfaces.IItinerarioDAO;
import Interfaces.INegocios;
import entidades.Itinerario;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class ItinerarioControl implements INegocios<Itinerario>{
    
    private final IItinerarioDAO itinerariosDAO;
    
    /**
     * Constructor que inicializa ItinerarioDAO
     */
    public ItinerarioControl(){
        this.itinerariosDAO = new ItinerarioDAO();
    }
    
    /**
     * Metodo que recupera datos de itinerario
     * @return 
     */
    @Override
    public List<Itinerario> recuperarDatos() {
        try{
            return this.itinerariosDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudo consultar la especie");
            return null;
        }
    }

    /**
     * Metodo que verifica datos de itinerario
     * @param itinerario
     * @return 
     */
    @Override
    public boolean verificar(Itinerario itinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda Itinerario
     * @param itinerario
     * @return 
     */
    @Override
    public boolean guarda(Itinerario itinerario) {
         try{
            return this.itinerariosDAO.agregar(itinerario);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la itinerario");
            return false;
        }
    }

    /**
     * Metodo que actualiza Itinerario
     * @param itinerario
     * @return 
     */
    @Override
    public boolean actualiza(Itinerario itinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina itinerario
     * @param itinerario
     * @return 
     */
    @Override
    public boolean elimina(Itinerario itinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta itinerario mediante id
     * @param idItinerario
     * @return 
     */
    @Override
    public Itinerario consulta(ObjectId idItinerario) {
        try{
            return this.itinerariosDAO.consultar(idItinerario);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la itinerario");
            return null;
        }
    }
    
}
