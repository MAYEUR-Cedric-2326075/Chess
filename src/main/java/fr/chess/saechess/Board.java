package fr.chess.saechess;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {
    private ChessPiece[][] board = new ChessPiece[8][8];

    public Board() {
        setPrefSize(480, 480);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rect = new Rectangle(60, 60);
                rect.setFill((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
                rect.setTranslateX(i * 60);
                rect.setTranslateY(j * 60);
                getChildren().add(rect);
            }
        }
        initializePieces();
        drawPieces();
    }

    private void initializePieces() {
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn(i, 1, false);
            board[i][6] = new Pawn(i, 6, true);
        }
    }

    private void drawPieces() {
        getChildren().removeIf(node -> node instanceof ChessPiece);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    getChildren().add(board[i][j]);
                }
            }
        }
    }

    public ChessPiece getPiece(int x, int y) {
        return board[x][y];
    }

    public void movePiece(ChessPiece piece, int newX, int newY) {
        int oldX = piece.getX();
        int oldY = piece.getY();
        board[oldX][oldY] = null;
        board[newX][newY] = piece;
        piece.setX(newX);
        piece.setY(newY);
        drawPieces();
    }
}