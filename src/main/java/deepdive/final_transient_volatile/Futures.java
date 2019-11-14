package deepdive.final_transient_volatile;

import java.time.LocalDate;
import java.util.concurrent.*;

public class Futures {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(128);

        CompletableFuture<Person> someFuturePerson = CompletableFuture.supplyAsync( () -> {
            try {
                Thread.sleep(400);
                return new Person("Hans", "Dampf", LocalDate.of(1983, 6, 14));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }, executorService);

        Future<Person> yearOlderFuturePerson = someFuturePerson.thenApply(person -> person.birthday());

        Person person = yearOlderFuturePerson.get();
    }

}
