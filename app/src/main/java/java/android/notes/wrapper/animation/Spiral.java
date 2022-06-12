package java.android.notes.wrapper.animation;

public class Spiral {
    private double x = 2,y;

    public void reset(){
        x = 10;y = 0;
    }

    public void process(){
        double xCopy = ((x - y*0.2)*0.99);
        double yCopy = ((y + x*0.2)*0.99);

        x = xCopy;
        y = yCopy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
