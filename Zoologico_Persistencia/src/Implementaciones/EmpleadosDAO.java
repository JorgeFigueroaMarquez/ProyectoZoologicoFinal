/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import Interfaces.IConexionBD;
import Interfaces.IEmpleadosDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import entidades.Empleado;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author A515-52
 */
public class EmpleadosDAO implements IEmpleadosDAO{
    
    private IConexionBD conexionBD;
    private MongoDatabase baseDatos;
    
    /**
     * Constructor que inicializa conexion y la crea
     */
    public EmpleadosDAO()
    {
        this.conexionBD = new ConexionBD();
        this.baseDatos = conexionBD.crearConexion();
    }
    
    /**
     * Metodo que vincula a la coleccion empleado
     * @return 
     */
    private MongoCollection<Empleado> getColeccion(){
        return this.baseDatos.getCollection("habitats", Empleado.class);
    }
    
    /**
     * Metodo que agrega empleado
     * @param empleado
     * @return 
     */
    @Override
    public boolean agregar(Empleado empleado) {
        try{
        MongoCollection<Empleado> coleccion = this.getColeccion();
        coleccion.insertOne(empleado);
        return true;
        }catch(Exception ex){
            System.out.println("No se pudo agregar la especie");
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Metodo que actualiza empleado
     * @param idEmpleado
     * @param empleado
     * @return 
     */
    @Override
    public boolean actualizar(Long idEmpleado, Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina empleado
     * @param idEmpleado
     * @return 
     */
    @Override
    public boolean eliminar(Long idEmpleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta empleado
     * @param idEmpleado
     * @return 
     */
    @Override
    public Empleado consultar(Long idEmpleado) {
        MongoCollection<Empleado> coleccion = this.getColeccion();
        Empleado empleado = coleccion.find(Filters.eq("_id", idEmpleado)).first();
        return empleado;
    }

    /**
     * Metodo que consulta empleados
     * @return 
     */
    @Override
    public List<Empleado> consultarTodos() {
        // TODO: MANEJAR POSIBLES EXCEPCIONES...
        MongoCollection<Empleado> coleccion = this.getColeccion();
        List<Empleado> listaEmpleados = new LinkedList<>();
        coleccion.find(
            //Filters.and(
                //Filters.gt("rating", 4), 
                //Filters.lt("rating", 5))
        ).into(listaEmpleados);
        return listaEmpleados;
    }   
}
