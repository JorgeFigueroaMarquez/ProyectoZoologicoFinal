/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Especie;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public interface IEspeciesDAO {
    
    /**
     * metodo que implementa EspecieDAO
     * @param especie
     * @return 
     */
    public boolean agregar(Especie especie);
    
    /**
     * metodo que implementa EspecieDAO
     * @param idEspecie
     * @param especie
     * @return 
     */
    public boolean actualizar(Long idEspecie, Especie especie);
    
    /**
     * metodo que implementa EspecieDAO
     * @param idEspecie
     * @return 
     */
    public boolean eliminar(ObjectId idEspecie);
    
    /**
     * metodo que implementa EspecieDAO
     * @param nombre
     * @return 
     */
    public Especie consultarPorNombre(String nombre);
    
    /**
     * metodo que implementa EspecieDAO
     * @param especie
     * @return 
     */
    public Especie consultar(ObjectId especie);
    
    /**
     * metodo que implementa EspecieDAO
     * @return 
     */
    public List<Especie> consultarTodos();
}
