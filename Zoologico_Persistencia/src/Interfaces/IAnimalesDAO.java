/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Animal;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public interface IAnimalesDAO {
    
    /**
     * metodo que implementa animales dao
     * @param animal
     * @return 
     */
    public boolean agregar(Animal animal);
    
    /**
     * metodo que implementa animales dao
     * @param idAnimal
     * @param animal
     * @return 
     */
    public boolean actualizar(Long idAnimal, Animal animal);
    
    /**
     * metodo que implementa animales dao
     * @param idAnimal
     * @return 
     */
    public boolean eliminar(ObjectId idAnimal);
    
    /**
     * metodo que implementa animales dao
     * @param nombre
     * @return 
     */
    public Animal consultarPorNombre(String nombre);
    
    /**
     * metodo que implementa animales dao
     * @param idAnimal
     * @return 
     */
    public Animal consultar(ObjectId idAnimal);
    
    /**
     * metodo que implementa animales dao
     * @return 
     */
    public List<Animal> consultarTodos();
}
