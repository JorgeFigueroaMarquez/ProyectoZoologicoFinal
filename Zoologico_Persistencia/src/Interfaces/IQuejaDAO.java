/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Queja;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public interface IQuejaDAO {
    
    /**
     * metodo que implementa QuejaDAO
     * @param queja
     * @return 
     */
    public boolean agregar(Queja queja);
    
    /**
     * metodo que implementa QuejaDAO
     * @param idQueja
     * @param queja
     * @return 
     */
    public boolean actualizar(Long idQueja, Queja queja);
    
    /**
     * metodo que implementa QuejaDAO
     * @param idQueja
     * @return 
     */
    public boolean eliminar(Long idQueja);
    
    /**
     * metodo que implementa QuejaDAO
     * @param idQueja
     * @return 
     */
    public Queja consultar(ObjectId idQueja);
    
    /**
     * metodo que implementa QuejaDAO
     * @return 
     */
    public List<Queja> consultarTodos();
}
