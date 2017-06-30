package ker.particle.entity;

import ker.particle.BaseEntity.BasicEntity;
import ker.particle.BaseEntity.interfaces.IBasicEntity;
import ker.particle.global.Liquid;
import ker.particle.points.PVector;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by kierpagdato on 6/20/17.
 */
public class BallEntity extends BasicEntity implements IBasicEntity{

    private Ellipse2D.Double shape;

    //    coefficient of friction
    protected float c = 0.01f;
    //    normal force
    protected float normal = 1f;
    protected float frictionMag = c * normal;


    public BallEntity(float x, float y, int width, int height, float topSpeed, float mass, Color color) {
        super(x, y, width, height, topSpeed, mass, color);
    }

    @Override
    public void update(){

        velocity.add(acceleration);
        location.add(velocity);

        acceleration.mult(0f);

    }

    @Override
    public void draw(Graphics2D graphics2D){

        graphics2D.setColor(color);

//        to monitor V and A
//        graphics2D.drawString("V: " + velocity.getX(), location.getX(), location.getY() - 15);
//        graphics2D.drawString("A: " + acceleration.getX(), location.getX(), location.getY() - 5);

        shape = new Ellipse2D.Double(location.getX(), location.getY(), width, height);
        graphics2D.fill(shape);
    }

    private PVector friction(){
        PVector friction = velocity.get();
        friction.mult(-1);
        friction.normalize();

        friction.mult(frictionMag);

        return friction;
    }

    public void drag(Liquid l){

        float speed = velocity.mag();
        float dragMagnitude = l.getC() * speed * speed;

        PVector drag = velocity.get();
        drag.mult(-1f);
        drag.normalize();

        drag.mult(dragMagnitude);

        applyForce(drag);

    }
}
