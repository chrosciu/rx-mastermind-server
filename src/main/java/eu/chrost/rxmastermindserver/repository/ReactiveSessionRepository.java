package eu.chrost.rxmastermindserver.repository;

import eu.chrost.rxmastermindserver.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@RequiredArgsConstructor
public class ReactiveSessionRepository {
    private final SessionRepository sessionRepository;

    public Mono<Session> save(Session session) {
        return Mono.fromCallable(() -> sessionRepository.save(session)).subscribeOn(Schedulers.boundedElastic());
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
