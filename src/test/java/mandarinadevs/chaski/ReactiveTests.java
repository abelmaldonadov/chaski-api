package mandarinadevs.chaski;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
public class ReactiveTests {
    static IntStream getStream() {
        return IntStream.generate(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            return (int) (Math.random() * 100);
        });
    }

    static Flux<Integer> getFlux() {
        return Flux
            .interval(Duration.ofSeconds(1))
            .map(p -> (int) Math.random() * 100);
    }

    public static void main(String... args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (int u = 0; u <= 10; u++) {
            String usuario = "Usuario " + u;
            threadPool.submit(() -> {
//                getStream()
//                    .mapToObj(p -> usuario + ": " + p)
//                    .forEach(log::info);
                getFlux()
                    .map(p -> usuario + ": " + p)
                    .subscribe(log::info);
            });
        }
    }
}
