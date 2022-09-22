package click;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;

class MyCanvasBattle extends JComponent {

    HashMap<Integer, String> map = new HashMap<Integer, String>(225);


    int busercount = 0;
    int rusercount = 0;

    public void paint(Graphics g)
    {
        int xCenter = getWidth() / 2;
        int xEnd = getWidth()/15;
        int yEnd = getHeight()/15;
        int rcount = 0;
        int bcount = 0;
        Random r = new Random();
        g.drawLine(xCenter, getHeight(), xCenter, 0);

        System.out.println("rcount: "+rcount+" bcount: "+bcount);

    }
    public MyCanvasBattle() {
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent me) {
                if(me.getX()>=getWidth()/2) {
                    busercount += 1;
                    Graphics g=getGraphics();
                    g.setColor(Color.BLUE);
                    g.fillOval(me.getX(),me.getY(),5,5);
                }
                else if(me.getX()<(getWidth()/2)) {
                    rusercount += 1;
                    Graphics g=getGraphics();
                    g.setColor(Color.RED);
                    g.fillOval(me.getX(),me.getY(),5,5);
                }
                System.out.println(" red: "+rusercount+" blue: "+busercount);
            }
        });
    }

    public void winner(){
        if(busercount > rusercount) {
            String win = "blue";
            System.out.println(win);
        }
        else if (rusercount > busercount) {
            String win = "red";
            System.out.println(win);
        }
    }

}

public class CanvasBattle extends JFrame{
    private Timer timer;
    private int count = 0;
    private MyCanvasBattle myCanvas;

    public CanvasBattle() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);

        window.setVisible(true);

        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                //What should the timer do?
                count++;
                if(count>30) {
                    timer.stop();
                    window.dispose();
                }
                //System.out.println(count);
            }
        };
        timer = new Timer(1000, taskPerformer);

        window.getContentPane().add(new MyCanvasBattle());
        timer.start();
    }

    public static void main(String[] a)
    {
        SwingUtilities.invokeLater(CanvasBattle::new);
    }
}
