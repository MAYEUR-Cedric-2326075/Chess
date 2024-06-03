package fr.chess.saechess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChessGame extends Application {
    private Board board;
    private ChessPiece selectedPiece;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        board = new Board();

        Scene scene = new Scene(board);
        scene.setOnMouseClicked(this::handleMouseClick);

        primaryStage.setTitle("Chess Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMouseClick(MouseEvent event) {
        int x = (int) (event.getX() / 60);
        int y = (int) (event.getY() / 60);

        if (selectedPiece == null) {
            selectedPiece = board.getPiece(x, y);
        } else {
            if (selectedPiece.isValidMove(x, y, board.board)) {
                board.movePiece(selectedPiece, x, y);
            }
            selectedPiece = null;
        }
    }
}