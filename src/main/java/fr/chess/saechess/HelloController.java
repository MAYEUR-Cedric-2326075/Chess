package fr.chess.saechess;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    //set le timer à 10 par défaut
    private int secondsRemaining = 600; // 600 = 10 minutes    /     1800 = 30 min

    //menu déroulant por choisir le mode de jeu
    @FXML
    private SplitMenuButton menu1;
    //menu déroulant pour choisir le temps de jeu
    @FXML
    private SplitMenuButton menu2;

    //changement du texte dans le menu déroulant du mode de jeu
    @FXML
    protected void pvp() {
        menu1.setText("player vs player");
    }
    //changement du texte dans le menu déroulant du mode de jeu
    @FXML
    protected void pve() {
        menu1.setText("player vs bot");
    }

    //menu temps de jeu pour changer le texte et le temps 10 minutes
    @FXML
    protected void time1() {
        menu2.setText("10 minutes");
        timerLabelN.setText("10:00");
        timerLabelB.setText("10:00");
        secondsRemaining = 600; // 600 = 10 minutes
    }
    //menu temps de jeu pour changer le texte et le temps à 30 minutes
    @FXML
    protected void time2() {
        menu2.setText("30 minutes");
        timerLabelN.setText("30:00");
        timerLabelB.setText("30:00");
        secondsRemaining = 1800; // 1800 = 30 min
    }

    //variables pour le timer
    private Timer timer;
    private boolean isRunning = false;

    //fontions du bouton pour lancer le timer
    @FXML
    protected void btnJouer() {
        //lancer le timer
        startTimer();
    }
    //fontions du bouton pour stoper le timer
    @FXML
    protected void stopTime() {
        //stop le temps
        if (timer != null) {
            timer.cancel();
            isRunning = false;
        }
    }
    //fontions du bouton pour relancer le timer
    @FXML
    protected void TimeResume() {
        //fait reprendre le court du temps
        if (!isRunning) {
            startTimer();
        }
    }

    //Label pour afficher le timer du coté noir
    @FXML
    private Label timerLabelN;
    //Label pour afficher le timer du coté blanc
    @FXML
    private Label timerLabelB;

    //import du roi blanc pour déplacement
    @FXML
    private ImageView wk;

    //TextFields pour les déplacements z,q,s,d
    @FXML
    private TextField textA;
    //TextFields pour les déplacements par coordonées
    @FXML
    private TextField textF1;
    //TextFields pour les déplacements par coordonées
    @FXML
    private TextField textF2;

    //fonctions pour l'oppacité quand pion séléctionner
    public void opp1() {
        wk.setOpacity(0.5);
    }
    public void opp2() {
        wk.setOpacity(1);
    }

    //faire bouger le roi blanc avec z,q,s,d
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
                System.out.println("touche non valide");
        }
    }

    //déplacement roi blanc avec coordonnées du plateau
    public void btnDeplacer() {
        String nombre1 = textF1.getText();
        String nombre2 = textF2.getText();
        int resultat1 = Integer.parseInt(nombre1);
        int resultat2 = Integer.parseInt(nombre2);
        wk.setTranslateX(resultat1*60-300);
        wk.setTranslateY(-resultat2*60-60+120);
    }

    //fonction pour lancer le timer
    public void startTimer() {
        if (isRunning) return;

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

    //fonction qui ce lance au début du programe
    public void initialize() {

    }

}