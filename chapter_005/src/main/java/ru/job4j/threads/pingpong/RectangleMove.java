package ru.job4j.threads.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int x = 0;
    private int y = 0;
    private int dx = 10;
    private int dy = 2;
    private int limitX;
    private int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        while (true) {
            x = x + dx;
            y = y + dy;
            if (x + rect.getWidth() >= limitX) {
                x = limitX - (int) rect.getWidth();
                dx = -dx;
            }
            if (x < 0) {
                x = 0;
                dx = -dx;
            }
            if (y + rect.getHeight() >= limitY) {
                dy = -dy;
            }
            if (y < 0) {
                y = 0;
                dy = -dy;
            }
            this.rect.setX(x);
            this.rect.setY(y);
            System.out.println(this.rect.getX());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
