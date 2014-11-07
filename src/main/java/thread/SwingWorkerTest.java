package thread;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class SwingWorkerTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphic test");
        frame.setSize(250, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("start");
        button.addActionListener(new Lis());

        JButton stop = new JButton("interrupt");
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(Thread.currentThread().getName());
                Thread.currentThread().interrupt();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.add(stop);

        frame.setVisible(true);
    }
}

class Lis implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        new SwingWorker<Integer,String>() {

            @Override
            protected Integer doInBackground() throws Exception {
                int sum = 0;
                for(int i = 0;i < 109000000;i++){
                    sum += (int)(Math.random() * 10000);
                }
                System.out.println(Thread.currentThread().getName());
                return sum;
            }

            public void done(){
                try {
                    JOptionPane.showMessageDialog(null, get());
                } catch (HeadlessException | InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.execute();;


    }

}