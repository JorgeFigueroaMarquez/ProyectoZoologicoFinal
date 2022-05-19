/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Guia;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public interface IGuiaDAO{
   
    /**
     * metodo que implementa GuiaDAO
     * @param guia
     * @return 
     */
    public boolean agregar(Guia guia);
    
    /**
     * metodo que implementa GuiaDAO
     * @param idGuia
     * @param guia
     * @return 
     */
    public boolean actualizar(Long idGuia, Guia guia);
    
    /**
     * metodo que implementa GuiaDAO
     * @param idGuia
     * @return 
     */
    public boolean eliminar(Long idGuia);
    
    /**
     * metodo que implementa GuiaDAO
     * @param idGuia
     * @return 
     */
    public Guia consultar(ObjectId idGuia);
    
    /**
     * metodo que implementa GuiaDAO
     * @return 
     */
    public List<Guia> consultarTodos();
    
}
