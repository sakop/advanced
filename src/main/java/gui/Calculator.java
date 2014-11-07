package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("計算機");
        frame.setSize(250, 270);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int margin = 10;
        Border border = BorderFactory.createEmptyBorder(margin, margin, margin, margin);

        Font font = new Font(Font.SERIF, Font.PLAIN, 14);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new BorderLayout(10, 10));
        bigPanel.setBorder(border);

        JTextField field = new JTextField("90.0", 20);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setFont(font);

        bigPanel.add(field, BorderLayout.NORTH);

        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new GridLayout(4, 4, 10, 10));

        addRowButtons(operatorPanel, 7, 9, "/", font);
        addRowButtons(operatorPanel, 4, 6, "*", font);
        addRowButtons(operatorPanel, 1, 3, "-", font);

        JButton button = new JButton(0 + "");
        operatorPanel.add(button);
        button.setFont(font);

        button = new JButton(".");
        operatorPanel.add(button);
        button.setFont(font);

        button = new JButton("=");
        operatorPanel.add(button);
        button.setFont(font);

        button = new JButton("+");
        operatorPanel.add(button);
        button.setFont(font);

        bigPanel.add(operatorPanel, BorderLayout.CENTER);

        frame.add(bigPanel);

        ImageIcon icon = new ImageIcon("src/main/java/gui/cal.png");
        frame.setIconImage(icon.getImage());

        frame.setVisible(true);

    }

    private static void addRowButtons(JPanel operatorPanel, int start, int end, String operator, Font font) {
        for (int i = start; i <= end; i++) {
            JButton button = new JButton(i + "");
            button.setFont(font);
            operatorPanel.add(button);
        }

        JButton button = new JButton(operator);
        button.setFont(font);
        operatorPanel.add(button);
    }
}
