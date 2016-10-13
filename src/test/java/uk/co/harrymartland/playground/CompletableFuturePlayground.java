package uk.co.harrymartland.playground;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;

public class CompletableFuturePlayground {

    @Test
    public void canMapResults() throws ExecutionException, InterruptedException {

        Integer result = CompletableFuture
                .supplyAsync(() -> "4")
                .thenApplyAsync(Integer::parseInt)
                .get();
        Assert.assertEquals((Integer) 4, result);
    }

    @Test
    public void canReceiveExceptionInHandleMethod() throws ExecutionException, InterruptedException {

        final RuntimeException exception = new RuntimeException();
        Throwable result = CompletableFuture
                .supplyAsync(() -> {
                    throw exception;
                })
                .handleAsync((o, throwable) -> throwable)
                .get();
        Assert.assertEquals(exception, result.getCause());
    }

    @Test
    public void shouldThrowExecutionExceptionWithThrownExceptionInside() {

        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        IndexOutOfBoundsException expectedException = new IndexOutOfBoundsException();
        objectCompletableFuture.completeExceptionally(expectedException);

        try {
            objectCompletableFuture.get();
        } catch (InterruptedException e) {
            Assert.fail("Should throw execution exeption");
        } catch (ExecutionException e) {
            Assert.assertEquals(expectedException, e.getCause());
        }
    }
}
