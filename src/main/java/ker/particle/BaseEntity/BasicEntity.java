package ker.particle.BaseEntity;

import ker.particle.Constants;
import ker.particle.entity.BallEntity;
import ker.particle.global.Liquid;
import ker.particle.points.PVector;

import java.awt.*;

/**
 * Created by kierpagdato on 6/20/17.
 */
public class BasicEntity {

    protected PVector location;
    protected PVector location_origin;
    protected PVector velocity;
    protected PVector acceleration;

    protected int width;
    protected int height;
    protected float topSpeed;
    protected float mass;
    protected float G;

    protected Color color;


//    location topspeed mass color
    public BasicEntity(float x, float y, int width, int height, float topSpeed, float mass, Color color) {

        location = new PVector(x, y);
        this.width = width;
        this.height = height;

        location_origin = new PVector(x + (width / 2), y + (height / 2));

        this.color = color;

        this.topSpeed = topSpeed;
        this.mass = mass;

        setup();
    }

    protected void setup(){

        velocity = new PVector(0f, 0f);
        acceleration = new PVector(0f, 0f);

        G = 1f;
    }

    public void checkEdges(){

        if (location.getX() + width > Constants.CANVAS_WIDTH) {
            location.setX(Constants.CANVAS_WIDTH - width);
            velocity.setX(velocity.getX() * -1f);
        } else if (location.getX() < 0f) {
            velocity.setX(velocity.getX() * -1f);
            location.setX(0f);
        }

        if (location.getY() + height > Constants.CANVAS_HEIGHT) {
            velocity.setY(velocity.getY() * -1f);
            location.setY(Constants.CANVAS_HEIGHT - height);
        }else if(location.getY() < 0f){
            velocity.setY(velocity.getY() * -1f);
            location.setY(0f);
        }
    }

    public boolean isInsideLiquid(Liquid l){

        if(location.getX() > l.getX() && location.getX() < l.getX() + l.getWidth() && location.getY() > l.getY() && location.getY() < l.getY() + l.getHeight()){
            return true;
        }else{
            return false;
        }
    }

    public void applyForce(PVector force){
        PVector f = PVector.div(force, mass);
        acceleration.add(f);
    }


    public PVector attract(BallEntity ballEntity){

        PVector force = PVector.sub(location, ballEntity.getLocation());
        float distance = force.mag();
        distance = constrain(distance, 5f, 25f);

        force.normalize();

        float strength = (G * mass * ballEntity.getMass()) / (distance * distance);

        force.mult(strength);

        return force;
    }

    private float constrain(float distance, float min, float max){

        if(distance < min)
            return min;
        if(distance > max)
            return max;

        return distance;
    }

    public float getMass() {
        return mass;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public PVector getLocation() {
        return location;
    }

    public PVector getLocation_origin() {
        return location_origin;
    }
}
