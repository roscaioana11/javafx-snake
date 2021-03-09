package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

import static sample.Play.COLUMNNUMBER;

public class Grid {
    public Point[][] mapGrid;
    public Snake snake;
    public Food food;

    public Grid() {
        this.mapGrid = new Point[COLUMNNUMBER][COLUMNNUMBER];
        for(int i = 0; i < COLUMNNUMBER; i++){
            for (int j = 0; j < COLUMNNUMBER; j++){
                mapGrid[i][j] = new Point(i, j);
            }
        }
        reset();
    }

    public void render(GraphicsContext context){
        for(int i = 0; i < COLUMNNUMBER; i++){
            for (int j = 0; j < COLUMNNUMBER; j++){
                mapGrid[i][j].render(context, Color.BLACK);
            }
        }
        food.render(context, Color.GOLDENROD);
        snake.render(context, Color.FORESTGREEN);
    }

    public int update(){
        //TODO change food location to space that isn't occupied by snake
        int status = snake.update(food);
        if(status == 1){
            Random random = new Random();
            food.changeFoodLocation(random.nextInt(COLUMNNUMBER), random.nextInt(COLUMNNUMBER));
        }else if(status == -1){
            return -1;
        }
        return 0;
    }

    public void reset(){
        Random random = new Random();
        snake = new Snake(new Point(random.nextInt(COLUMNNUMBER),random.nextInt(COLUMNNUMBER)), "UP");
        food = new Food(new Point(random.nextInt(COLUMNNUMBER),random.nextInt(COLUMNNUMBER)));
    }
}
