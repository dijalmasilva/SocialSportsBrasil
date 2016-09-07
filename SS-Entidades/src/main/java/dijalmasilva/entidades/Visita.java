/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.entidades;

import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
public class Visita {

    @Id
    private Date id;

    private Long visitante;
    private Long visitado;

    public Visita() {
        this.id = new Date();
    }

    public Visita(Long visitante, Long visitado) {
        this.id = new Date();
        this.visitante = visitante;
        this.visitado = visitado;
    }

    public Date getId() {
        return id;
    }

    public void setId(Date id) {
        this.id = id;
    }

    public Long getVisitante() {
        return visitante;
    }

    public void setVisitante(Long visitante) {
        this.visitante = visitante;
    }

    public Long getVisitado() {
        return visitado;
    }

    public void setVisitado(Long visitado) {
        this.visitado = visitado;
    }

}
