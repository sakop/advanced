package gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphic test");
        frame.setSize(250, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(1, 3));

        final TestPanel p1,p2;
        frame.add(p1 = new TestPanel(1));
        frame.add(p2 = new TestPanel(2));

        JButton changeColor = new JButton("change color");
        changeColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setFilled(!p1.isFilled());
                p2.setFilled(!p2.isFilled());
            }
        });

        frame.add(changeColor);
        frame.setVisible(true);
    }
}

class TestPanel extends JPanel {
    private int type;
    private boolean isFilled = false;

    public TestPanel(int type) {
        this.type = type;
    }

    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
        repaint();
    }

    public boolean isFilled(){
        return this.isFilled;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        if (type == 1) {
            if (!isFilled)
                g.drawRect(10, 10, width - 20, height - 20);
            else
                g.fillRect(10, 10, width - 20, height - 20);
        }
        else {
            if (!isFilled)
                g.drawOval(10, 10, width - 20, height - 20);
            else
                g.fillOval(10, 10, width - 20, height - 20);
        }
    }

}