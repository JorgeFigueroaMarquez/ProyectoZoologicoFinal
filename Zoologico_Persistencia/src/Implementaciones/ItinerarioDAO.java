/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IItinerarioDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Itinerario;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public class ItinerarioDAO implements IItinerarioDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Metodo que inicializa conexion
     */
    public ItinerarioDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula coleccion Itinerario
     * @return 
     */
    private MongoCollection<Itinerario> getColeccion(){
        return this.baseDatos.getCollection("itinerarios", Itinerario.class);
    }
    
    /**
     * Metodo que agrega itinerario
     * @param itinerario
     * @return 
     */
    @Override
    public boolean agregar(Itinerario itinerario) {
        try{
        MongoCollection<Itinerario> coleccion = this.getColeccion();
        coleccion.insertOne(itinerario);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }  
    }

    /**
     * Metodo que actualiza itinerario
     * @param idItinerario
     * @param itinerario
     * @return 
     */
    @Override
    public boolean actualizar(Long idItinerario, Itinerario itinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina itinerario
     * @param idItinerario
     * @return 
     */
    @Override
    public boolean eliminar(Long idItinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta itinerario
     * @param idItinerario
     * @return 
     */
    @Override
    public Itinerario consultar(ObjectId idItinerario) {
        MongoCollection<Itinerario> coleccion = this.getColeccion();
        Itinerario itinerario = coleccion.find(Filters.eq("_id", idItinerario)).first();
        return itinerario;
    }

    /**
     * Metodo que consulta itinerario
     * @return 
     */
    @Override
    public List<Itinerario> consultarTodos() {
        MongoCollection<Itinerario> coleccion = this.getColeccion();
        List<Itinerario> listaItinerario = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaItinerario);
        return listaItinerario;
    }
    
}
