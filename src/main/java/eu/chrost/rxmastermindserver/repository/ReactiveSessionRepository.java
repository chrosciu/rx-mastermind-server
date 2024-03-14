package eu.chrost.rxmastermindserver.repository;

import eu.chrost.rxmastermindserver.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ReactiveSessionRepository {
    private final SessionRepository sessionRepository;

    public Mono<Session> save(Session session) {
        //TODO: Implement
        return null;
    }

    public Mono<Session> findById(long id) {
        //TODO: Implement
        return null;
    }

    public Mono<Void> deleteById(long id) {
        //TODO: Implement
        return null;
    }
}
