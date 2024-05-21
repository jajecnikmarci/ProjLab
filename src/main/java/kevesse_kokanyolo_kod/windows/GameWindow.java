package kevesse_kokanyolo_kod.windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import kevesse_kokanyolo_kod.controllers.Controller;
import kevesse_kokanyolo_kod.views.InventoryView;
import kevesse_kokanyolo_kod.views.ItemsInRoomView;
import kevesse_kokanyolo_kod.views.LabyrinthView;
import kevesse_kokanyolo_kod.views.PlayerInfoView;

import java.awt.*;

public class GameWindow extends JFrame {
    private JPanel menuContainer;
    public ItemsInRoomView itemsInRoomView;
    public LabyrinthView labyrinthView;    
    public PlayerInfoView playerInfoView;
    public InventoryView inventoryView;
    public InfoView infoView;
    Controller controller;

    public GameWindow(Controller controller) {
        super("Labyrinth");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.controller=controller;

        menuContainer = new JPanel();
        menuContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        getContentPane().add(menuContainer, BorderLayout.SOUTH);
        setVisible(true);

        labyrinthView = new LabyrinthView(controller);
        add(labyrinthView);

        playerInfoView = new PlayerInfoView(controller);
        menuContainer.add(playerInfoView);

        inventoryView = new InventoryView();
        menuContainer.add(inventoryView);

        itemsInRoomView = new ItemsInRoomView();
        menuContainer.add(itemsInRoomView);

        infoView = new InfoView();
        menuContainer.add(infoView);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Not used
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Check if the Escape key was pressed
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    controller.deselectPerson();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used
            }
        });

    }
}
