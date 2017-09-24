import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pjoa09 on 9/20/17.
 */
public class Robot {

    Engine engine = new Engine();
    Debug debug = new Debug();



    public Block choice(Block[][] theirGrid,String turn){
        Board baard = new Board();
        baard.createBoard();
        Block[][] ourGrid = baard.getGrid();
        for (int i=0; i<theirGrid.length;i++){
            for (int j=0; j<theirGrid.length;j++){
                Block block = new Block();
                block.setAll(theirGrid[i][j].getI(),theirGrid[i][j].getJ(),theirGrid[i][j].getType());
                baard.addBlock(block);
            }
        }
        Random rand = new Random();
        int i;
        int j;
        Block block = new Block();

        while (true) {
            i = rand.nextInt(7);
            j = rand.nextInt(7);

            block = baard.getBlockFromGrid(i,j);
            if (engine.isPermissible(block,turn,baard)){
                break;
            }
        }


        return block;
    }




}
