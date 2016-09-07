/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.repository;

import dijalmasilva.entidades.Visita;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Dijalma Silva <dijalmacz@gmail.com>
 */
public interface VisitaRepository extends MongoRepository<Visita, Date>{
    
    public List<Visita> findByIdAndVisitado(Date day, Long id);
}
