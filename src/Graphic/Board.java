package Graphic;

import Pieces.Pawn;
import Pieces.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Board {
    private static JPanel panel;
    private static Square[][] squares;
    private static final Color LIGHT = Color.decode("#f0f0f0");
    private static final Color DARK = Color.decode("#5b80ba");

    public Board(int width, int height) {
        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(new GridLayout(8, 8));
        panel.setBounds(0,0, width, height);
        drawBoard();
    }

    public static class Square {
        private Boolean isOccupied;
        private Color color;
        private JPanel panel;

        private Square() {
            panel = new JPanel(new BorderLayout());
            isOccupied = false;
        }

        public JPanel squarePanel() {
            return panel;
        }
    }

    private void drawBoard() {
        squares = new Square[8][8];

        for(int locationX = 0; locationX < 8; locationX++) {
            for(int locationY = 0; locationY < 8; locationY++) {
                Square square = new Square();
                squares[7 - locationX][locationY] = square;

                if((locationX % 2) == (locationY % 2)) {
                    square.panel.setBackground(LIGHT);
                    square.color = LIGHT;
                } else {
                    square.panel.setBackground(DARK);
                    square.color = DARK;
                }

                panel.add(square.panel);
             }
        }
    }



    private static void generatePiece(char name, boolean color, int locationX, int locationY) {
        Piece piece = null;
        name = Character.toLowerCase(name);

        switch (name) {

        }

        Pawn pawn = new Pawn("pawn", true, 7,0);
        pawn.setImage("./images/white_pawn.png");
        JPanel current = Board.getSquares()[0][0].squarePanel();
        current.add(pawn.getImage());

    }

    public static Piece[][] generatePieces(String fenNotation) {
        char buffer; int locationX = 0; int locationY = 7;
        Piece[][] pieces = new Piece[8][8];

        for(int i = 0; i < fenNotation.length(); i++) {
            buffer = fenNotation.charAt(i);

            if(Character.isAlphabetic(buffer)) {
                if(Character.isLowerCase(buffer)) generatePiece(buffer, false, locationX, locationY);
                else generatePiece(buffer, true, locationX, locationY);
                locationX++;

            } else if(Character.isDigit(buffer)) {
                locationX += Character.getNumericValue(buffer);

            } else if(buffer == '/') {
                locationX = 0;
                locationY--;

            } else {
                i = fenNotation.length();
            }
        }

        return pieces;
    }

    public JPanel getPanel() {
        return panel;
    }

    public static Square[][] getSquares() {
        return squares;
    }
}
