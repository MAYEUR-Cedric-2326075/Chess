package fr.chess.saechess;// Minuteur.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Minuteur {
    private int seconds;
    private int minutes;
    private int hours;
    private Timer timer;
    private JLabel timeLabel;

    public Minuteur() {
        seconds = 10;
        minutes = 0;
        hours = 0;

        timeLabel = new JLabel(formatTime());
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });

        JFrame frame = new JFrame("Minuteur");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(timeLabel, java.awt.BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void tick() {
        if (seconds == 0) {
            seconds = 60;
            minutes--;
        }
        seconds--;
        timeLabel.setText(formatTime());
        if (minutes == 0 && seconds == 0) {
            timer.stop();
            System.out.println("Fin du chrono !");
        }
    }

    private String formatTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        seconds = 0;
        minutes = 0;
        hours = 0;
        timeLabel.setText(formatTime());
    }

    public static void main(String[] args) {
        Minuteur minuteur = new Minuteur();
        
        // Start the timer for demonstration
        minuteur.start();
    }
}
