package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {
    public Point[][] mapGrid;
    public Snake snake;
    public Food food;

    public Grid(Snake snake,Food food) {
        this.mapGrid = new Point[Play.COLUMNNUMBER][Play.COLUMNNUMBER];
        this.snake = snake;
        this.food = food;
    }

    public void render(GraphicsContext context){
        context.setFill(Color.BLACK);
        int rectSize = Play.SCREENWIDTH / Play.COLUMNNUMBER;
        for(int i = 0; i < Play.COLUMNNUMBER; i++){
            for (int j = 0; j < Play.COLUMNNUMBER; j++){
                //TODO move this into Point
                context.fillRect(i * Play.COLUMNNUMBER, j * Play.COLUMNNUMBER,
                        i * Play.COLUMNNUMBER + rectSize, j * Play.COLUMNNUMBER + rectSize);
            }
        }
        //TODO draw snake, food
    }

    public int update(){
        return 0;
    }
}
