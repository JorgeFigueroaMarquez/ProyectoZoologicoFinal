/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.EspeciesDAO;
import Interfaces.IEspeciesDAO;
import Interfaces.INegocios;
import entidades.Especie;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class EspeciesControl implements INegocios<Especie>{
    
    private final IEspeciesDAO especiesDAO;
            
    /**
     * constructor que inicializa EspecieDAO.
     */
    public EspeciesControl(){
        this.especiesDAO = new EspeciesDAO();
    }
    
    /**
     * Recupera datos de especie en lista
     * @return 
     */
    @Override
    public List<Especie> recuperarDatos() {
        try{
            return this.especiesDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudo consultar la especie");
            return null;
        }
    }

    /**
     * Verifica especie
     * @param entidad
     * @return 
     */
    @Override
    public boolean verificar(Especie entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda especie
     * @param entidad
     * @return 
     */
    @Override
    public boolean guarda(Especie entidad) {
        try{
            return this.especiesDAO.agregar(entidad);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la especie");
            return false;
        }
    }

    /**
     * Metodo que actualiza especie
     * @param entidad
     * @return 
     */
    @Override
    public boolean actualiza(Especie entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina especie
     * @param entidad
     * @return 
     */
    @Override
    public boolean elimina(Especie entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta especie mediante id
     * @param idEspecie
     * @return 
     */
    @Override
    public Especie consulta(ObjectId idEspecie) {
        try{
            return this.especiesDAO.consultar(idEspecie);
        }catch(Exception ex){
            System.out.println("No se pudo consultar la especie");
            return null;
        }
    }
    
}
