package eu.chrost.rxmastermindserver.controller;


import eu.chrost.rxmastermindserver.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public Mono<Long> create() {
        //TODO: Implement
        return null;
    }

    @PutMapping("/{id}/{sample}")
    public Mono<String> guess(@PathVariable("id") long id, @PathVariable("sample") String sample) {
        //TODO: Implement
        return null;
    }

    @DeleteMapping("/{id}")
    public Mono<Void> destroy(@PathVariable("id") long id) {
        //TODO: Implement
        return null;
    }
}
