

/**
 * Created by danniwu on 11/05/15.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JPanel{

    GameRepository gameRepository = new GameRepository();

    public SaveGamePanel(JFrame frame, GameController gameController) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel label = new JLabel("Gem spil som: ");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(new Color(165, 25, 25));
        label.setFont(new Font(null, 0, 30));
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(label, BorderLayout.NORTH);

        //A textfield where you have to enter a name for the game you want to save
        final JTextField nameSavedGame = new JTextField("Skriv her");
        nameSavedGame.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 30, false), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        add(nameSavedGame, BorderLayout.CENTER);
        //Can't be left empty, you need to name the game you wnat to save!

        //Separate JPanel with FlowLayout for the buttons to appear next to each other
        JPanel buttonsPanel = new JPanel() {};

        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setSize(200, 200);
        buttonsPanel.setOpaque(false);
        add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Hover hover = new Hover();

        //Saves the game under the name from the text field and closes the window to let the player resume the game
        JButton saveGame = new JButton("Gem spillet og fortsÃ¦t");
        saveGame.setBorderPainted(false);
        saveGame.setContentAreaFilled(false);
        saveGame.setFocusPainted(false);
        saveGame.setOpaque(false);
        saveGame.setForeground(Color.LIGHT_GRAY);
        saveGame.setFont(new Font(null, 0, 20));
        saveGame.addMouseListener(hover);
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameController savableGameController = gameController.clone();
                savableGameController.setObserver(null);

                gameRepository.persist(nameSavedGame.getText(), savableGameController);

                frame.setVisible(false);
                saveGame.setText("Skriv her");
            }
        });

        //Closes the window without doing anything and resumes the game..
        JButton dontSave = new JButton("FortsÃ¦t uden at gemme");
        dontSave.setBorderPainted(false);
        dontSave.setContentAreaFilled(false);
        dontSave.setFocusPainted(false);
        dontSave.setOpaque(false);
        dontSave.setForeground(Color.LIGHT_GRAY);
        dontSave.setFont(new Font(null, 0, 20));
        dontSave.addMouseListener(hover);
        dontSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        buttonsPanel.add(saveGame);
        buttonsPanel.add(dontSave);

    }

    private class Hover implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            button.setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            button.setForeground(Color.LIGHT_GRAY);
        }
    }
}
