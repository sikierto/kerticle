package ker.particle.entity;

import ker.particle.BaseEntity.BasicEntity;
import ker.particle.BaseEntity.interfaces.IBasicEntity;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by kierpagdato on 6/29/17.
 */
public class AttractorEntity extends BasicEntity implements IBasicEntity {

    private Ellipse2D.Double shape;


    public AttractorEntity(float x, float y, int width, int height, float topSpeed, float mass, Color color) {
        super(x, y, width, height, topSpeed, mass, color);
    }

    @Override
    protected void setup() {
        super.setup();

        width = (int)(mass * 2f);
        height = (int)(mass * 2f);

        location.setX(location.getX() - (width / 2));
        location.setY(location.getY() - (height / 2));


        shape = new Ellipse2D.Double(location.getX(), location.getY(), width, height);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D graphics2D) {

        graphics2D.setColor(color);
        graphics2D.fill(shape);
    }
}
