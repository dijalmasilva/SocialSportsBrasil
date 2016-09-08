/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.entidades;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
@Document
public class Log {

    @Id
    private String id;

    @Field
    private String event;

    @Field
    private String name;

    @CreatedDate
    private Date data;

    public Log() {
    }

    public Log(String event, String name, Date data) {
        this.event = event;
        this.name = name;
        this.data = data;
    }

    public Log(String id, String event, String name, Date data) {
        this.id = id;
        this.event = event;
        this.name = name;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
