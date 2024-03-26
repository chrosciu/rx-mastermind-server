package eu.chrost.rxmastermindserver.service;

import eu.chrost.rxmastermindserver.exception.SessionNotFoundException;
import eu.chrost.rxmastermindserver.model.Session;
import eu.chrost.rxmastermindserver.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final GuessService guessService;
    private final SessionRepository sessionRepository;

    public Mono<Long> create() {
        String code = guessService.code();
        Session session = Session.builder().code(code).build();
        return sessionRepository.save(session).map(Session::getId);
    }

    public Mono<String> guess(long id, String sample) {
        return sessionRepository.findById(id)
                .map(s -> guessService.guess(s.getCode(), sample))
                .switchIfEmpty(Mono.error(new SessionNotFoundException()));
    }

    public Mono<Void> destroy(long id) {
        return sessionRepository.deleteById(id);
    }

}
