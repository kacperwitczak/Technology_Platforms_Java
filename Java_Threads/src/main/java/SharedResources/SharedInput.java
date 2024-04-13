package SharedResources;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SharedInput {
    private Queue<Integer> tasks;
    private volatile AtomicBoolean isClosed = new AtomicBoolean(false);

    public SharedInput() {
        tasks = new LinkedList<Integer>();
    }

    public synchronized void addTask(Integer task) {
        System.out.println("Adding task");
        tasks.add(task);

        notify();
    }

    public synchronized Integer getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            System.out.println("Waiting for tasks");

            wait();
            if (tasks.isEmpty() && isClosed.get()) {
                return null;
            }
        }

        System.out.println("Got the task");
        return tasks.poll();
    }

    public synchronized int getSize() {
        return tasks.size();
    }

    public synchronized void close() {
        isClosed.set(true);
        notifyAll();
    }
}
