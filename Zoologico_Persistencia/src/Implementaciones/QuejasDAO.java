/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IQuejaDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Queja;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class QuejasDAO implements IQuejaDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Metodo que inicializa la conexion
     */
    public QuejasDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula la coleccion queja
     * @return 
     */
    private MongoCollection<Queja> getColeccion(){
        return this.baseDatos.getCollection("quejas", Queja.class);
    }   
    
    /**
     * Metodo que agrega queja
     * @param queja
     * @return 
     */
    @Override
    public boolean agregar(Queja queja) {
        try{
        MongoCollection<Queja> coleccion = this.getColeccion();
        coleccion.insertOne(queja);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza queja mediante id
     * @param idQueja
     * @param queja
     * @return 
     */
    @Override
    public boolean actualizar(Long idQueja, Queja queja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina queja
     * @param idQueja
     * @return 
     */
    @Override
    public boolean eliminar(Long idQueja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta queja
     * @param idQueja
     * @return 
     */
    @Override
    public Queja consultar(ObjectId idQueja) {
        MongoCollection<Queja> coleccion = this.getColeccion();
        Queja queja = coleccion.find(Filters.eq("_id", idQueja)).first();
        return queja;
    }

    /**
     * Metodo que consulta quejas
     * @return 
     */
    @Override
    public List<Queja> consultarTodos() {
        MongoCollection<Queja> coleccion = this.getColeccion();
        List<Queja> listaQuejas = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaQuejas);
        return listaQuejas;
    }
    
}
