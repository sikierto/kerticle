package ker.particle.BaseEntity;

import java.awt.*;

/**
 * Created by kierpagdato on 6/29/17.
 */
public class BasicGlobal {


    protected float x;
    protected float y;
    protected float width;
    protected float height;

    protected float c;

    protected Color color;

    public BasicGlobal(float x, float y, float width, float height, float c, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getC() {
        return c;
    }

    public Color getColor() {
        return color;
    }
}
