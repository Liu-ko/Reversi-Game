
/**
 * This class contains information about player
 */
public class Player
{
    private int score;
    private String name;
    private int color;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        score = 2;
        name = "Computer";
        color = 8;
    }

    /**
     * Constructor for objects of class Player
     */
    public Player(String name, int color)
    {
        // initialise instance variables
        score = 2;
        this.name = name;
        this.color = color;
    }

    /**
     * Method that allows to set player's name.
     * @param String name - player's name to be set
     * @return No parameter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method that allows to set player's color.
     * @param int color - players sign for the game
     * @return No parameter
     */
    public void setColor(int color)
    {
        this.color = color;
    }

    /**
     * Method that allows to set player's score.
     * @param array of integers containing the game's grid
     * @return No parameter
     */
    public void setScore(int board[][])
    {
        int score = 0;
        for (int i = 1; i < 9; i++)
            for (int j = 1; j < 9; j++)
                if(board[i][j] == color)
                    score++;
        this.score = score;
    }

    /**
     * Method that allows to get player's name.
     * @param No parameter
     * @return String player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method that allows to get player's score.
     * @param No parameter
     * @return integer player's score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Method that allows to get player's color.
     * @param no parameter
     * @return int player's sing
     */
    public int getColor()
    {
        return color;
    }
}
