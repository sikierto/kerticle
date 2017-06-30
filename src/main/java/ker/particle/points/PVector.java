package ker.particle.points;

import ker.particle.utils.RandomUtils;

/**
 * Created by kierpagdato on 6/20/17.
 */
public class PVector {

    private float x;
    private float y;


    public PVector(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public void add(PVector v){
        x = x + v.x;
        y = y + v.y;
    }

    public static PVector add(PVector v1, PVector v2){
        return new PVector(v1.x + v2.x, v1.y + v2.y);
    }

    public void sub(PVector v){
        x = x - v.x;
        y = y - v.y;
    }

    public static PVector sub(PVector v1, PVector v2){
        return new PVector(v1.x - v2.x, v1.y - v2.y);
    }

    public void mult(float n){
        x = x * n;
        y = y * n;
    }

    public static PVector mult(PVector v1, float n){
        return new PVector(v1.x * n, v1.y * n);
    }

    public void div(float n){
        x = x / n;
        y = y / n;
    }

    public static PVector div(PVector v1, float n){
        return new PVector(v1.x / n, v1.y / n);
    }

    public float mag(){
        return (float)Math.sqrt(x*x + y*y);
    }

    public void normalize(){
        float m = mag();
        if(m != 0){
            div(m);
        }
    }

    public void limit(float max){
        if(mag() > max){
            normalize();
            mult(max);
        }
    }

    public static PVector random2D(){
        return new PVector((float) RandomUtils.generateRandom(-1,1), (float) RandomUtils.generateRandom(-1,1));
    }

    public void update(float x, float y){
        this.x = x;
        this.y = y;
    }

    public PVector get(){
        return new PVector(getX(), getY());
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PVector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
