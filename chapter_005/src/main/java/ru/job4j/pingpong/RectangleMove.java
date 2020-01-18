package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        int dx = 10;
        int dy = 2;
        while (!Thread.currentThread().isInterrupted()) {
            if (rect.getX() + rect.getWidth() + dx >= limitX) {
                dx = -dx;
                this.rect.setX(limitX);
            }
            if (rect.getX() + dx < 0) {
                this.rect.setX(0);
                dx = -dx;
            }
            if (rect.getY() + dy < 0) {
                this.rect.setY(0);
                dy = -dy;
            }
            if (rect.getY() + dy >= limitY) {
                dy = -dy;
                this.rect.setY(limitY);
            }
            this.rect.setX(rect.getX() + dx);
            this.rect.setY(rect.getY() + dy);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
