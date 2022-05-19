/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IEspeciesDAO;
import entidades.Especie;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class EspeciesDAO implements IEspeciesDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Metodo que crea conexion 
     */
    public EspeciesDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula con conexion especie
     * @return 
     */
    private MongoCollection<Especie> getColeccion(){
        return this.baseDatos.getCollection("especies", Especie.class);
    }
    
    /**
     * Metodo que agrega especie
     * @param especie
     * @return 
     */
    @Override
    public boolean agregar(Especie especie) {
        try{
        MongoCollection<Especie> coleccion = this.getColeccion();
        coleccion.insertOne(especie);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza
     * @param idEspecie
     * @param especie
     * @return 
     */
    @Override
    public boolean actualizar(Long idEspecie, Especie especie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina especie
     * @param idEspecie
     * @return 
     */
    @Override
    public boolean eliminar(ObjectId idEspecie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta por nombre especie
     * @param nombre
     * @return 
     */
    @Override
    public Especie consultarPorNombre(String nombre) {
        MongoCollection<Especie> coleccion = this.getColeccion();
        Especie especie = coleccion.find(Filters.eq("nombreEspañol", nombre)).first();
        return especie;
    }
    
    /**
     * Metodo que consulta especie por id
     * @param idEspecie
     * @return 
     */
    public Especie consultar(ObjectId idEspecie){
        MongoCollection<Especie> coleccion = this.getColeccion();
        Especie especie = coleccion.find(Filters.eq("_id", idEspecie)).first();
        return especie;
    }
    
    /**
     * Metodo que consulta especies
     * @return 
     */
    @Override
    public List<Especie> consultarTodos() {
        // TODO: MANEJAR POSIBLES EXCEPCIONES...
        MongoCollection<Especie> coleccion = this.getColeccion();
        List<Especie> listaEspecies = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaEspecies);
        return listaEspecies;
    }
    
}
