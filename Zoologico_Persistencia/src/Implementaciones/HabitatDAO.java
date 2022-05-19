/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IHabitatDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Habitat;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author mj_es
 */
public class HabitatDAO implements IHabitatDAO{
    
    private IConexionBD conexionBD;
    private MongoDatabase baseDatos;
    
    /**
     * Metodo que inicializa conexion
     */
    public HabitatDAO()
    {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula conexion habitat
     * @return 
     */
    private MongoCollection<Habitat> getColeccion(){
        return this.baseDatos.getCollection("habitats", Habitat.class);
    }
    
    /**
     * Metodo que agrega habitat
     * @param habitat
     * @return 
     */
    @Override
    public boolean agregar(Habitat habitat) {
        try{
        MongoCollection<Habitat> coleccion = this.getColeccion();
        coleccion.insertOne(habitat);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza habitat
     * @param idHabitat
     * @param habitat
     * @return 
     */
    @Override
    public boolean actualizar(Long idHabitat, Habitat habitat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina habitat
     * @param idHabitat
     * @return 
     */
    @Override
    public boolean eliminar(Long idHabitat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta habitat con id
     * @param idHabitat
     * @return 
     */
    @Override
    public Habitat consultar(ObjectId idHabitat) {
        MongoCollection<Habitat> coleccion = this.getColeccion();
        Habitat habitat = coleccion.find(Filters.eq("_id", idHabitat)).first();
        return habitat;
    }

    /**
     * Metodo que consulta habitats
     * @return 
     */
    @Override
    public List<Habitat> consultarTodos() {
        // TODO: MANEJAR POSIBLES EXCEPCIONES...
        MongoCollection<Habitat> coleccion = this.getColeccion();
        List<Habitat> listaHabitats = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaHabitats);
        return listaHabitats;
    }
    
}
