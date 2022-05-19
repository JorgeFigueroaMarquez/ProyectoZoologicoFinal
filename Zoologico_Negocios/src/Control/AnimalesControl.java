/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.AnimalesDAO;
import Interfaces.IAnimalesDAO;
import Interfaces.INegocios;
import entidades.Animal;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class AnimalesControl implements INegocios<Animal>{
    
    private final IAnimalesDAO animalesDAO;
    
    /**
     * Constructor que inicializa las animalesDAO
     */
    public AnimalesControl(){
        this.animalesDAO = new AnimalesDAO();
    }
    
    /**
     * Lista que recupera datos de los animales
     * @return 
     */
    @Override
    public List<Animal> recuperarDatos() {
        try{
            return this.animalesDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudo consultar el animal");
            return null;
        }
    }

    /**
     * Verifica que el animal este correcto
     * @param entidad
     * @return 
     */
    @Override
    public boolean verificar(Animal entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda el animal
     * @param animal
     * @return 
     */
    @Override
    public boolean guarda(Animal animal) {
        try{
            return this.animalesDAO.agregar(animal);
        }catch(Exception ex){
            System.out.println("No se pudo guardar el animal");
            return false;
        }
    }

    /**
     * Metodo que actualiza el animal
     * @param entidad
     * @return 
     */
    @Override
    public boolean actualiza(Animal entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina el animal
     * @param entidad
     * @return 
     */
    @Override
    public boolean elimina(Animal entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta el animal y tiene como parametro su id
     * @param idAnimal
     * @return 
     */
    @Override
    public Animal consulta(ObjectId idAnimal) {
        try{
            return this.animalesDAO.consultar(idAnimal);
        }catch(Exception ex){
            System.out.println("No se pudo consultar el animal");
            return null;
        }
    }
    
}
