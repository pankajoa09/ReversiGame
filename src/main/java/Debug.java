import java.util.ArrayList;

/**
 * Created by pjoa09 on 9/17/17.
 */
public class Debug {

    public void printGrid(Block[][] grid,String turn) {

        System.out.println("========================");
        System.out.println("DEBUG PRINT GRID");
        System.out.println("========================");
        System.out.println("Turn: "+turn);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getType().equals("White")){
                    System.out.print("*");
                }
                if (grid[i][j].getType().equals("Black")){
                    System.out.print("&");
                }
                if (grid[i][j].getType().equals("Empty")){
                    System.out.print(" ");
                }
                System.out.print(" ");
                //System.out.println("Real Location" + " " + i + " " + j);
                //System.out.print(grid[i][j].getI() + " " + grid[i][j].getJ() + " " + grid[i][j].getType());
                //System.out.println("Accurate: " + (grid[i][j].getI() == i) + " " + (grid[i][j].getJ() == j));
                //System.out.println(grid[i][j]);

            }
            System.out.println();
        }
        System.out.println("========================");
    }

    public void printContents1DArray(ArrayList<Block> victims){
        for (int i = 0; i<victims.size();i++){
            victims.get(i).printSelf();
        }
    }

    public void printContents2DArray(ArrayList<ArrayList<Block>> victims){
        for (int i = 0; i<victims.size();i++){
            ArrayList<Block> smalllist = victims.get(i);
            for (int j=0; j<smalllist.size();j++){
                smalllist.get(j).printSelf();
            }
        }
    }
}
