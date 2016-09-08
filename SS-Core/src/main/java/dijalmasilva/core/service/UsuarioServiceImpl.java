/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.entidades.Usuario;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import dijalmasilva.core.repository.UsuarioRepository;
import dijalmasilva.entidades.Grupo;
import dijalmasilva.entidades.Visita;
import java.util.ArrayList;
import java.util.Objects;

@Named
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRepository dao;
    @Inject
    private VisitaService visitaService;
//    @Inject
//    private LogService logService;

    @Override
    public Usuario login(String emailOuUsername, String password) {
        if (emailOuUsername.contains("@")) {
            return loginEmail(emailOuUsername, password);
        } else {
            return loginUsername(emailOuUsername, password);
        }
    }

    private Usuario loginEmail(String email, String password) {
        Usuario user = dao.findByEmail(email);
        if (user != null && user.getSenha().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    private Usuario loginUsername(String username, String password) {
        Usuario user = dao.findByUsername(username);
        if (user != null && user.getSenha().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public Usuario salvarUsuario(Usuario u) {
//        Log l = new Log(UUID.randomUUID().toString(), "Cadastro de usuário", u.getNome(), LocalDate.now());
//        logService.salvar(l);
        return dao.save(u);
    }

    @Override
    public void desativarConta(Long id) {
        Usuario u = dao.findOne(id);
        u.setConta("DESATIVADA");
        dao.save(u);
    }

    @Override
    public Usuario atualizarPerfil(Usuario u) {
        return dao.save(u);
    }

    @Override
    public Usuario seguirAmigo(Long id, Long amigo) {
        Usuario user = dao.findOne(id);
        Usuario friend = dao.findOne(amigo);
        user.addAmigo(friend);
        dao.save(friend);
        return dao.save(user);
    }

    @Override
    public Usuario deixarDeSeguirAmigo(Long id, Long amigo) {
        Usuario user = dao.findOne(id);
        Usuario friend = dao.findOne(amigo);
        user.removeAmigo(friend);
        dao.save(friend);
        return dao.save(user);
    }

    private List<Usuario> buscarPorUsername(String username) {

        List<Usuario> usuarios = dao.findByUsernameContaining(username);
        return usuarios;
    }

    private List<Usuario> buscarPorEmail(String email) {

        List<Usuario> usuarios = dao.findByEmailContaining(email);
        return usuarios;
    }

    @Override
    public List<Usuario> listarTodos() {
        return (List<Usuario>) dao.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public List<Usuario> buscarUsuarios(String nome) {
        List<Usuario> usuarios;

        if (nome.contains("@")) {
            usuarios = buscarPorEmail(nome);
        } else {
            usuarios = buscarPorUsername(nome);
        }

        return usuarios;
    }

    @Override
    public boolean isFollow(Long id, Long seguindo) {
        Usuario user = dao.findOne(id);
        return user.isFollowingUser(seguindo);
    }

    @Override
    public List<Usuario> buscarUsuariosComIdDiferenteAndNaoDesativada(String nome, Long id) {
        List<Usuario> usuariosEncontrados;
        String[] nomes = nome.split(" ");
        List<Usuario> usuariosEncontradosPorNome;
        List<Usuario> usuariosEncontradosSobrenome;
        if (nomes != null && nomes.length > 1) {
            usuariosEncontradosPorNome = dao.findByNomeContainingAndIdNotAndContaNotLikeIgnoreCase(nomes[0], id, "Desativada");
            usuariosEncontradosSobrenome = dao.findBySobrenomeContainingAndIdNotAndContaNotLikeIgnoreCase(nomes[1], id, "Desativada");

        } else {
            usuariosEncontradosPorNome = dao.findByNomeContainingAndIdNotAndContaNotLikeIgnoreCase(nome, id, "Desativada");
            usuariosEncontradosSobrenome = dao.findBySobrenomeContainingAndIdNotAndContaNotLikeIgnoreCase(nome, id, "Desativada");
        }

        usuariosEncontrados = usuariosEncontradosPorNome;

        for (Usuario u : usuariosEncontradosSobrenome) {
            if (!isContido(u, usuariosEncontrados)) {
                usuariosEncontrados.add(u);
            }
        }

        return usuariosEncontrados;
    }

    private boolean isContido(Usuario u, List<Usuario> usuarios) {

        for (Usuario usuario : usuarios) {
            if (usuario.equals(u)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Usuario seguirGrupo(Usuario u, Grupo grupo) {
        u.addGrupo(grupo);

        return dao.save(u);
    }

    @Override
    public Usuario deixarDeSeguirGrupo(Usuario u, Grupo g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> visitaramSeuPerfil(Long id) {
        List<Visita> visitas = visitaService.visitasDeHoje(id);
        List<Usuario> usuarios = new ArrayList<>();

        for (Visita visita : visitas) {
            if (!isExistInVisit(visita.getVisitante(), usuarios)) {
                usuarios.add(dao.findOne(visita.getVisitante()));
            }
        }

        return usuarios;
    }

    private boolean isExistInVisit(Long id, List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            if (Objects.equals(u.getId(), id)) {
                return true;
            }
        }

        return false;
    }
}
