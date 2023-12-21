package com.github.grxdiii.chess.main;

import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
import com.github.grxdiii.chess.pieces.Piece;
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;

/**
 * A small implementation of the game Chess using Java's Swing Library. This class starts by creating an
 * array list to store each piece, then produces the board by utilizing a JFrame, JPanels, and a JLayeredPane.
 *
 *
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Game implements MouseListener, MouseMotionListener {
    private static Board board;
    private static Piece selectedPiece;
    private static Tile sourceTile;
    private static Tile destinationTile;
    private static Integer locationX;
    private static Integer locationY;
    public static Boolean shouldWhiteMove;

    public Game() throws InterruptedException, FileNotFoundException {
        board = new Board( this, this);
        sourceTile = null;
        destinationTile = null;
        selectedPiece = null;
        shouldWhiteMove = true;

        //GameUtils.TestFenNotation(Board.getBoard());
        GameUtils.ParseFenNotation(Board.getBoard());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Component component = board.getBoardPanel().findComponentAt(event.getX(), event.getY());

        if(component instanceof Piece) {
            selectedPiece = (Piece) component;

            if(shouldWhiteMove != selectedPiece.getColor()) {
                selectedPiece = null; return;
            }

            sourceTile = (Tile) selectedPiece.getParent();
            locationX = sourceTile.getX() - event.getX();
            locationY = sourceTile.getY() - event.getY();
            selectedPiece.setLocation(event.getX() + locationX, event.getY() + locationY);
            board.getLayeredPane().add(selectedPiece, JLayeredPane.DRAG_LAYER);
            board.getLayeredPane().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (selectedPiece != null) {
            selectedPiece.setLocation(
                Math.max(Math.min(event.getX() + locationX, 815), 0),
                Math.max(Math.min(event.getY() + locationY, 815), 0)
            );
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        board.getLayeredPane().setCursor(null);

        if(selectedPiece != null) {
            selectedPiece.setVisible(false);
            board.getLayeredPane().remove(selectedPiece);
            selectedPiece.setVisible(true);

            Component component = board.getBoardPanel().findComponentAt(
                    Math.max(Math.min(event.getX(), 815), 0),
                    Math.max(Math.min(event.getY(), 815), 0));

            if(component instanceof Piece) destinationTile = (Tile) component.getParent();
            else destinationTile = (Tile) component;
            selectedPiece.movePiece(sourceTile, destinationTile);
        }

        selectedPiece = null;
    }

    /* UNSUPPORTED OPERATIONS */
    ///////////////////////////////////////////////////////////////
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent event) {}
    ///////////////////////////////////////////////////////////////

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        new Game();
    }
}
