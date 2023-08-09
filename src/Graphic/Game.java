package Graphic;

import Pieces.Piece;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Game implements MouseListener, MouseMotionListener {
    private static JFrame frame;
    private static Board board;
    private static String fenNotation;
    private static Piece[][] pieces;
    private static JLabel piece;

    /**
     *
     */
    public Game() {
        fenNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        frame = new JFrame();
        board = new Board(920, 920);
        frame.setTitle("Chess");
        frame.setSize(920, 948);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(board.getPanel());
        board.getPanel().addMouseListener(this);
        board.getPanel().addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public static void main(String[] args) {
        new Game();
        pieces = Board.generatePieces(fenNotation);
        frame.setVisible(true);
    }
}
