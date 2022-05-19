/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Zona;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public interface IZonaDAO {
    
    /**
     * metodo que implementa ZonaDAO
     * @param zona
     * @return 
     */
    public boolean agregar(Zona zona);
    
    /**
     * metodo que implementa ZonaDAO
     * @param idZona
     * @param zona
     * @return 
     */
    public boolean actualizar(Long idZona, Zona zona);
    
    /**
     * metodo que implementa ZonaDAO
     * @param idZona
     * @return 
     */
    public boolean eliminar(Long idZona);
    
    /**
     * metodo que implementa ZonaDAO
     * @param idZona
     * @return 
     */
    public Zona consultar(ObjectId idZona);
    
    /**
     * metodo que implementa ZonaDAO
     * @return 
     */
    public List<Zona> consultarTodos();
}
