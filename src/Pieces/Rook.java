package Pieces;

import Graphic.Board;
import Graphic.Tile;

import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Rook extends Piece {
    private Boolean canCastle;

    public Rook(String type, boolean color, int locationX, int locationY) {
        super(type, color, locationX, locationY);
    }

    @Override
    public ArrayList<Tile> legalMoves(Board board) {
        Tile[][] squares = board.getTiles();
        ArrayList<Tile> moves = new ArrayList<>();

        int temp = 1;

        while(locationX + temp <= 7) {
            if(squares[locationX + temp][locationY].isOccupied && squares[locationX + temp][locationY].getPiece().color == color) break;
            moves.add(squares[locationX + temp][locationY]);
            temp++;
        }   temp = 1;

        while(locationX - temp >= 0) {
            if(squares[locationX - temp][locationY].isOccupied && squares[locationX - temp][locationY].getPiece().color == color) break;
            moves.add(squares[locationX - temp][locationY]);
            temp++;
        }   temp = 1;

        while(locationY + temp <= 7) {
            if(squares[locationX][locationY + temp].isOccupied && squares[locationX][locationY + temp].getPiece().color == color) break;
            moves.add(squares[locationX][locationY + temp]);
            temp++;
        }   temp = 1;

        while(locationY - temp >= 0) {
            if(squares[locationX][locationY - temp].isOccupied && squares[locationX][locationY - temp].getPiece().color == color) break;
            moves.add(squares[locationX][locationY - temp]);
            temp++;
        }

        return moves;
    }
}
