package Pieces;

import Graphic.Board;
import Graphic.Tile;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Knight extends Piece {
    public Knight(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }


    @Override
    public ArrayList<Tile> legalMoves() {
        moves = new ArrayList<>();
        Tile[][] board = Board.getBoard();

        if(locationX - 2 >= 0 && locationY - 1 >= 0) {
            moves.add(board[locationX - 2][locationY - 1]);
        }

        if(locationX - 2 >= 0 && locationY + 1 <= 7) {
            moves.add(board[locationX -2][locationY +1]);
        }

        /*
        addMove(locationX + 2, locationY - 1);
        addMove(locationX + 2, locationY + 1);
        addMove(locationX + 1, locationY - 2);
        addMove(locationX - 1, locationY - 2);
        addMove(locationX - 2, locationY - 1);
        addMove(locationX - 2, locationY + 1);
        addMove(locationX + 1, locationY + 2);
        addMove(locationX - 1, locationY + 2);


         */
        return moves;
    }

}
