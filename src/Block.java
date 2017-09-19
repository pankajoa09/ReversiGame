/**
 * Created by pjoa09 on 9/16/17.
 */
public class Block {

    public int getI() {
        return i;
    }


    public int getJ() {
        return j;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int i;
    private int j;
    private String type;




    public void setAll(int i,int j,String type){
        this.i = i;
        this.j = j;
        this.type = type;

    }




}
