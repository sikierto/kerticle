package ker.particle.global;

import ker.particle.BaseEntity.BasicGlobal;
import ker.particle.BaseEntity.interfaces.IBasicGlobal;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by kierpagdato on 6/29/17.
 */
public class Liquid extends BasicGlobal implements IBasicGlobal{

    private Rectangle2D.Double shape;

    public Liquid(float x, float y, float width, float height, float c, Color color) {
        super(x, y, width, height, c, color);

        shape = new Rectangle2D.Double(x, y, width, height);
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
