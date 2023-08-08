package Graphic;

import Pieces.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Game implements MouseListener, MouseMotionListener {
    private static JFrame frame;
    private static Board board;
    private static String fenNotation;
    private static Piece[][] pieces;

    public Game() {
        fenNotation = "rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2";
        frame = new JFrame();
        board = new Board(920, 920);
        frame.setTitle("Chess");
        frame.setSize(920, 948);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(board.getPanel());
    }

    public static void main(String[] args) {
        new Game();
        //pieces = Board.generatePieces(fenNotation);

        Pawn pawn = new Pawn("pawn", true, 7,0);
        pawn.setImage("./images/white_pawn.png");
        JPanel current = Board.getSquares()[0][0].squarePanel();
        current.add(pawn.getImage());

        frame.setVisible(true);
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
}
