package com.ardadev.presentation.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class LabelWithHyperlink extends JLabel {
    public LabelWithHyperlink(String text, String link, int size) {
        setForeground(Color.BLUE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setText(text);
        setPreferredSize(new Dimension(150, 14));
        setFont(new Font(null, Font.PLAIN, size));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            try {
                                Desktop.getDesktop().browse(new URI(link));
                                //Thread.sleep(1000);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null,
                                        "Couldn't open the system default browser.",
                                        "Alert",
                                        JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/favicon.png"));
                            }
                            return null;
                        }
                    };

                    worker.execute();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.BLUE);
            }
        });
    }

    public LabelWithHyperlink(Icon icon, String link) {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setIcon(icon);
        setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            try {
                                Desktop.getDesktop().browse(new URI(link));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null,
                                        "Couldn't open the system default browser.",
                                        "Alert",
                                        JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/favicon.png"));
                            }
                            return null;
                        }
                    };
                    worker.execute();
                }
            }
        });
    }
}
