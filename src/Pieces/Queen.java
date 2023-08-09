package Pieces;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Queen extends Piece {
    public Queen(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public Integer[] legalMoves() {
        return new Integer[0];
    }
}
