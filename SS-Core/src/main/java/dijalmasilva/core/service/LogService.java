/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.entidades.Log;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
@Service
public interface LogService {
    
    void salvar(Log log);
    
    List<Log> buscarTodos();
}
