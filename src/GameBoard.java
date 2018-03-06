
/**
 * This class contains grid for plaing and main methods manipulating it
 */
public class GameBoard
{
    // creating grid for playing
    private int[][] grid = new int[10][10];

    /**
     * Constructor for objects of class GameBoard
     */
    public GameBoard(int color1, int color2)
    {
        grid[4][5] = color1;    //putting the first four pieces
        grid[5][4] = color1;
        grid[4][4] = color2;
        grid[5][5] = color2;
        grid[3][4] = 2;
        grid[4][3] = 2;
        grid[6][5] = 2;
        grid[5][6] = 2;
    }

    /**
     * This method returns grid
     * @param No param
     * @return array of integers
     */
    public int[][] getBoard()
    {
        return grid;
    }

    /**
     * This method set value to the chousen box
     * @param int x - row, int y - collumn, int color - value to set
     * @return array of integers
     */
    public void setBox(int x, int y, int color)
    {
        grid[x][y] = color;
    }

    /**
     * It marks boxes with possiple move for player (2 means it is possible to put piece here)
     * @param integers color1 and color2 are markers of player and his oppoent respectively
     * @return No return
     */
    public void makeBoard(int color1, int color2)
    {
        int i;
        for(int x = 0; x < 9; x++)
            for(int y = 0; y < 9; y++)
                if(grid[x][y] == 2)
                    grid[x][y] = 0;
        for(int x = 1; x < 9; x++)
            for(int y = 1; y < 9; y++)
            {
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x+i][y] == color2)
                        i++;
                    if((grid[x+i][y] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x-i][y] == color2)
                        i++;
                    if((grid[x-i][y] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x][y+i] == color2)
                        i++;
                    if((grid[x][y+i] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x][y-i] == color2)
                        i++;
                    if((grid[x][y-i] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x+i][y+i] == color2)
                        i++;
                    if((grid[x+i][y+i] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;;
                    while(grid[x+i][y-i] == color2)
                        i++;
                    if((grid[x+i][y-i] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x-i][y+i] == color2)
                        i++;
                    if((grid[x-i][y+i] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
                if(grid[x][y] == 0)
                {
                    i = 1;
                    while(grid[x-i][y-i] == color2)
                        i++;
                    if((grid[x-i][y-i] == color1)&&(i != 1))
                        grid[x][y] = 2;
                }
            }
    }

    /**
     * This method changes color of opponent's pieces after move is made
     * @param x, y - integer row's and column coordinates, where to put a piece
     * @param color1 and color2 are markers of player and his oppoent respectively
     * @return No parameter
     */
    public void changeColor(int x, int y, int color1, int color2)
    {
        int i = 1, z;
        while(grid[x+i][y] == color2)
            i++;
        if(grid[x+i][y] == color1)
            for(z = x + i; z > x; z--)
                grid[z-1][y] = color1;
        i = 1;
        while(grid[x-i][y] == color2)
            i++;
        if(grid[x-i][y] == color1)
            for(z = x - i; z < x; z++)
                grid[z+1][y] = color1;
        i = 1;
        while(grid[x][y+i] == color2)
            i++;
        if(grid[x][y+i] == color1)
            for(z = y + i; z > y; z--)
                grid[x][z-1] = color1;
        i = 1;
        while(grid[x][y-i] == color2)
            i++;
        if(grid[x][y-i] == color1)
            for(z = y - i; z < y; z++)
                grid[x][z+1] = color1;
        i = 1;
        while(grid[x+i][y+i] == color2)
            i++;
        if(grid[x+i][y+i] == color1)
            for(z = x + i; z > x; z--)
            {
                grid[x+i-1][y+i-1] = color1;
                i--;
            }
        i = 1;
        while(grid[x+i][y-i] == color2)
            i++;
        if(grid[x+i][y-i] == color1)
            for(z = x + i; z > x; z--)
            {
                grid[x+i-1][y-i+1] = color1;
                i--;
            }
        i = 1;
        while(grid[x-i][y-i] == color2)
            i++;
        if(grid[x-i][y-i] == color1)
            for(z = x - i; z < x; z++)
            {
                grid[x-i+1][y-i+1] = color1;
                i--;
            }
        i = 1;
        while(grid[x-i][y+i] == color2)
            i++;
        if(grid[x-i][y+i] == color1)
            for(z = x - i; z < x; z++)
            {
                grid[x-i+1][y+i-1] = color1;
                i--;
            }
    }

    /**
     * This method counts number of boxes with chosen sign
     * @paran int a - boxes with it will be counted
     * @return int number of boxes with chosen sign
     */
    public int getNumber(int a)
    {
        int number = 0;
        for(int i = 1; i < 9; i++)
            for(int j = 1; j < 9; j++)
                if(grid[i][j] == a)
                    number++;
        return number;
    }
}
