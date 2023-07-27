import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener {

    Board game = new Board();
    static Game newGame = new Game();
    static JFrame frame = new JFrame("2048");
    static Color green;
    String gameBoard = game.toString();


    public static void setUpGUI(){
        frame.addKeyListener( newGame );
        frame.getContentPane().add( newGame );
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("You pressed Down");
            game.downMove();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("You pressed Up");
            game.upMove();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("You pressed Left");
            game.leftMove();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("You pressed Right");
            game.rightMove();
            game.spawn();
            gameBoard = game.toString();
            frame.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) { //need to spawn two new tiles;
            System.out.println("You pressed Enter");
            game = new Board();
            game.spawn();
            game.spawn();
            frame.repaint();
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {}

    public void paint (Graphics g){
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.drawString("2048", 250,20);
        graphics2D.drawString("Score: " + game.getScore(), 200 - 4 * String.valueOf(game.getScore()).length(), 40);
        graphics2D.drawString( "Highest Tile: " + game.getHighestTile(), 280 - 4 * String.valueOf( game.getHighestTile() ).length(),40 );
        graphics2D.drawString("Please press Enter to start the game", 180,315);
        graphics2D.drawString("Use arrow keys to move", 180, 335);
        if (game.isBoardFullBoolean()){
            graphics2D.drawString("Press Enter to restart", 200, 355);
        }
        graphics2D.setColor(Color.gray);
        graphics2D.fillRect(140,50,250, 250);
        for ( int i = 0; i < 4; i++ )
        {
            for ( int j = 0; j < 4; j++ )
            {
                drawTiles( g, game.board[i][j], j * 60 + 150, i * 60 + 60 );
            }
        }
        if (game.isGameOver())
        {
            graphics2D.setColor( Color.gray );
            graphics2D.fillRect( 140, 50, 250, 250 );
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    graphics2D.setColor(Color.RED);
                    graphics2D.fillRoundRect(j * 60 + 150, i * 60 + 60, 50, 50, 5, 5);
                    graphics2D.setColor( Color.black );
                    g.drawString("GAME", j * 60 + 160, i * 60 + 75);
                    g.drawString("OVER", j * 60 + 160, i * 60 + 95);
                }
            }
        }
    }
    public void drawTiles( Graphics g, Tile tile, int x, int y )
    {
        int tileValue = tile.getValue();
        int length = String.valueOf(tileValue).length();
        Graphics2D graphics2D= (Graphics2D)g;
        graphics2D.setColor(Color.lightGray);
        graphics2D.fillRoundRect(x, y, 50, 50, 5, 5);
        graphics2D.setColor(Color.black);
        if (tileValue > 0)
        {
            graphics2D.setColor(tile.getTileColor());
            graphics2D.fillRoundRect(x, y, 50, 50, 5, 5);
            graphics2D.setColor(Color.black);
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    public static void main(String args[]){
        setUpGUI();
        System.out.println("Created Board ");
    }
}
