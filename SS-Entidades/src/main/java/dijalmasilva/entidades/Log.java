/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.entidades;

import com.datastax.driver.core.LocalDate;
import java.util.Date;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
@Table
public class Log {

    @PrimaryKey
    private Long id;

    private String event;
    private String nome;
    private Date data;

    public Log() {
    }

    public Log(String event, String nome, Date data) {
        this.event = event;
        this.nome = nome;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
