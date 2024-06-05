package fr.chess.saechess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.layout.GridPane;


public class HelloController {

    private int secondsRemaining = 600; // 600 = 10 minutes / 1800 = 30 min
    @FXML
    private GridPane chessBoard; // Déclaration de la variable gridPane

    @FXML
    private SplitMenuButton menu1;
    @FXML
    private SplitMenuButton menu2;

    @FXML
    private Label timerLabelN;
    @FXML
    private Label timerLabelB;

    @FXML
    private ImageView pawn;//image du pions

    @FXML
    protected void pvp() {
        menu1.setText("player vs player");
    }

    @FXML
    protected void pve() {
        menu1.setText("player vs bot");
    }

    @FXML
    protected void time1() {
        menu2.setText("10 minutes");
        secondsRemaining = 600; // 600 = 10 minutes
    }

    @FXML
    protected void time2() {
        menu2.setText("30 minutes");
        secondsRemaining = 1800; // 1800 = 30 min
    }

    @FXML
    protected void btnJouer() {
        // lancer une partie
    }


    @FXML
    public void initialize() {
        // Initialize the timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (secondsRemaining > 0) {
                        secondsRemaining--;
                        int minutes = secondsRemaining / 60;
                        int seconds = secondsRemaining % 60;
                        timerLabelN.setText(String.format("%02d:%02d", minutes, seconds));
                        timerLabelB.setText(String.format("%02d:%02d", minutes, seconds));
                    } else {
                        timer.cancel();
                    }
                });
            }
        }, 0, 1000);
        // Initialize the pawn qui est le pion
        initializePawn(pawn);

    }

    //déplacement des pion en maitenant le clique de la souris
    private void initializePawn(ImageView pawn) {
        // Ajouter un écouteur d'événements de clic de souris pour déplacer le pion
        pawn.setOnMousePressed(event -> pawn.setOpacity(0.5));
        pawn.setOnMouseReleased(event -> pawn.setOpacity(1));

        pawn.setOnMouseClicked(event -> {
            // Récupérer les coordonnées de la cellule de la grille où l'utilisateur a cliqué
            int clickedColIndex = (int) (event.getX() / chessBoard.getWidth() * chessBoard.getColumnCount());
            int clickedRowIndex = (int) (event.getY() / chessBoard.getHeight() * chessBoard.getRowCount());

            // Déplacer le pion vers la cellule de la grille où l'utilisateur a cliqué
            movePawnTo(clickedColIndex, clickedRowIndex);
        });
    }





    private void movePawnTo(int colIndex, int rowIndex) {
        GridPane.setConstraints(pawn, colIndex, rowIndex);
    }

}




