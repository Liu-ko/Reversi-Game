/**
 * ReversiGui
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ReversiGui implements ActionListener
{
    // Default filename to use for saving and loading files
    // Possible improvement: replace with a FileChooser
    private final static String DEFAULT_FILENAME = "ReversiLastGame.txt";
    private int GRID_SIZE = 8;
    private static JFrame frame = null;
    private JButton [] buttonArray;
    private JButton [] menuArray = new JButton [8];
    private Player player1 = new Player("Vlad", 1);
    private Player player2 = new Player();
    private JLabel score[] = new JLabel[2];
    private JLabel who = new JLabel("", SwingConstants.CENTER);
    private GameBoard board = new GameBoard(player1.getColor(), player2.getColor());
    private int turn = 1;
    private int way = 1;
    private final int EXIT = 100, GAME1 = 101, GAME2 = 102, RULES = 103, LOAD = 104, SAVE = 105, RESTART = 106, FORFEIT = 107;
    private Color color = new Color(208, 255, 224);

    public JMenuBar createMenu()
    {
        JMenuBar menuBar  = new JMenuBar();;
        JMenu menu = new JMenu("Reversi Menu");
        JMenuItem menuItem;

        menuBar.add(menu);

        // A group of JMenuItems. You can create other menu items here if desired
        menuItem = new JMenuItem("New Game vs. Computer");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("New Game vs. Player");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Rules");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a submenu
        menu.addSeparator();
        return menuBar;
    }

    /**
     * This method creates Menu Panel for the game
     * @param No parameters
     * @return JPanel Container with Main Menu items
     */
    public Container createMenuPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(color);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);
        c.gridwidth = GridBagConstraints.REMAINDER;

        menuArray[0] = new JButton("Player vs. Computer");
        menuArray[0].setActionCommand("" + GAME1);
        menuArray[0].addActionListener(this);
        panel.add(menuArray[0], c);

        menuArray[1] = new JButton("Player vs. Player");
        menuArray[1].setActionCommand("" + GAME2);
        menuArray[1].addActionListener(this);
        panel.add(menuArray[1], c);

        menuArray[2] = new JButton("Load game");
        menuArray[2].setActionCommand("" + LOAD);
        menuArray[2].addActionListener(this);
        panel.add(menuArray[2], c);

        menuArray[3] = new JButton("Rules");
        menuArray[3].setActionCommand("" + RULES);
        menuArray[3].addActionListener(this);
        panel.add(menuArray[3], c);

        menuArray[4] = new JButton("EXIT");
        menuArray[4].setActionCommand("" + EXIT);
        menuArray[4].addActionListener(this);
        panel.add(menuArray[4], c);
        return panel;
    }

    /**
     * This method creates the main Panel for the game, where to play
     * @param No parameters
     * @return JPanel Container that is displayed during the game process
     */
    public Container createContentPane()
    {
        Container panel = new Container();
        panel.setLayout(new BorderLayout());
        panel.add(createHeader(), BorderLayout.NORTH);      //area displaying whose turn is it
        panel.add(createGameMenu(), BorderLayout.EAST);     //area displaying the menu during the game process
        panel.add(createGrid());                            //grid with buttons where to play
        panel.setBackground(color);
        return panel;
    }

    /**
     * This method creates area responsible for whose turn is it
     * @param No parameters
     * @return JPanel JComponent displaying the name of current player
     */
    public JComponent createHeader()
    {
        if(turn == 1)
            who.setText(player1.getName() + "\'s turn");
        else
            who.setText(player2.getName() + "\'s turn");
        who.setFont(new Font(who.getFont().getName(), Font.PLAIN, 50));
        who.setBackground(color);
        who.setOpaque(true);                //allow to display another background
        return who;
    }

    /**
     * This method is responsible for changing score and current player's score
     * @param No parameters
     * @return No parameters
     */
    public void changeHeader()
    {
        if(turn == 1)
            who.setText(player1.getName() + "\'s turn");
        else
            who.setText(player2.getName() + "\'s turn");
        score[0].setText(player1.getName() + ": " + player1.getScore());
        score[1].setText(player2.getName() + ": " + player2.getScore());
    }

    /**
     * This method creates area with game's second menu and area containing score
     * @param No parameters
     * @return JPanel JComponent containing menu and score area
     */
    protected JComponent createGameMenu()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(color);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 5, 0, 5);
        c.gridwidth = GridBagConstraints.REMAINDER;

        score[0] = new JLabel(player1.getName() + ": " + player1.getScore());
        score[0].setBorder(BorderFactory.createLineBorder(Color.black));
        score[0].setFont(new Font(who.getFont().getName(), Font.PLAIN, 30));
        score[0].setBackground(new Color(56, 69, 192));
        score[0].setOpaque(true);
        panel.add(score[0], c);
        score[1] = new JLabel(player2.getName() + ": " + player2.getScore());
        score[1].setBorder(BorderFactory.createLineBorder(Color.black));
        score[1].setFont(new Font(score[1].getFont().getName(), Font.PLAIN, 30));
        score[1].setBackground(new Color(108, 207, 239));
        score[1].setOpaque(true);
        panel.add(score[1], c);

        c.insets = new Insets(20, 20, 20, 20);

        menuArray[5] = new JButton("Restart");
        menuArray[5].setActionCommand("" + RESTART);
        menuArray[5].addActionListener(this);
        panel.add(menuArray[5], c);

        menuArray[6] = new JButton("Forfeit");
        menuArray[6].setActionCommand("" + FORFEIT);
        menuArray[6].addActionListener(this);
        panel.add(menuArray[6], c);

        menuArray[7] = new JButton("Save game");
        menuArray[7].setActionCommand("" + SAVE);
        menuArray[7].addActionListener(this);
        panel.add(menuArray[7], c);

        panel.add(menuArray[2], c);
        panel.add(menuArray[4], c);

        panel.setPreferredSize(new Dimension(200, 100));
        return panel;
    }

    public JComponent createGrid()
    {
        int numButtons = GRID_SIZE * GRID_SIZE;
        JPanel grid = new JPanel(new GridLayout(GRID_SIZE,GRID_SIZE));
        grid.setBackground(color);
        buttonArray = new JButton[numButtons];

        for (int i=0; i<numButtons; i++)
        {
            buttonArray[i] = new JButton(" ");

            // This label is used to identify which button was clicked in the action listener
            buttonArray[i].setActionCommand("" + i); // String "0", "1" etc.
            buttonArray[i].addActionListener(this);
            buttonArray[i].setFont(new Font(buttonArray[i].getFont().getName(), Font.PLAIN, 30));
            grid.add(buttonArray[i]);
        }
        fillGrid();
        return grid;
    }

    /**
     * This method is displaying grid with buttons to make move for player
     * @param No parameters
     * @return No parameters
     */
    public void fillGrid()
    {
        int row, col, simbol;
        for(row = 1; row < 9; row++)
        {
            for(col = 1; col < 9; col++)
            {
                simbol = board.getBoard()[row][col];
                switch (simbol)
                {
                    case 0: setGuiSquare(row-1, col-1, ' ');
                        break;
                    case 1: setGuiSquare(row-1, col-1, '�');
                        break;
                    case 2: setGuiSquare(row-1, col-1, '*');
                        break;
                    case 8: setGuiSquare(row-1, col-1, 'o');
                        break;
                }
            }
        }
    }

    /**
     * This method handles events from the Menu and the board.
     * @param No parameters
     * @return No parameters
     */
    public void actionPerformed(ActionEvent e)
    {
        String classname = getClassName(e.getSource());
        JComponent component = (JComponent)(e.getSource());

        if (classname.equals("JMenuItem"))
        {
            JMenuItem menusource = (JMenuItem)(e.getSource());
            String menutext  = menusource.getText();

            // Determine which menu option was chosen
            if (menutext.equals("Load Game"))
            {
                /* ReversiGUI    Add your code here to handle Load Game **********/
                loadGame();
            }
            else if (menutext.equals("New Game vs. Computer"))
            {
                /* ReversiGUI    Add your code here to handle Save Game **********/
                createGame1();
            }
            else if (menutext.equals("New Game vs. Player"))
            {
                /* ReversiGUI    Add your code here to handle Save Game **********/
                createGame2();
            }
            else if (menutext.equals("Rules"))
            {
                /* ReversiGUI    Add your code here to handle Save Game **********/
                rules();
            }
            else if (menutext.equals("Exit"))
            {
                /* ReversiGUI    Add your code here to handle Save Game **********/
                System.exit(0);
            }
        }
        // Handle the event from the user clicking on a command button
        else if (classname.equals("JButton"))
        {
            JButton button = (JButton)(e.getSource());
            int bnum = Integer.parseInt(button.getActionCommand());
            if(bnum < 100)
            {
                int row = bnum / GRID_SIZE;
                int col = bnum % GRID_SIZE;

                /* ReversiGUI    Add your code here to handle user clicking on the grid ***********/
                if(turn == 1)
                {
                    if(makeMove(player1.getColor(), player2.getColor(), row+1, col+1))
                        turn = 2;
                    else JOptionPane.showMessageDialog(null, "It is imposible to put your piece here", "Error", JOptionPane.ERROR_MESSAGE);
                    if((way == 1)&&(board.getNumber(2) != 0)&&(turn == 2))
                    {
                        computer();
                        turn = 1;
                    }
                }
                else
                {
                    if(makeMove(player2.getColor(), player1.getColor(), row+1, col+1))
                        turn = 1;
                    else JOptionPane.showMessageDialog(null, "It is imposible to put your piece here", "Error", JOptionPane.ERROR_MESSAGE);
                }
                fillGrid();
                changeHeader();
                if(board.getNumber(2) == 0)         //check if there are any options for the next player to make move
                {
                    if(turn == 1)
                    {
                        board.makeBoard(player2.getColor(), player1.getColor());        //change board for another player
                        if(board.getNumber(2) != 0)     //check if there are any options for another player to make move
                        {                               // if yes then prepare move for another player
                            JOptionPane.showMessageDialog(null, "You do not have any options where to put your piece. The turn passes on to another player");
                            fillGrid();
                            turn = 2;
                            changeHeader();
                            if(way == 1)        //if another player is a Computer then make its move
                            {
                                computer();
                                fillGrid();
                                changeHeader();
                                if(board.getNumber(2) != 0)
                                    turn = 1;
                                else result();
                            }
                        }
                        else
                        {
                            fillGrid();
                            changeHeader();
                            result();
                        }
                    }
                    else
                    {
                        board.makeBoard(player1.getColor(), player2.getColor());
                        if(board.getNumber(2) != 0)
                        {
                            JOptionPane.showMessageDialog(null,"You do not have any options where to put your piece. The turn passes on to another player");
                            fillGrid();
                            turn = 2;
                            changeHeader();
                        }
                        else
                        {
                            fillGrid();
                            changeHeader();
                            result();
                        }
                    }
                }
            }
            // actions for other buttons used in the game
            else switch(bnum)
            {
                case GAME1: createGame1(); break;
                case GAME2: createGame2(); break;
                case EXIT: System.exit(0); break;
                case LOAD: loadGame(); break;
                case SAVE: saveGame(); break;
                case RULES: rules(); break;
                case FORFEIT: forfeit(); break;
                case RESTART: restart(); break;

            }
        }
    }

    /**
     *  Returns the class name
     */
    protected String getClassName(Object o)
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /**
     * Create the GUI and show it.
     * For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI()
    {
        // Create and set up the window.
        frame = new JFrame("ReversiGui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        ReversiGui Reversigui = new ReversiGui();
        frame.setJMenuBar(Reversigui.createMenu());
        frame.setContentPane(Reversigui.createMenuPanel());

        // Display the window, setting the size
        frame.setSize(650, 600);
        frame.setVisible(true);
    }

    /**
     * Sets a Gui grid square at row, col to display a character
     * @param int row, int col, char c
     */
    public boolean setGuiSquare(int row, int col, char c)
    {
        int bnum = row * GRID_SIZE + col;
        if (bnum >= (GRID_SIZE*GRID_SIZE))
        {
            return false;
        }
        else
        {
            buttonArray[bnum].setText(Character.toString(c));
        }
        return true;
    }

    /**
     * This is a standard main function for a Java GUI
     */
    public static void main(String[] args)
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    /**
     * This method is responsible for making players move in general
     * @param int color1 - current player's sign, int color2 - another player's sign, int x - row, int y - row;
     * @return boolean if it is possible to make this move;
     */
    public boolean makeMove(int color1, int color2, int x, int y)
    {
        boolean right = false;
        if((x > 0)&&(x < 9)&&(y < 9)&&(y > 0))
            if (board.getBoard()[x][y] == 2)
            {
                right = true;
                board.changeColor(x, y, color1, color2);
                board.makeBoard(color2, color1);
            }
        player1.setScore(board.getBoard());
        player2.setScore(board.getBoard());
        return right;
    }

    /**
     * This method makes move for computer
     * @param No parameters
     * @return No parameters
     */
    public void computer()
    {
        int number, choice;
        int x = 1, y = 1;
        number = board.getNumber(2);
        choice = (int) Math.random()*number + 1;
        for(int i = 1; i < 9; i++)
            for(int j = 1; j < 9; j++)
                if(board.getBoard()[i][j] == 2)
                {
                    choice--;
                    if(choice == 0)
                    {
                        x = i;
                        y = j;
                    }
                }
        board.changeColor(x, y, player2.getColor(), player1.getColor());
        board.makeBoard(player1.getColor(), player2.getColor());
        player1.setScore(board.getBoard());
        player2.setScore(board.getBoard());
    }

    //************************************************************************
    //*** ReversiGUI: Modify the methods below to respond to Menu and Mouse click events

    /**
     * This method creates game for one real player, promts player to write his name
     * @param No parameters
     * @return No parameters
     */
    public void createGame1()
    {
        way = 1;
        player1.setName(JOptionPane.showInputDialog(null, "What's the name of the first player?"));
        if(player1.getName() == null)
            player1.setName("Vlad");
        board = new GameBoard(player1.getColor(), player2.getColor());
        frame.setContentPane(createContentPane()); frame.setVisible(true);
    }

    /**
     * This method creates game for two real players, promts players to write their names
     * @param No parameters
     * @return No parameters
     */
    public void createGame2()
    {
        way = 2;
        player1.setName(JOptionPane.showInputDialog(null, "What's the name of the first player?"));
        if(player1.getName() == null)
            player1.setName("Vlad");
        player2.setName(JOptionPane.showInputDialog(null, "What's the name of the second player?"));
        if(player2.getName() == null)
            player2.setName("Evi");
        board = new GameBoard(player1.getColor(), player2.getColor());
        frame.setContentPane(createContentPane()); frame.setVisible(true);
    }

    /**
     * This method loads the last saved game
     * @param No parameters
     * @return No parameters
     */
    public void loadGame()
    {
        String information[] = new String[11];
        String separated[] = new String[10];
        File file = new File(DEFAULT_FILENAME);
        if(file.exists())
        {
            information = read();
            separated = information[0].split(" ");
            player1.setName(separated[0]);
            separated = information[1].split(" ");
            player2.setName(separated[0]);
            try
            {
                if(information[2].equals("2"))
                    turn = 2;
                for(int i = 3; i < 11; i++)
                {
                    separated = information[i].split(" ");
                    for(int j = 0; j < 8; j++)
                        board.setBox(i-2, j+1, Integer.parseInt(separated[j]));
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "The process of loading the file failed. We start a new game for you.", "Error", JOptionPane.ERROR_MESSAGE);
                board = new GameBoard(player1.getColor(), player2.getColor());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You do not have any saved game to load. We start a new game for you.", "Error", JOptionPane.ERROR_MESSAGE);
            board = new GameBoard(player1.getColor(), player2.getColor());
        }
        player1.setScore(board.getBoard());
        player2.setScore(board.getBoard());
        frame.setContentPane(createContentPane()); frame.setVisible(true);
        fillGrid();
        Object[] option = {"with Computer", "with Player"};         //text for buttons used below
        int response = JOptionPane.showOptionDialog(null, "Do you want to play with computer or another player?", "A Silly Question", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if(response == JOptionPane.YES_OPTION)
        {
            way = 1;
            if(turn == 2)       //make move if it is Computer's turn
            {
                changeHeader();
                computer();
                turn = 1;
                if(board.getNumber(2) == 0)
                {
                    fillGrid();
                    changeHeader();
                    result();
                }
            }
        }
        else if(response == JOptionPane.NO_OPTION)
            way = 2;
        else
        {
            way = 1;
            JOptionPane.showMessageDialog(null, "You missed the button. So I decide for you. Play with Computer");
        }
    }

    /**
     * This method reads file to load
     * @param No parameters
     * @return array with information from the file
     */
    public String[] read()
    {
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        String nextLine;
        String[] information = new String[11];
        int line = 0;
        try
        {
            fileReader = new FileReader(DEFAULT_FILENAME);
            bufferedReader = new BufferedReader(fileReader);
            nextLine = bufferedReader.readLine();
            while(nextLine != null)
            {
                information[line] = nextLine;
                line++;
                nextLine = bufferedReader.readLine();
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "The problem appears openning the file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            if(fileReader != null)
                try
                {
                    fileReader.close();
                }
                catch(IOException e)
                {
                    JOptionPane.showMessageDialog(null, "The problem appears trying to close the file", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
        return information;
    }

    /**
     * This method saves game as a file
     * @param No parameters
     * @return No parameters
     */
    public void saveGame()
    {
        int board[][] = this.board.getBoard();
        FileOutputStream file = null;
        PrintWriter printWriter = null;
        try
        {
            file = new FileOutputStream(DEFAULT_FILENAME);
            printWriter = new PrintWriter(file);
            printWriter.println(player1.getName() + " " + player1.getScore());
            printWriter.println(player2.getName() + " " + player2.getScore());
            printWriter.println(turn);
            for(int i = 1; i < 9; i++)
            {
                for(int j = 1; j < 9; j++)
                    printWriter.print(board[i][j] + " ");
                printWriter.println();
            }
            JOptionPane.showMessageDialog(null,"The game is saved");
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Oups, the problem accur while trying to save the game", "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            if(printWriter != null)
                try
                {
                    printWriter.close();
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Oups, the problem accur while trying to close the file", "Error", JOptionPane.ERROR_MESSAGE);
                }
        }
    }

    /**
     * This method starts a new game with the same players' names
     * @param No parameters
     * @return No parameters
     */
    public void restart()
    {
        board = new GameBoard(player1.getColor(), player2.getColor());
        turn = 1;
        player1.setScore(board.getBoard());
        player2.setScore(board.getBoard());
        fillGrid();
        changeHeader();
    }

    /**
     * This method displays window with result of the game when the game ended
     * @param No parameters
     * @return No parameters
     */
    public void result()
    {
        String text = null;
        if(player1.getScore() > player2.getScore())
            text = player1.getName() + " wins!";
        else if(player2.getScore() > player1.getScore())
            text = player2.getName() + " wins!";
        else
            text = "Neither player wins. It is a draw!";
        displayScore(text);
        frame.setContentPane(createMenuPanel());
        frame.setVisible(true);
    }

    /**
     * This method creates window with results of the game to display
     * @param No parameters
     * @return No parameters
     */
    public void displayScore(String text)
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        JLabel label = new JLabel(text);
        panel.add(label, c);
        label = new JLabel(player1.getName() + " ");
        panel.add(label);
        label = new JLabel(player1.getScore() + " : " + player2.getScore(), JLabel.CENTER);
        label.setPreferredSize(new Dimension (50, 30));
        label.setBackground(new Color(85, 242, 189));
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setOpaque(true);
        panel.add(label);
        label = new JLabel(" " + player2.getName());
        panel.add(label);
        JOptionPane.showMessageDialog(null, panel, "Game Ends", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method displays window with result of the game when one of player forfeits
     * @param No parameters
     * @return No parameters
     */
    public void forfeit()
    {
        String text = null;
        if(turn == 1)
            text = player1.getName() + " forfeits, " + player2.getName() + " wins!";
        else
            text = player2.getName() + " forfeits, " + player1.getName() + " wins!";
        displayScore(text);
        frame.setContentPane(createMenuPanel());
        frame.setVisible(true);
    }

    /**
     * This method displays rules of the game
     * @param No parameters
     * @return No parameters
     */
    public void rules()
    {
        String text = "First players - Black pieces\nSecond player - White pieces\nBoxes where you can put your piece - Star\n\n";
        String text2 = "The grid displays the current situation with black and white pieces of two players. Taking turn, you can \n";
        text2 = text2 + "place one of your pieces on the board. After your move all opponent's pieces between your new piece and\n";
        text2 = text2 + "old ones (in all directions) will be flipped over to have your color. Then the turn goes to the opponent.\n";
        text2 = text2 + "The game ends when no one of players can make a move. However, if one player can not make a valid move,\n";
        text2 = text2 + "but another player can, the play passes back to the other player.";
        JOptionPane.showMessageDialog(null, text + text2);
        //JOptionPane.showMessageDialog(null, "Hey! There we are! Almost rules... Not done yet");
    }

}
