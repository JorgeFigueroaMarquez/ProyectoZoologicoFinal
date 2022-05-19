/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Habitat;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public interface IHabitatDAO {
    
    /**
     * metodo que implementa HabitatDAO
     * @param habitad
     * @return 
     */
    public boolean agregar(Habitat habitad);
    
    /**
     * metodo que implementa HabitatDAO
     * @param idHabitat
     * @param habitad
     * @return 
     */
    public boolean actualizar(Long idHabitat, Habitat habitad);
    
    /**
     * metodo que implementa HabitatDAO
     * @param idHabitat
     * @return 
     */
    public boolean eliminar(Long idHabitat);
    
    /**
     * metodo que implementa HabitatDAO
     * @param idHabitat
     * @return 
     */
    public Habitat consultar(ObjectId idHabitat);
    
    /**
     * metodo que implementa HabitatDAO
     * @return 
     */
    public List<Habitat> consultarTodos();
}
