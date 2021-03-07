package sample;

import javafx.scene.canvas.GraphicsContext;

public class GameLoop implements Runnable{
    private final Grid grid;
    private final GraphicsContext context;
    private static final int FRAMERATE = 1;

    public GameLoop(Grid grid,GraphicsContext context) {
        this.grid = grid;
        this.context = context;
    }

    @Override
    public void run() {

        while (true){
            int status = update();
            if(status == -1){
                break;
            }
            render();
            try {
                Thread.sleep((long) (1000 / FRAMERATE));
            } catch (InterruptedException ignore) {
            }
            System.out.println("loop ended");
        }
    }

    public void render(){
        grid.render(context);
    }

    public int update(){
        return 0;
    }
}
