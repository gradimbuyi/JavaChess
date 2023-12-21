package com.github.grxdiii.chess.graphic;

import com.github.grxdiii.chess.pieces.Piece;
import javax.swing.JPanel;


/**
 * The Tile class defines the properties that makes up a single chess tile within the Board.
 * Each tile has location based off the x-y axis. THe
 *
 * @author Gradi Tshielekeja Mbuyi
 * @version 1.0
 */
public class Tile extends JPanel {
    private final Integer locationX;
    private final Integer locationY;
    private Piece piece;
    public Boolean isOccupied;

    /**
     * The TIle constructor takes in two parameters: locationX and locationY.
     *
     * @param locationX
     * @param locationY
     */
    public Tile(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.piece = null;
        this.isOccupied = false;
    }

    public void removePiece() {
        if(this.piece == null) return;
        isOccupied = false;
        remove(piece);
        piece = null;
        repaint();

    }

    public boolean addPiece(Piece piece) {
        boolean hasCaptured = isOccupied;
        removePiece();
        this.piece = piece;
        this.piece.changeLocation(locationX, locationY);
        this.isOccupied = true;
        this.add(piece);
        this.validate();
        this.repaint();
        return hasCaptured;
    }

    public Piece getPiece() {
        return piece;
    }

    public Integer getLocationX() {
        return locationX;
    }

    public Integer getLocationY() {
        return locationY;
    }

    public Boolean checkPieceColor() {
        if(isOccupied) return !piece.getColor();
        return null;
    }
}
