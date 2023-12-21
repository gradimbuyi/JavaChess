package com.github.grxdiii.chess.pieces;

import com.github.grxdiii.chess.graphic.Board;
import com.github.grxdiii.chess.graphic.Tile;
import com.github.grxdiii.chess.main.Game;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public abstract class Piece extends JLabel {
    protected String type;
    protected final Boolean color;
    protected Integer locationX;
    protected Integer locationY;
    protected Integer numMoves;
    protected ArrayList<Tile> moves;
    protected static Tile[][] board = Board.getBoard();

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

        if(this instanceof King && this.numMoves == 0 && sourceTile.getLocationX().intValue() == destinationTile.getLocationX().intValue() &&
          (destinationTile.getLocationY() <= 2 || destinationTile.getLocationY() >= 6)) {

               if(destinationTile.getLocationY() <= 2 && ((King) this).performCastling(false)) return false;
          else if(destinationTile.getLocationY() >= 6 && ((King) this).performCastling(true))  return false;

        }


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
