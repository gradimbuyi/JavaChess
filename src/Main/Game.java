package Main;

import Graphic.Board;
import Graphic.Tile;
import Pieces.Piece;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Game implements MouseListener {
    private static Board board;
    private static String fenNotation;
    private static Piece[][] pieces;

    public Game() {
        fenNotation = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        board = new Board( this);
    }

    public static String getFenNotation() {
        return fenNotation;
    }

    private Tile getTile(int locationX, int locationY) {
        locationX = locationX / 115;
        locationY = locationY / 115;
        return board.getTiles()[7 - locationY][locationX];
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Tile tile = getTile(event.getX(), event.getY());

        tile.changeColor();
        tile.getPiece().legalMoves();

        if(tile.isOccupied) {
            //tile.removePiece();
            System.out.println(fenNotation);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {}

    /* UNSUPPORTED OPERATIONS */
    ///////////////////////////////////////////////////////////////
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    ///////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        new Game();
        pieces = board.generatePieces(fenNotation);
        board.setVisible();
        System.out.println(fenNotation);
    }
}
