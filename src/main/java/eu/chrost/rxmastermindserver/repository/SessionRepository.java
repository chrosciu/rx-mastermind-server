package eu.chrost.rxmastermindserver.repository;

import eu.chrost.rxmastermindserver.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
