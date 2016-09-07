/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.repository;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import dijalmasilva.entidades.Log;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
public class LogRepository {

    private Cluster cluster;
    private Session session;

    public LogRepository() {
        this.cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        this.session = cluster.connect("socialsports");
    }

    public void salvar(Log log) {
        PreparedStatement stmt = session.prepare("insert into log (event, nome, data) values (?,?,?)");
        BoundStatement bound = new BoundStatement(stmt);
        try {
            session.execute(bound.bind(log.getEvent(), log.getNome(), log.getData()));
        } catch (Exception ex) {
            session.execute("create table log (event text, nome text, data date)");
        }
    }

    public List<Log> buscarTodos() {
        Statement select = QueryBuilder.select().all().from("log");
        ResultSet rs = session.execute(select);
        List<Row> all = rs.all();
        List<Log> log = new ArrayList<>();
        for (Row r : all) {
            log.add(new Log(r.getString("event"), r.getString("nome"), r.getDate("data")));
        }
        return log;
    }
}
