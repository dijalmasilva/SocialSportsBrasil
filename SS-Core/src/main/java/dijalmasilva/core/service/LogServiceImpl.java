/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.core.repository.LogRepository;
import dijalmasilva.entidades.Log;
import java.util.List;
import javax.inject.Named;

@Named
public class LogServiceImpl implements LogService {

    private LogRepository dao = new LogRepository();
    
    @Override
    public void salvar(Log l) {
        dao.salvar(l);
    }

    @Override
    public List<Log> todos() {
        return dao.buscarTodos();
    }
    
}
