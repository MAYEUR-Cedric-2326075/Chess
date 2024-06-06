package fr.chess.saechess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    private int secondsRemaining = 600; // 600 = 10 minutes    /     1800 = 30 min

    @FXML
    private SplitMenuButton menu1;
    @FXML
    private SplitMenuButton menu2;
    private Scanner scanner;

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
        timerLabelN.setText("10:00");
        timerLabelB.setText("10:00");
        secondsRemaining = 600; // 600 = 10 minutes
    }
    @FXML
    protected void time2() {
        menu2.setText("30 minutes");
        timerLabelN.setText("30:00");
        timerLabelB.setText("30:00");
        secondsRemaining = 1800; // 1800 = 30 min
    }

    @FXML
    protected void stopTime() {
        //stop le temps
        if (timer != null) {
            timer.cancel();
            isRunning = false;
        }
    }

    @FXML
    protected void btnJouer() {
        //lancer une partie
        startTimer();
    }

    @FXML
    protected void TimeResume() {
        //fait reprendre le court du temps
        if (!isRunning) {
            startTimer();
        }
    }

    @FXML
    private Label timerLabelN;
    @FXML
    private Label timerLabelB;

    private Timer timer;
    private boolean isRunning = false;


    @FXML
    private ImageView wk;
    @FXML
    private ImageView bk;

    @FXML
    private GridPane chessBoard;

    public void initialize() {

    }

    @FXML
    private TextField textA;
    @FXML
    private TextField textF1;
    @FXML
    private TextField textF2;

    public void opp1() {
        wk.setOpacity(0.5);;
    }
    public void opp2() {
        wk.setOpacity(1);
    }

    public void wkMove() {
        switch(textA.getText()) {
            case "z":
                wk.setTranslateY(wk.getTranslateY()-60);
                break;
            case "q":
                wk.setTranslateX(wk.getTranslateX()-60);
                break;
            case "s":
                wk.setTranslateY(wk.getTranslateY()+60);
                break;
            case "d":
                wk.setTranslateX(wk.getTranslateX()+60);
                break;
            default:
                // code block
        }
    }
    public void btnDeplacer() {
        String nombre1 = textF1.getText();
        String nombre2 = textF2.getText();
        int resultat1 = Integer.parseInt(nombre1);
        int resultat2 = Integer.parseInt(nombre2);
        wk.setTranslateX(resultat1*60-300);
        wk.setTranslateY(-resultat2*60-60+120);
    }

    public void startTimer() {
        if (isRunning) return; // Avoid starting multiple timers

        timer = new Timer();
        isRunning = true;
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
                        isRunning = false;
                    }
                });
            }
        }, 0, 1000);
    }


}