package fr.chess.saechess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    private int secondsRemaining = 600; // 600 = 10 minutes    /     1800 = 30 min

    @FXML
    private SplitMenuButton menu1;
    @FXML
    private SplitMenuButton menu2;

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
        //lancer une partie
    }

    @FXML
    private Label timerLabelN;
    @FXML
    private Label timerLabelB;

    public void initialize() {
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


    }
}