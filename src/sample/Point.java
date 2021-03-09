package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point {
    private int x;
    private int y;

    public Point(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void render(GraphicsContext context,Color color){
        context.setFill(color);
        int rectSize = Play.SCREENWIDTH / Play.COLUMNNUMBER;
        context.fillRect(x * rectSize, y * rectSize,
                rectSize, rectSize);
    }

}
