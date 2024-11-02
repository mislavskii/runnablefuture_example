package callable_example;

import java.util.concurrent.Callable;

public class CallableExample implements Callable<CallableExample> {

    private final Integer timeToCompute;
    private final String taskName;

    public CallableExample(Integer timeToCompute, String taskName) {
        this.timeToCompute = timeToCompute;
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public Integer getTimeToCompute() {
        return timeToCompute;
    }

    @Override
    public CallableExample call() throws Exception {
        System.out.println(getTaskName() + " выполнится за: " + timeToCompute + " миллисекунд");
        Thread.sleep(timeToCompute);
        return this;
    }
}
