package ru.job4j.wait;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ParallelSearch {
    public static void main(String[] args) {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            Thread.sleep(500);
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
