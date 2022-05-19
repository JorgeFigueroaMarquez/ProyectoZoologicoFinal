/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entidades.Empleado;
import java.util.List;

/**
 *
 * @author A515-52
 */
public interface IEmpleadosDAO {
    
    /**
     * metodo que implementa EmpleadoDAO
     * @param empleado
     * @return 
     */
    public boolean agregar(Empleado empleado);
    
    /**
     * metodo que implementa EmpleadoDAO
     * @param idEmpleado
     * @param empleado
     * @return 
     */
    public boolean actualizar(Long idEmpleado, Empleado empleado);
    
    /**
     * metodo que implementa EmpleadoDAO
     * @param idEmpleado
     * @return 
     */
    public boolean eliminar(Long idEmpleado);
    
    /**
     * metodo que implementa EmpleadoDAO
     * @param idEmpleado
     * @return 
     */
    public Empleado consultar(Long idEmpleado);
    
    /**
     * metodo que implementa EmpleadoDAO
     * @return 
     */
    public List<Empleado> consultarTodos();
}
