package ker.particle.entity;

import ker.particle.BaseEntity.BasicEntity;
import ker.particle.BaseEntity.interfaces.IBasicEntity;
import ker.particle.enums.Direction;
import ker.particle.points.PVector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by kierpagdato on 6/21/17.
 */
public class CarEntity extends BasicEntity implements IBasicEntity{

    private Rectangle2D shape;
    private PVector car2dAccel = new PVector(0.001f, 0f);

    private boolean isValidKey = false;

    public CarEntity(float x, float y, int width, int height, float topSpeed, float mass, Color color) {
        super(x, y, width, height, topSpeed, mass, color);
    }

    @Override
    public void setup() {

        super.setup();

    }

    @Override
    public void update() {

        velocity.add(acceleration);
        velocity.limit(topSpeed);
        location.add(velocity);

        checkEdges();
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        graphics2D.setColor(color);

        graphics2D.drawString("V: " + velocity.getX(), location.getX(), location.getY() - 15);
        graphics2D.drawString("A: " + acceleration.getX(), location.getX(), location.getY() - 5);

        shape = new Rectangle2D.Double(location.getX(), location.getY(), width, height);
        graphics2D.fill(shape);
    }


    public void translateKeyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                acceleration.add(car2dAccel);
                isValidKey = true;
                break;
            case KeyEvent.VK_LEFT:
                acceleration.sub(car2dAccel);
                isValidKey = true;
                break;
        }
    }

    public void translateKeyReleased(KeyEvent e){
//        reset the acceleration
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                acceleration.mult(0.0f);
                isValidKey = false;
                break;
            case KeyEvent.VK_LEFT:
                acceleration.mult(0.0f);
                isValidKey = false;
                break;
        }
    }

    private Direction get2dDirection(){
        if(velocity.getX() > 0){
            return Direction.RIGHT;
        }else if(velocity.getX() < 0){
            return Direction.LEFT;
        }

        return Direction.REST;
    }

}
