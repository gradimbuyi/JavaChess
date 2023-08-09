package Pieces;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Rook extends Piece {
    public Rook(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public Integer[] legalMoves() {
        return new Integer[0];
    }
}
