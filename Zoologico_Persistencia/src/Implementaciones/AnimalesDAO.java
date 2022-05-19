/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IAnimalesDAO;
import Interfaces.IConexionBD;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Animal;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class AnimalesDAO implements IAnimalesDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Constructor que inicializa conexionBD
     */
    public AnimalesDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que te vincula a la coleccion de animales
     * @return 
     */
    private MongoCollection<Animal> getColeccion(){
        return this.baseDatos.getCollection("animales", Animal.class);
    }
    
    /**
     * Metodo que agrega animal
     * @param animal
     * @return 
     */
    @Override
    public boolean agregar(Animal animal) {
        try{
        MongoCollection<Animal> coleccion = this.getColeccion();
        coleccion.insertOne(animal);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza animal
     * @param idAnimal
     * @param animal
     * @return 
     */
    @Override
    public boolean actualizar(Long idAnimal, Animal animal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina animal
     * @param idAnimal
     * @return 
     */
    @Override
    public boolean eliminar(ObjectId idAnimal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta animal por nombre
     * @param nombre
     * @return 
     */
    @Override
    public Animal consultarPorNombre(String nombre) {
        MongoCollection<Animal> coleccion = this.getColeccion();
        Animal animal = coleccion.find(Filters.eq("nombre", nombre)).first();
        return animal;
    }

    /**
     * Metodo que consulta animal
     * @param idAnimal
     * @return 
     */
    @Override
    public Animal consultar(ObjectId idAnimal) {
        MongoCollection<Animal> coleccion = this.getColeccion();
        Animal animal = coleccion.find(Filters.eq("_id", idAnimal)).first();
        return animal;
    }

    /**
     * Metodo que consulta todos los animales
     * @return 
     */
    @Override
    public List<Animal> consultarTodos() {
       // TODO: MANEJAR POSIBLES EXCEPCIONES...
        MongoCollection<Animal> coleccion = this.getColeccion();
        List<Animal> listaAnimales = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaAnimales);
        return listaAnimales;
    }
    
}
