import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;

class MyCanvas extends JComponent {

    HashMap<Integer, String> map = new HashMap<Integer, String>(225);


    int busercount = 0;
    int rusercount = 0;

    public void paint(Graphics g)
    {
        int xEnd = getWidth()/15;
        int yEnd = getHeight()/15;
        int rcount = 0;
        int bcount = 0;
        Random r = new Random();
        for(int y=0; y<getHeight(); y+=yEnd){
            for(int x=0; x<getWidth(); x+=xEnd){
                int rect = r.nextInt(0,2);
                int color = r.nextInt(0,2);
                //if(rect == 0)
                //   continue;
                if(color == 0) {
                    map.put(x+y, "blue");
                    bcount += 1;
                    g.setColor(Color.BLUE);
                }
                else {
                    map.put(x+y, "red");
                    rcount += 1;
                    g.setColor(Color.red);
                }
                g.fillRect(x,y,xEnd, yEnd);
            }
        }

        System.out.println("rcount: "+rcount+" bcount: "+bcount);

    }
    public MyCanvas() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int xEnd = getWidth()/15;
                int yEnd = getHeight()/15;
                int x = me.getX()/xEnd;
                int y = me.getY()/yEnd;
                if(map.get((x*xEnd)+(y*yEnd))=="blue") {
                    map.put((x*xEnd)+(y*yEnd), "white");
                    busercount += 1;
                }
                else if(map.get((x*xEnd)+(y*yEnd)) == "red") {
                    map.put((x*xEnd)+(y*yEnd), "white");
                    rusercount += 1;
                }
                System.out.println("Red: "+rusercount+" Blue: "+busercount);
                Color co = Color.WHITE;
                Graphics g = getGraphics();
                g.setColor(co);
                g.fillRect(x*xEnd, y*yEnd, xEnd, yEnd);
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

    public class Canvas extends JFrame{
    private Timer timer;
    private int count = 0;

    public Canvas() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);
        window.getContentPane().add(new MyCanvas());

        window.setVisible(true);

        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                //What should the timer do?
                count++;
                if(count>45) {
                    timer.stop();
                    window.dispose();
                }
            }
        };
        timer = new Timer(1000, taskPerformer);

        JButton start = new JButton("Start");

        timer.start();

    }

    public static void main(String[] a)
    {
        SwingUtilities.invokeLater(Canvas::new);
    }
}
