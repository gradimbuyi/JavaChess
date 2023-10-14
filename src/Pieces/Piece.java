package Pieces;

import Graphic.Board;
import Graphic.Tile;
import Main.Game;

import javax.management.QueryExp;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece extends JLabel {
    protected final String type;
    protected final Boolean color;
    protected Integer locationX;
    protected Integer locationY;
    protected Integer numMoves;
    protected ArrayList<Tile> moves;

    /**
     *
     * @param type
     * @param color
     * @param locationX
     * @param locationY
     */
    public Piece(String type, boolean color, int locationX, int locationY) {
        this.type = type;
        this.color = color;
        this.locationX = locationX;
        this.locationY = locationY;
        this.numMoves = 0;
        if(this.color) setImage("./images/white_" + this.type + ".png");
        else setImage("./images/black_" + this.type + ".png");
    }

    /**
     *
     * @return
     */
    public abstract ArrayList<Tile> legalMoves();

    /**
     *
     * @param moves
     * @param destinationTile
     * @return
     */
    public Boolean checkIfValid(ArrayList<Tile> moves, Tile destinationTile) {
        if(moves.size() > 0) for(Tile move : moves) if(move == destinationTile) return true;
        return false;
    }

    /**
     *
     * @param fileName
     */
    public void setImage(String fileName) {
        this.setIcon(new ImageIcon(new ImageIcon(fileName).getImage().
                getScaledInstance(105, 105, Image.SCALE_SMOOTH)));
    }

    /**
     *
     * @param sourceTile
     * @param destinationTile
     * @return
     */
    public Boolean movePiece(Tile sourceTile, Tile destinationTile) {
        boolean hasCaptured;

        if(!checkIfValid(legalMoves(), destinationTile)) {
            sourceTile.addPiece(this);
            return false;
        }

        hasCaptured = destinationTile.addPiece(this);

        if(sourceTile != destinationTile) {
            sourceTile.removePiece();
            Game.shouldWhiteMove = !Game.shouldWhiteMove;
            numMoves++;
        }

        return hasCaptured;
    }

    public Boolean addMove(int locationX, int locationY) {
        Tile[][] board = Board.getBoard();

        if(this instanceof Pawn && locationX == this.locationX && locationY == this.locationY) {
            int new_x = this.color ? locationX - 1 : locationX + 1;

            if(new_x >= 0 && new_x <= 7) {
                if(locationY - 1 >= 0 && board[new_x][locationY - 1].checkPieceColor() == color) {
                    moves.add(board[new_x][locationY - 1]);
                }

                if(locationY + 1 <= 7 && board[new_x][locationY + 1].checkPieceColor() == color) {
                    moves.add(board[new_x][locationY + 1]);
                }
            }
        }

        else if(this instanceof Rook || this instanceof Queen || this instanceof Bishop) {
            if(board[locationX][locationY].isOccupied) {
                if(board[locationX][locationY].getPiece().getColor() == color) return false;
                else {
                    moves.add(board[locationX][locationY]);
                    return false;
                }
            }

            moves.add(board[locationX][locationY]);
            return true;
        }

        else if(locationX >= 0 && locationX <= 7 && locationY >= 0 && locationY <= 7) {
            if(board[locationX][locationY].checkPieceColor() == color || board[locationX][locationY].checkPieceColor() == null) {
                moves.add(board[locationX][locationY]);
                return true;
            }
        }

        return false;
    }

    public void changeLocation(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public Boolean getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public Integer getLocationX() {
        return locationX;
    }

    public Integer getLocationY() {
        return locationY;
    }
}
