package kevesse_kokanyolo_kod.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kevesse_kokanyolo_kod.people.AcademicPerson;
import kevesse_kokanyolo_kod.people.Cleaner;

public class PlayerInfoView extends JPanel {


    public PlayerInfoView() {
        createPlayerInfo();
        
    }
    /**
     * Megjeleníti a kapott hallgató vagy oktató információit (név, életek).
     * @param person a személy, akinek az információit meg kell jeleníteni
     */
    public void display(AcademicPerson person) {
    }

    /**
     * Megjeleníti a megadott takarító információit.
     * @param cleaner
     */
    public void display(Cleaner cleaner) {
    }

    
    //A bal alsó sarokban lévő játékos információt tároló részt jeleníti meg
    private void createPlayerInfo() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(350, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel playerInfo = new JLabel("Player");
        playerInfo.setBorder(new EmptyBorder(5, 20, 40, 0));
        playerInfo.setFont(playerInfo.getFont().deriveFont(20f));

        JLabel playerName = new JLabel("Name: ");
        playerName.setBorder(new EmptyBorder(0, 20, 0, 0));
        playerName.setFont(playerInfo.getFont().deriveFont(18f));

        JLabel playerSouls = new JLabel("Souls: ");
        playerSouls.setBorder(new EmptyBorder(20, 20, 50, 0));
        playerSouls.setFont(playerInfo.getFont().deriveFont(18f));

        JButton pickUpButton = new JButton("Pick up item");
        pickUpButton.setFont(playerInfo.getFont().deriveFont(18f));
        pickUpButton.setPreferredSize(new Dimension(225, 50));

        add(playerInfo);
        add(playerName);
        add(playerSouls);
        add(pickUpButton);

    }
}
