/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.controllers;

import dijalmasilva.core.service.LogService;
import dijalmasilva.entidades.Log;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
@Controller
public class ControladorLog {

    @Inject
    private LogService service;
    
    @RequestMapping("/admin/logs")
    public String logs(HttpServletRequest req){
        List<Log> logs = service.todos();
        req.setAttribute("logs", logs);
        
        return "log";
    }
}
