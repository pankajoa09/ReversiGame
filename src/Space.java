import com.sun.tools.javac.util.*;
import javafx.util.*;

/**
 * Created by pjoa09 on 9/12/17.
 */
public class Space {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Space getOnNorth() {
        return onNorth;
    }

    public Space getOnSouth() {
        return onSouth;
    }

    public Space getOnEast() {
        return onEast;
    }

    public Space getOnWest() {
        return onWest;
    }

    public Space getOnNorthEast() {
        return onNorthEast;
    }

    public Space getOnNorthWest() {
        return onNorthWest;
    }

    public Space getOnSouthEast() {
        return onSouthEast;
    }

    public Space getOnSouthWest() {
        return onSouthWest;
    }

    private Space onNorth;
    private Space onSouth;
    private Space onEast;
    private Space onWest;
    private Space onNorthEast;
    private Space onNorthWest;
    private Space onSouthEast;
    private Space onSouthWest;


    public void set(int X,int Y){
        System.out.println("we are all gonna die");
        System.out.println("x "+X);
        System.out.println("y "+Y);
        this.x=X;
        this.y=Y;
        this.onNorth.set(x,y-1);
        this.onSouth.set(x,y+1);
        this.onEast.set(x+1,y);
        this.onWest.set(x-1,y);
        this.onNorthEast.set(x+1,y-1);
        this.onNorthWest.set(x-1,y-1);
        this.onSouthEast.set(x+1,y+1);
        this.onSouthWest.set(x-1,y+1);
    }





}
