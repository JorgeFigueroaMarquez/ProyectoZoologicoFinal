/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import entidades.Cuidador;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public interface ICuidadoresDAO{
    
    /**
     * metodo que implementa cuidadorDAO
     * @param cuidador
     * @return 
     */
     public boolean agregar(Cuidador cuidador);
    
     /**
      * metodo que implementa cuidadorDAO
      * @param idCuidador
      * @param cuidador
      * @return 
      */
    public boolean actualizar(Long idCuidador, Cuidador cuidador);
    
    /**
     * metodo que implementa cuidadorDAO
     * @param idCuidador
     * @return 
     */
    public boolean eliminar(Long idCuidador);
    
    /**
     * metodo que implementa cuidadorDAO
     * @param idCuidador
     * @return 
     */
    public Cuidador consultar(ObjectId idCuidador);
    
    /**
     * metodo que implementa cuidadorDAO
     * @return 
     */
    public List<Cuidador> consultarTodos();
}
