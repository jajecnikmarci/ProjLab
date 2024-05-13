package kevesse_kokanyolo_kod.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import kevesse_kokanyolo_kod.controllers.Controller;
import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;
import kevesse_kokanyolo_kod.people.Student;

public class PlayerInfoView extends JPanel {

    String playerNameString;
    int souls; //???
    Controller controller;
    JLabel playerInfoLabel;
    JLabel playerNameLabel;
    JLabel playerName;
    JLabel playerSoulsLabel;
    JLabel playerSouls;
    JButton pickUpButton;


    public PlayerInfoView(Controller controller) {
        this.controller=controller;
        createPlayerInfo();
    }

    /**
     * Megjeleníti a kapott hallgató vagy oktató információit (név, életek).
     * @param person a személy, akinek az információit meg kell jeleníteni
     */
    public void display(AcademicPerson person) {
        for (var studentEntry : controller.getLabyrinthBuilder().getStudents().entrySet()) {
            if(studentEntry.getValue().equals(person)) {
                playerInfoLabel.setText("Student");
                playerNameLabel.setText("Name: "+studentEntry.getKey());
                playerSoulsLabel.setText("Souls: "+String.valueOf(studentEntry.getValue().getSouls()));
            }
        }
        
        for (var professorEntry : controller.getLabyrinthBuilder().getProfessors().entrySet()) {
            if(professorEntry.getValue().equals(person)) {
                playerInfoLabel.setText("Professor");
                playerSoulsLabel.setVisible(false);
                playerNameLabel.setText("Name: "+professorEntry.getKey());
            }
        }
    }

    /**
     * Megjeleníti a megadott takarító információit.
     * @param cleaner
     */
    public void display(Cleaner cleaner) {
        playerSoulsLabel.setVisible(false);
        for (var cleanerEntry : controller.getLabyrinthBuilder().getCleaners().entrySet()) {
            if(cleanerEntry.getValue().equals(cleaner)) {
                playerInfoLabel.setText("Cleaner");
                playerNameLabel.setText("Name: "+cleanerEntry.getKey());
            }
        }
    }
 
    //A bal alsó sarokban lévő játékos információt tároló részt jeleníti meg
    private void createPlayerInfo() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(350, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        playerInfoLabel = new JLabel("Player");
        playerInfoLabel.setBorder(new EmptyBorder(5, 20, 40, 0));
        playerInfoLabel.setFont(playerInfoLabel.getFont().deriveFont(20f));

        playerNameLabel = new JLabel("Name: ");
        playerNameLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        playerNameLabel.setFont(playerInfoLabel.getFont().deriveFont(18f));

        playerSoulsLabel = new JLabel("Souls: ");
        playerSoulsLabel.setBorder(new EmptyBorder(20, 20, 50, 0));
        playerSoulsLabel.setFont(playerInfoLabel.getFont().deriveFont(18f));

        pickUpButton = new JButton("Pick up item");
        pickUpButton.setFont(playerInfoLabel.getFont().deriveFont(18f));
        pickUpButton.setPreferredSize(new Dimension(225, 50));
        pickUpButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.pickUp(playerNameString);
                }
            }
        );


        add(playerInfoLabel);
        add(playerNameLabel);
        add(playerSoulsLabel);
        add(pickUpButton);

    }
}
