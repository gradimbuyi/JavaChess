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
    private static Piece piece;
    private static Tile source;
    private static Tile destination;
    private static Integer locationX;
    private static Integer locationY;
    public static Boolean shouldWhiteMove;

    public Game() {
        // Creates new board that listens to the mouse motion
        board = new Board( this, this);
        // Set every relevant property of movements to null
        source = null; destination = null; piece = null; shouldWhiteMove = true;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Component component = board.getBoardPanel().findComponentAt(event.getX(), event.getY());

        if(component instanceof Piece) {
            piece = (Piece) component;

            if(shouldWhiteMove != piece.getColor()) {
                piece = null; return;
            }

            source = (Tile) piece.getParent();
            locationX = source.getX() - event.getX();
            locationY = source.getY() - event.getY();
            piece.setLocation(event.getX() + locationX, event.getY() + locationY);
            board.getLayeredPane().add(piece, JLayeredPane.DRAG_LAYER);
            board.getLayeredPane().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (piece != null) {
            piece.setLocation(
                Math.max(Math.min(event.getX() + locationX, 815), 0),
                Math.max(Math.min(event.getY() + locationY, 815), 0)
            );
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        board.getLayeredPane().setCursor(null);

        if(piece != null) {
            piece.setVisible(false);
            board.getLayeredPane().remove(piece);
            piece.setVisible(true);

            Component component = board.getBoardPanel().findComponentAt(
                    Math.max(Math.min(event.getX(), 815), 0),
                    Math.max(Math.min(event.getY(), 815), 0));

            if(component instanceof Piece) destination = (Tile) component.getParent();
            else destination = (Tile) component;
            piece.movePiece(source, destination);
        }
        piece = null;
    }


    /* UNSUPPORTED OPERATIONS */
    ///////////////////////////////////////////////////////////////
    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseMoved(MouseEvent event) {}
    @Override
    public void mouseClicked(MouseEvent event) {}
    ///////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        new Game();
    }
}
