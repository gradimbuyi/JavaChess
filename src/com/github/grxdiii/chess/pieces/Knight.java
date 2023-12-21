package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
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

        if(locationX - 2 >= 0) {
            if((locationY - 1 >= 0) && (board[locationX - 2][locationY - 1].getPiece() == null ||
                board[locationX - 2][locationY - 1].getPiece().getColor() != color)) {
                moves.add(board[locationX - 2][locationY - 1]);
            }

            if((locationY + 1 <= 7) && (board[locationX - 2][locationY + 1].getPiece() == null ||
                board[locationX - 2][locationY + 1].getPiece().getColor() != color)) {
                moves.add(board[locationX - 2][locationY + 1]);
            }
        }

        if(locationX + 2 <= 7) {
            if((locationY - 1 >= 0) && (board[locationX + 2][locationY - 1].getPiece() == null ||
                board[locationX + 2][locationY - 1].getPiece().getColor() != color)) {
                moves.add(board[locationX + 2][locationY - 1]);
            }

            if((locationY + 1 <= 7) && (board[locationX + 2][locationY + 1].getPiece() == null ||
                board[locationX + 2][locationY + 1].getPiece().getColor() != color)) {
                moves.add(board[locationX + 2][locationY + 1]);
            }
        }

        if(locationY - 2 >= 0) {
            if((locationX - 1 >= 0) && (board[locationX - 1][locationY - 2].getPiece() == null ||
                board[locationX - 1][locationY - 2].getPiece().getColor() != color)) {
                moves.add(board[locationX - 1][locationY - 2]);
            }

            if((locationX + 1 <= 7) && (board[locationX + 1][locationY - 2].getPiece() == null ||
                board[locationX + 1][locationY - 2].getPiece().getColor() != color)) {
                moves.add(board[locationX + 1][locationY - 2]);
            }
        }

        if(locationY + 2 <= 7) {
            if((locationX - 1 >= 0) && (board[locationX - 1][locationY + 2].getPiece() == null ||
                board[locationX - 1][locationY + 2].getPiece().getColor() != color)) {
                moves.add(board[locationX - 1][locationY + 2]);
            }

            if((locationX + 1 <= 7) && (board[locationX + 1][locationY + 2].getPiece() == null ||
                board[locationX + 1][locationY + 2].getPiece().getColor() != color)) {
                moves.add(board[locationX + 1][locationY + 2]);
            }
        }

        return moves;
    }

}
