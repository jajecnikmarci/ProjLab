package kevesse_kokanyolo_kod.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame {
    public MenuWindow() {
        JFrame frame = new JFrame("Labyrinth");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        frame.getContentPane().add(panel);

        JLabel label = new JLabel("Labyrinth");
        label.setFont(label.getFont().deriveFont(50f));
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.insets = new Insets(0, 0, 100, 0);
        panel.add(label, gbcLabel);

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(330, 60));
        startButton.setFont(startButton.getFont().deriveFont(20f));
        GridBagConstraints gbcButton1 = new GridBagConstraints();
        gbcButton1.gridx = 0;
        gbcButton1.gridy = 1;
        gbcButton1.insets = new Insets(0, 0, 20, 0);
        panel.add(startButton, gbcButton1);

        JButton addStudentButton = new JButton("Add student");
        addStudentButton.setPreferredSize(new Dimension(330, 60));
        addStudentButton.setFont(addStudentButton.getFont().deriveFont(20f));
        GridBagConstraints gbcButton2 = new GridBagConstraints();
        gbcButton2.gridx = 0;
        gbcButton2.gridy = 2;
        gbcButton2.insets = new Insets(0, 0, 20, 0);
        panel.add(addStudentButton, gbcButton2);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new GameWindow();
                    }
                });
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //TODO
                    }
                });
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
