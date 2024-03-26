package eu.chrost.rxmastermindserver;

import eu.chrost.rxmastermindserver.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Transactional
//transactions will not work here - see https://github.com/spring-projects/spring-framework/issues/24226
@Slf4j
class RxMastermindServerApplicationTests {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private SessionRepository sessionRepository;

    @Test
    void shouldCreateSessionMakeGuessAndDeleteSession() {
        Long sessionId = webTestClient
                .post()
                .uri("/session")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Long.class)
                .returnResult()
                .getResponseBody();
        log.info("{}", sessionId);

        StepVerifier.create(sessionRepository.findById(sessionId))
                .assertNext(s -> Assertions.assertEquals(sessionId, s.getId()))
                .verifyComplete();

        String result = webTestClient
                .put()
                .uri("/session/{id}/{sample}", sessionId, "1234")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        log.info("{}", result);

        String result2 = webTestClient
                .put()
                .uri("/session/{id}/{sample}", sessionId, "1234")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        log.info("{}", result2);

        Assertions.assertEquals(result, result2);

        webTestClient
                .put()
                .uri("/session/{id}/{sample}", sessionId, "A")
                .exchange()
                .expectStatus().isBadRequest();

        webTestClient
                .delete()
                .uri("/session/{id}", sessionId)
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        webTestClient
                .put()
                .uri("/session/{id}/{sample}", sessionId, "1234")
                .exchange()
                .expectStatus().isNotFound();

        StepVerifier.create(sessionRepository.findById(sessionId))
                .verifyComplete();

    }

}
