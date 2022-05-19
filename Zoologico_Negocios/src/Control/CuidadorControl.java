/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.CuidadoresDAO;
import Interfaces.ICuidadoresDAO;
import Interfaces.INegocios;
import entidades.Cuidador;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class CuidadorControl implements INegocios<Cuidador>{
    
    private final ICuidadoresDAO cuidadoresDAO;
    
    /**
     * Constructor que inicializa CuidadoresDAO
     */
    public CuidadorControl(){
        this.cuidadoresDAO = new CuidadoresDAO();
    }
    
    /**
     * Metodo que recupera datos del cuidador
     * @return 
     */
    @Override
    public List<Cuidador> recuperarDatos() {
        try{
            return this.cuidadoresDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudieron recuperar los datos");
            return null;
        }
    }

    /**
     * Metodo que verifica cuidadores
     * @param cuidador
     * @return 
     */
    @Override
    public boolean verificar(Cuidador cuidador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda cuidador
     * @param cuidador
     * @return 
     */
    @Override
    public boolean guarda(Cuidador cuidador) {
        try{
            return this.cuidadoresDAO.agregar(cuidador);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la itinerario");
            return false;
        }
    }

    /**
     * Metodo que actualiza cuidador
     * @param cuidador
     * @return 
     */
    @Override
    public boolean actualiza(Cuidador cuidador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina cuidador
     * @param cuidador
     * @return 
     */
    @Override
    public boolean elimina(Cuidador cuidador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta cuidador
     * @param idCuidador
     * @return 
     */
    @Override
    public Cuidador consulta(ObjectId idCuidador) {
        try{
            return this.cuidadoresDAO.consultar(idCuidador);
        }catch(Exception ex){
            System.out.println("No se pudo consultar el cuidador");
            return null;
        }
    }
    
}
