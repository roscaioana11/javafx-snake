package sample;

public class Food {
    public Point food;

    public Food(Point food) {
        this.food = food;
    }

    public void changeFoodLocation(int foodX,int foodY){
        food.setX(foodX);
        food.setY(foodY);
    }
}
