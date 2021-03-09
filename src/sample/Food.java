package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Food {
    public Point foodPosition;

    public Food(Point food) {
        this.foodPosition = food;
    }

    public void changeFoodLocation(int foodX,int foodY){
        foodPosition.setX(foodX);
        foodPosition.setY(foodY);
    }

    public void render(GraphicsContext context,Color color){
        foodPosition.render(context, color);
    }
}
