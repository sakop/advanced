package homework.chap12;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HangTest extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("吊人游戏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);

        final MyPanel panel = new MyPanel();
        frame.add(panel,BorderLayout.CENTER);

        JPanel lowPanel = new JPanel();
        lowPanel.setLayout(new GridLayout(1,2,10,10));

        JButton prev = new JButton("PREV");
        prev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.i -= 1;
                if(panel.i < 0){
                    panel.i = 1;
                }
                panel.repaint();
            }
        });



        JButton next = new JButton("NEXT");


        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.i += 1;
                if(panel.i == 2){
                    panel.i = 0;
                }
                panel.repaint();
            }
        });
        lowPanel.add(prev);
        lowPanel.add(next);

        frame.add(lowPanel,BorderLayout.SOUTH);


        frame.setVisible(true);
    }
}

class MyPanel extends JPanel {

    public int i = 0;

    public Dimension getPreferredSize() {
        return new Dimension(350, 350);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintRack(g);
        paintBody(g);
    }

    private void paintBody(Graphics g) {
        if (i == 0) {
            g.drawOval(120, 70, 60, 60);

            g.drawLine(150, 20, 150, 70);
            g.drawLine(150, 140, 120, 180);
            g.drawLine(150, 140, 180, 180);

            g.drawLine(150, 130, 150, 200);
            g.drawLine(150, 200, 100, 230);
            g.drawLine(150, 200, 200, 230);
        } else {
            g.drawOval(150, 70, 60, 60);

            g.drawLine(150, 20, 180, 70);
            g.drawLine(180, 130, 200, 200);
            g.drawLine(195, 180, 140, 130);
            g.drawLine(195, 180, 210, 120);

            g.drawLine(200, 200, 170, 250);
            g.drawLine(200, 200, 230, 240);
        }
    }

    private void paintRack(Graphics g) {
        g.drawArc(15, 270, 100, 100, 30, 120);
        g.drawLine(65, 20, 65, 270);
        g.drawLine(65, 20, 200, 20);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
        repaint();
    }
}
