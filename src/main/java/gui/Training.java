package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Training extends JFrame implements Runnable, ActionListener {

    public static JButton run;
    public static JButton exit;
    public static JButton stop;
    public static Thread demo;
    public static int i = 1, j = 1;

    //用到两个过时方法。还不知道怎么解决
    public void run() {
        while (true) {
            System.out.println(i++);
            try {
                demo.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Training he = new Training();
        demo = new Thread(he, "线程");
        // demo.start();
        //demo.setDaemon(false);
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.CENTER));

        run = new JButton("运行");
        exit = new JButton("退出");
        stop = new JButton("停止");

        run.addActionListener(he);
        exit.addActionListener(he);
        stop.addActionListener(he);

        jp.add(run);
        jp.add(stop);
        jp.add(exit);
        he.setLayout(new FlowLayout(FlowLayout.CENTER));
        he.add(jp);
        he.setLocation(300, 200);
        he.setSize(200, 100);
        he.setTitle("测试");
        he.setVisible(true);

    }

    public void actionPerformed(ActionEvent b) {

        if (b.getSource() == run) {
            System.out.println("线程正在运行");

            System.out.println(demo.getState().equals("BLOCKED"));

            if (j == 1) {
                demo.start();
                j++;
            }

            return;
        }
        if (b.getSource() == exit) {
            System.out.println("退出");

            System.out.println(demo.activeCount());
            System.exit(0);
            return;
        }
        if (b.getSource() == stop) {
            //
            //  try {
            //  demo.wait(11111);
            //  } catch (InterruptedException e) {
            //  // TODO Auto-generated catch block
            //  e.printStackTrace();
            //  }
            i = 1;
            System.out.println("线程停止");
            return;
        }

    }

}