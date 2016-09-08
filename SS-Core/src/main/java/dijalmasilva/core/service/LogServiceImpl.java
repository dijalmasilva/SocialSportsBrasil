/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.entidades.Log;
import java.util.List;
import javax.inject.Named;

@Named
public class LogServiceImpl implements LogService {

    @Override
    public void salvar(Log log) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Log> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Inject
//    private LogRepository dao;
//
//    @Override
//    public void salvar(Log log) {
//        dao.save(log);
//    }
//
//    @Override
//    public List<Log> buscarTodos() {
//        return (List<Log>) dao.findAll();
//    }

}
