package ru.job4j.wait;

import java.util.Queue;
import java.util.LinkedList;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int limit = 10;

    public synchronized void offer(T value) {
        while (this.queue.size() == this.limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }

        T value = this.queue.poll();
        return value;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
