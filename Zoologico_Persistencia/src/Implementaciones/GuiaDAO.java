/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IGuiaDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Empleado;
import entidades.Guia;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public class GuiaDAO implements IGuiaDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Metodo que inicializa conexion
     */
    public GuiaDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula guia a la coleccion guia
     * @return 
     */
    private MongoCollection<Guia> getColeccion(){
        return this.baseDatos.getCollection("guias", Guia.class);
    }   
    
    /**
     * Metodo que agrega guia
     * @param guia
     * @return 
     */
    @Override
    public boolean agregar(Guia guia) {
        try{
        MongoCollection<Guia> coleccion = this.getColeccion();
        coleccion.insertOne(guia);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza guia
     * @param idGuia
     * @param guia
     * @return 
     */
    @Override
    public boolean actualizar(Long idGuia, Guia guia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina guia
     * @param idGuia
     * @return 
     */
    @Override
    public boolean eliminar(Long idGuia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta guia por id
     * @param idGuia
     * @return 
     */
    @Override
    public Guia consultar(ObjectId idGuia) {
        MongoCollection<Guia> coleccion = this.getColeccion();
        Guia guia = coleccion.find(Filters.eq("_id", idGuia)).first();
        return guia;
    }

    /**
     * Metodo que consulta guias
     * @return 
     */
    @Override
    public List<Guia> consultarTodos() {
        MongoCollection<Guia> coleccion = this.getColeccion();
        List<Guia> listaGuias = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaGuias);
        return listaGuias;
    }
    
}