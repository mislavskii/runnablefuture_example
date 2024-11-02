package callable_example;

import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class Main {

    private static final Random random = new Random();
    public static void main(String[] args) {
//        RunnableFuture<Integer> future1 = new FutureTask<>(
//                new CallableExample(12000, "Задача 1")
//        );
//        RunnableFuture<Integer> future2 = new FutureTask<>(
//                new CallableExample(3000, "Задача 2")
//        );
//        RunnableFuture<Integer> future3 = new FutureTask<>(
//                new CallableExample(20000, "Задача 3")
//        );

        Stack<RunnableFuture<CallableExample>> taskList = new Stack<>();
//        taskList.add(future1);
//        taskList.add(future2);
//        taskList.add(future3);

        for (int i = 1; i < 4; i++) {
            taskList.add(new FutureTask<>(
                    new CallableExample(random.nextInt(2000, 25000), "Задача %d".formatted(i))
            ));
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);
        taskList.forEach(executor::execute);

        ResultCheckerExample resultCheckerExample = new ResultCheckerExample(taskList);
        executor.execute(resultCheckerExample);

        executor.shutdown();

        System.out.println("Выполнение основной программы не прерывается");

    }
}