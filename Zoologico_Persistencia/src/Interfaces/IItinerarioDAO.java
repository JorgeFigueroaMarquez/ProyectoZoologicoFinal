/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Itinerario;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public interface IItinerarioDAO {
    
    /**
     * metodo que implementa ItinerarioDAO
     * @param itinerario
     * @return 
     */
    public boolean agregar(Itinerario itinerario);
    
    /**
     * metodo que implementa ItinerarioDAO
     * @param idItinerario
     * @param itinerario
     * @return 
     */
    public boolean actualizar(Long idItinerario, Itinerario itinerario);
        
    /**
     * metodo que implementa ItinerarioDAO
     * @param idItinerario
     * @return 
     */
    public boolean eliminar(Long idItinerario);
    
    /**
     * metodo que implementa ItinerarioDAO
     * @param idItinerario
     * @return 
     */
    public Itinerario consultar(ObjectId idItinerario);
    
    /**
     * metodo que implementa ItinerarioDAO
     * @return 
     */
    public List<Itinerario> consultarTodos();
    
    
}
