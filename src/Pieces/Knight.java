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
    public ArrayList<Tile> legalMoves(Board board) {
        Tile[][] squares = board.getTiles();
        ArrayList<Tile> moves = new ArrayList<>();

        if(locationX + 2 <= 7 && locationY - 1 >= 0) {
            Tile possibleMove = squares[locationX + 2][locationY - 1];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color)  moves.add(possibleMove);
        }

        if(locationX + 2 <= 7 && locationY + 1 <= 7) {
            Tile possibleMove = squares[locationX + 2][locationY + 1];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        if(locationX + 1 <= 7 && locationY - 2 >= 0) {
            Tile possibleMove = squares[locationX + 1][locationY - 2];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        if(locationX - 1 >= 0 && locationY - 2 >= 0) {
            Tile possibleMove = squares[locationX - 1][locationY - 2];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        if(locationX - 2 >= 0 && locationY - 1 >= 0) {
            Tile possibleMove = squares[locationX - 2][locationY - 1];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        if(locationX - 2 >= 0 && locationY + 1 <= 7) {
            Tile possibleMove = squares[locationX - 2][locationY + 1];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        if(locationX + 1 <= 7 && locationY + 2 <= 7) {
            Tile possibleMove = squares[locationX + 1][locationY + 2];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        if(locationX - 1 >= 0 && locationY + 2 <= 7) {
            Tile possibleMove = squares[locationX - 1][locationY + 2];
            if(possibleMove.checkPieceColor() == null || possibleMove.checkPieceColor() == color) moves.add(possibleMove);
        }

        return moves;
    }

}
