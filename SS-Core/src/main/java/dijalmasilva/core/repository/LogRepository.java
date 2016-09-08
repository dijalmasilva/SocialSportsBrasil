/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.repository;

import dijalmasilva.entidades.Log;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
@Repository
@ViewIndexed(designDoc = "logs")
public interface LogRepository extends CouchbaseRepository<Log, String>{
    
}
