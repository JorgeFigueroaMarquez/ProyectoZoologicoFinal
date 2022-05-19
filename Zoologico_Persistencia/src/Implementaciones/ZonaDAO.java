/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IZonaDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entidades.Zona;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public class ZonaDAO implements IZonaDAO{
    
    private final MongoDatabase baseDatos;
    private final IConexionBD conexionBD;
    
    /**
     * Metodo que inicializa conexion
     */
    public ZonaDAO() {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula coleccion zona
     * @return 
     */
    private MongoCollection<Zona> getColeccion(){
        return this.baseDatos.getCollection("zonas", Zona.class);
    }
    
    /**
     * Metodo que agrega zona
     * @param zona
     * @return 
     */
    @Override
    public boolean agregar(Zona zona) {
        try{
        MongoCollection<Zona> coleccion = this.getColeccion();
        coleccion.insertOne(zona);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza zona
     * @param idZona
     * @param zona
     * @return 
     */
    @Override
    public boolean actualizar(Long idZona, Zona zona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina zona por id
     * @param idZona
     * @return 
     */
    @Override
    public boolean eliminar(Long idZona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta zona por id
     * @param idZona
     * @return 
     */
    @Override
    public Zona consultar(ObjectId idZona) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta todas las zonas
     * @return 
     */
    @Override
    public List<Zona> consultarTodos() {
        MongoCollection<Zona> coleccion = this.getColeccion();
        List<Zona> listaZonas = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaZonas);
        return listaZonas;
    }
    
}
