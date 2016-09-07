/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.core.repository.VisitaRepository;
import dijalmasilva.entidades.Visita;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class VisitaServiceImpl implements VisitaService {

    @Inject
    private VisitaRepository dao;

    @Override
    public List<Visita> visitasDeHoje(Long id) {
        return dao.findByIdAndVisitado(new Date(), id);
    }

    @Override
    public void visitou(Long visitante, Long visitado) {
        Visita v = new Visita(visitante, visitado);
        dao.save(v);
    }

}
