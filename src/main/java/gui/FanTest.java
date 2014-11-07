package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FanTest {

    public static void main(String[] args) throws InterruptedException {
        System.err.println("aa");
        Scanner s = new Scanner(System.in);
        s.next();
        System.out.println("aa" + new Thread().getState());
        JFrame frame = new JFrame("扇風機");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            int degree = 0;

            Image img;
            {
                img = new ImageIcon("src/main/java/gui/java.jpg").getImage();
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();
                g.drawImage(img, 0, 0, width, height, null);
                int radius = 75;

                degree += 30;

                int x = width / 2 - radius;
                int y = height / 2 - radius;

                int amplitude = 30;
                g.fillArc(x, y, radius * 2, radius * 2, degree, amplitude);
                g.fillArc(x, y, radius * 2, radius * 2, degree + 90, amplitude);
                g.fillArc(x, y, radius * 2, radius * 2, degree + 180, amplitude);
                g.fillArc(x, y, radius * 2, radius * 2, degree + 270, amplitude);
            }

            public Dimension getPreferredSize() {
                return new Dimension(250, 250);
            }
        };
        panel.setBorder(BorderFactory.createLineBorder(Color.yellow, 10));
        frame.add(panel);
        frame.setVisible(true);

        FanRunnable runnable = new FanRunnable(panel);
        Thread t = new Thread(runnable);
        t.start();

        JButton button = new JButton("切り替え");
        button.addActionListener(new StartEndListener(runnable));

        frame.pack();
        //  frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.add(button, BorderLayout.SOUTH);

    }
}

class StartEndListener implements ActionListener {
    static boolean isStopped = false;
    private FanRunnable runnable;

    public StartEndListener(FanRunnable runnable) {
        this.runnable = runnable;
    }


    public void actionPerformed(ActionEvent e) {
        if (!isStopped) {
            runnable.requestSuspend();
        }
        else {
            runnable.requestResume();
        }
        isStopped = !isStopped;
    }
}

class FanRunnable implements Runnable {

    private JPanel panel;

    private volatile boolean suspened = false;
    private Lock suspendLock = new ReentrantLock();
    private Condition cond = suspendLock.newCondition();

    public void requestSuspend() {
        suspened = true;
    }

    public void requestResume()
    {
        suspened = false;
        suspendLock.lock();
        try {
            cond.signalAll();
        } finally {
            suspendLock.unlock();
        }
    }

    public FanRunnable(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (suspened) {
                suspendLock.lock();
                try {
                    while (suspened) {
                        try {
                            cond.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } finally {
                    suspendLock.unlock();
                }
            }
            panel.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
