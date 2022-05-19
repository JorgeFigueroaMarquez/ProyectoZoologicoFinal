/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author A515-52
 * @param <T>
 */
public interface INegocios<T> {
    public List<T> recuperarDatos();
    public boolean verificar(T entidad);
    public boolean guarda(T entidad);
    public boolean actualiza(T entidad);
    public boolean elimina(T entidad);
    public T consulta(ObjectId id);
}
