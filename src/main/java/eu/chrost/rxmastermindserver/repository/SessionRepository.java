package eu.chrost.rxmastermindserver.repository;

import eu.chrost.rxmastermindserver.model.Session;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ReactiveCrudRepository<Session, Long> {
}
