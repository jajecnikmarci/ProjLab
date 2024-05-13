package kevesse_kokanyolo_kod.windows;

import javax.swing.*;

import kevesse_kokanyolo_kod.controllers.Controller;
import kevesse_kokanyolo_kod.views.InventoryView;
import kevesse_kokanyolo_kod.views.ItemsInRoomView;
import kevesse_kokanyolo_kod.views.LabyrinthView;
import kevesse_kokanyolo_kod.views.PlayerInfoView;

import java.awt.*;

public class GameWindow extends JFrame {
    private JPanel menuContainer;
    private ItemsInRoomView itemsInRoomView;
    private LabyrinthView labyrinthView;    
    private PlayerInfoView playerInfoView;
    private InventoryView inventoryView;
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
    }
}
