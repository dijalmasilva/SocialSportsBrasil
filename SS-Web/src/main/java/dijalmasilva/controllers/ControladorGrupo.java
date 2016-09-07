/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.controllers;

import dijalmasilva.core.service.GrupoService;
import dijalmasilva.core.service.IdoloService;
import dijalmasilva.entidades.Grupo;
import dijalmasilva.entidades.Idolo;
import dijalmasilva.entidades.Usuario;
import dijalmasilva.form.GrupoForm;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
@Controller
@RequestMapping("/groups")
public class ControladorGrupo {

    @Inject
    private GrupoService serviceGrupo;
    @Inject
    private IdoloService serviceIdolo;

    @RequestMapping("/my")
    public String followingGroups() {
        return "followingGroups";
    }

    @RequestMapping("/{id}")
    public String getGroup(@PathVariable Long id, HttpServletRequest req) {
        Grupo grupo = serviceGrupo.buscar(id);
        req.setAttribute("group", grupo);
        return "otherGroup";
    }

    @RequestMapping("/findByName")
    public String getGroups(String nome, HttpServletRequest req) {
        List<Grupo> groups = serviceGrupo.buscarPorNome(nome);
        req.setAttribute("gruposEncontrados", groups);
        return "groupsfind";
    }

    @RequestMapping("/new")
    public String newGroup() {
        return "newGroup";
    }

    @RequestMapping("/save")
    public String save(GrupoForm g, String idolo, HttpServletRequest req) {
        Usuario user = (Usuario) req.getSession().getAttribute("user");
        Idolo idol = serviceIdolo.buscar(idolo);
        if (idol == null) {
            req.setAttribute("result", "Idolo não cadastrado.");
        } else {
            Grupo grupo = convertToGrupo(g);
            grupo.setIdolo(idol);
            grupo = serviceGrupo.salvar(user, grupo);

            if (grupo != null) {
                req.setAttribute("result", "Grupo cadastrado com sucesso!");
            } else {
                req.setAttribute("result", "Não foi possível cadastrar grupo.!");
            }
        }
        return "newGroup";
    }

    @RequestMapping("/follow/{id}")
    public String seguirGrupo(@PathVariable Long id, HttpServletRequest req) {
        Usuario user = (Usuario) req.getSession().getAttribute("user");
        Usuario u = serviceGrupo.seguirGrupo(id, user);
        req.getSession().setAttribute("user", u);
        
        return "redirect:/groups/" + id;
    }
    
    @RequestMapping("/unfollow/{id}")
    public String deixarDeSeguirGrupo(@PathVariable Long id, HttpServletRequest req) {
        Usuario user = (Usuario) req.getSession().getAttribute("user");
        Usuario u = serviceGrupo.deixarDeSeguirGrupo(id, user);
        req.getSession().setAttribute("user", u);
        
        return "redirect:/groups/" + id;
    }

    private Grupo convertToGrupo(GrupoForm g) {
        Grupo grupo = new Grupo();
        grupo.setDescricao(g.getDescricao());
        grupo.setNome(g.getNome());

        return grupo;
    }
}
