package callable_example;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;

public class ResultCheckerExample implements Runnable {

    private final List<RunnableFuture<CallableExample>> runnableFutureList;

    public ResultCheckerExample(List<RunnableFuture<CallableExample>> runnableFutureList) {
        this.runnableFutureList = runnableFutureList;
    }

    public void run() {

        int completedTask = 0;

        while (completedTask != 3) {
            for (Iterator<RunnableFuture<CallableExample>> futureIterator = runnableFutureList.iterator(); futureIterator.hasNext(); ) {
                RunnableFuture<CallableExample> future = futureIterator.next();
                if (completedTask == 2) {
                    completedTask++;
                    future.cancel(true);
                    System.out.println("Оставшаяся задача остановлена, результат её работы не требуется.");
                    break;
                }
                if (future.isDone()) {
                    completedTask++;
                    try {
                        var task = future.get();
                        System.out.printf(
                                "%s выполнилась за: %d миллисекунд%n", task.getTaskName(), task.getTimeToCompute()
                        );
                        futureIterator.remove();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

}
