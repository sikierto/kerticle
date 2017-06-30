package ker.particle.manager;

import ker.particle.Constants;
import ker.particle.container.SystemCanvas;
import ker.particle.entity.AttractorEntity;
import ker.particle.entity.BallEntity;
import ker.particle.points.PVector;
import ker.particle.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by kierpagdato on 6/16/17.
 */
public class SystemManager {

    private static Logger LOGGER = LoggerFactory.getLogger(SystemManager.class);

    private float interpolation;
    private boolean mousePressed = false;

    private PVector center;

    private BallEntity[] ballEntity;
    private AttractorEntity attractorEntity;

    private Color[] colors = new Color[]{Color.BLACK, Color.ORANGE, Color.CYAN};

    public SystemManager() {

        center = new PVector(Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2);

        ballEntity = new BallEntity[10];
        for(int i = 0; i < 10; i++){
            ballEntity[i] = new BallEntity((float) RandomUtils.generateRandom(0, Constants.CANVAS_WIDTH), (float) RandomUtils.generateRandom(0, Constants.CANVAS_HEIGHT), 16, 16, 0f, 1f, Color.BLACK);
        }
        attractorEntity = new AttractorEntity((float) Constants.CANVAS_WIDTH / 2, (float) Constants.CANVAS_HEIGHT / 2, 0, 0, 0f, 20f, Color.GREEN);
    }

    public void update(){

        for(int i = 0; i < ballEntity.length; i++){
            for (int j = 0; j < ballEntity.length; j++) {
                if(j != i){
                    PVector force = ballEntity[j].attract(ballEntity[i]);
                    ballEntity[i].applyForce(force);
                }
            }
            ballEntity[i].update();
            ballEntity[i].checkEdges();
        }

    }

    public void draw(Graphics2D graphics2D){


        attractorEntity.draw(graphics2D);

        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("FPS: " + SystemCanvas.frameCount, 5, 10);


        for(int i = 0; i < ballEntity.length; i++){
            ballEntity[i].draw(graphics2D);
        }
    }


    public void translateKeyTyped(KeyEvent e){

    }

    public void translateKeyPressed(KeyEvent e){
    }

    public void translateKeyReleased(KeyEvent e){
    }

    public void translateMousePressed(MouseEvent e){
        mousePressed = true;
    }

    public void translateMouseReleased(MouseEvent e){
        mousePressed = false;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

}
