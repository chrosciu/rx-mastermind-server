package eu.chrost.rxmastermindserver.service;

import eu.chrost.rxmastermindserver.exception.SessionNotFoundException;
import eu.chrost.rxmastermindserver.model.Session;
import eu.chrost.rxmastermindserver.repository.ReactiveSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final GuessService guessService;
    private final ReactiveSessionRepository reactiveSessionRepository;

    public Mono<Long> create() {
        String code = guessService.code();
        Session session = Session.builder().code(code).build();
        return reactiveSessionRepository.save(session).map(Session::getId);
    }

    public Mono<String> guess(long id, String sample) {
        return reactiveSessionRepository.findById(id)
                .map(s -> guessService.guess(s.getCode(), sample))
                .switchIfEmpty(Mono.error(new SessionNotFoundException()));
    }

    public Mono<Void> destroy(long id) {
        return reactiveSessionRepository.deleteById(id);
    }

}
