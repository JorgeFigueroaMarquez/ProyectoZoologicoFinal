/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Implementaciones.QuejasDAO;
import Interfaces.INegocios;
import Interfaces.IQuejaDAO;
import entidades.Queja;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 */
public class QuejaControl implements INegocios<Queja>{
    
    private final IQuejaDAO quejasDAO;
    
    /**
     * Metodo que inicializa QuejasDAO
     */
    public QuejaControl(){
        this.quejasDAO = new QuejasDAO();
    }
    
    /**
     * Metodo que recuoera datos queja
     * @return 
     */
    @Override
    public List<Queja> recuperarDatos() {
        try{
            return this.quejasDAO.consultarTodos();
        }catch(Exception ex){
            System.out.println("No se pudieron recuperar los datos");
            return null;
        }
    }

    /**
     * Metodo que verifica queja
     * @param queja
     * @return 
     */
    @Override
    public boolean verificar(Queja queja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que guarda queja
     * @param queja
     * @return 
     */
    @Override
    public boolean guarda(Queja queja) {
        try{
            return this.quejasDAO.agregar(queja);
        }catch(Exception ex){
            System.out.println("No se pudo guardar la itinerario");
            return false;
        }
    }

    /**
     * Metodo que actualiza queja
     * @param queja
     * @return 
     */
    @Override
    public boolean actualiza(Queja queja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que elimina queja
     * @param queja
     * @return 
     */
    @Override
    public boolean elimina(Queja queja) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que consulta queja mediante id
     * @param idQueja
     * @return 
     */
    @Override
    public Queja consulta(ObjectId idQueja) {
        try{
            return this.quejasDAO.consultar(idQueja);
        }catch(Exception ex){
            System.out.println("No se pudo consultar la queja");
            return null;
        }
    }
    
}
