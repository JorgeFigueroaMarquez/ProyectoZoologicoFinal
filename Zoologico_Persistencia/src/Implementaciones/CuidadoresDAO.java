/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.ICuidadoresDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Cuidador;
import entidades.Empleado;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class CuidadoresDAO implements ICuidadoresDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Metodo que inicializa
     */
    public CuidadoresDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula a la coleccion cuidador
     * @return 
     */
    private MongoCollection<Cuidador> getColeccion(){
        return this.baseDatos.getCollection("cuidadores", Cuidador.class);
    }   

    /**
     * Metodo que agrega cuidador
     * @param cuidador
     * @return 
     */
    @Override
    public boolean agregar(Cuidador cuidador) {
        try{
        MongoCollection<Cuidador> coleccion = this.getColeccion();
        coleccion.insertOne(cuidador);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Meotodo que actualiza cuidador
     * @param idCuidador
     * @param cuidador
     * @return 
     */
    @Override
    public boolean actualizar(Long idCuidador, Cuidador cuidador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina cuidador
     * @param idCuidador
     * @return 
     */
    @Override
    public boolean eliminar(Long idCuidador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta cuidador
     * @param idCuidador
     * @return 
     */
    @Override
    public Cuidador consultar(ObjectId idCuidador) {
        MongoCollection<Cuidador> coleccion = this.getColeccion();
        Cuidador cuidador = coleccion.find(Filters.eq("_id", idCuidador)).first();
        return cuidador;
    }
    
    /**
     * Metodo que consulta todos los cuidadores
     * @return 
     */
    @Override
    public List<Cuidador> consultarTodos() {
        MongoCollection<Cuidador> coleccion = this.getColeccion();
        List<Cuidador> listaCuidadores = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaCuidadores);
        return listaCuidadores;
    }
    
    
    
}
