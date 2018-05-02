import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SnakeGameMain extends JPanel implements ActionListener {

    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int  speed = 10;

    Snake s = new Snake(5,6,5,5);
    Apple apple = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    Apple apple1 = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    Timer timer = new Timer(1000/speed,this);


    public SnakeGameMain(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);

    }


    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);

        for ( int x = 0; x < WIDTH*SCALE; x += SCALE){
            g.setColor(Color.white);
            g.drawLine(x,0,x,HEIGHT*SCALE);
        }
        for ( int y = 0; y < WIDTH*SCALE; y += SCALE){
            g.setColor(Color.white);
            g.drawLine(0,y,WIDTH*SCALE,y);
        }

        g.setColor(Color.green);
        g.fillOval(apple.posX*SCALE+4,apple.posY*SCALE+4,SCALE-8,SCALE-8);
        g.setColor(Color.green);
        g.fillOval(apple1.posX*SCALE+4,apple1.posY*SCALE+4,SCALE-8,SCALE-8);

        for(int l = 1; l < s.lenght;l++){
            g.setColor(Color.red);
            g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE - 6, SCALE - 6);
            g.setColor(Color.BLUE);
            g.fillRect(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE - 6, SCALE - 6);

        }

    }


    public static void main(String [] args){
        jFrame = new JFrame("Title");
        jFrame.setSize(WIDTH*SCALE+6,HEIGHT*SCALE+28);

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(new SnakeGameMain());

        jFrame.setVisible(true);
        //jFrame.add(new JButton("Кнопка"));




    }


    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        if ((s.sX[0] == apple.posX)&&(s.sY[0] == apple.posY)){
            apple.setRandomPosition();
            s.lenght++;
        }
        for(int l = 1; l < s.lenght;l++){
            if ((s.sX[l] == apple.posX)&&(s.sY[l] == apple.posY)){
                apple.setRandomPosition();
            }
            if ((s.sX[0] == s.sX[l])&&(s.sY[0] == s.sY[l])){
                timer.stop();
                JOptionPane.showMessageDialog(null,"GG WP, GO RE?");
                jFrame.setVisible(false);
                s.lenght = 2;
                s.direction = 0;
                apple.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();
            }
        }
        if ((s.sX[0] == apple1.posX)&&(s.sY[0] == apple1.posY)){
            apple1.setRandomPosition();
            s.lenght++;
        }
        for(int l = 1; l < s.lenght;l++){
            if ((s.sX[l] == apple1.posX)&&(s.sY[l] == apple1.posY)){
                apple1.setRandomPosition();
            }
            if ((s.sX[0] == s.sX[l])&&(s.sY[0] == s.sY[l])){
                timer.stop();
                JOptionPane.showMessageDialog(null,"GG WP, GO RE?");
                jFrame.setVisible(false);
                s.lenght = 2;
                s.direction = 0;
                apple1.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();
            }
        }
        //apple.setRandomPosition();
        repaint();
    }

    public class KeyBoard extends KeyAdapter{
        public void keyPressed (KeyEvent event){
            int key = event.getKeyCode();

            if ((key == KeyEvent.VK_UP)&&(s.direction!= 2)) s.direction = 0;
            if ((key == KeyEvent.VK_DOWN)&&(s.direction!= 0)) s.direction = 2;
            if ((key == KeyEvent.VK_RIGHT)&&(s.direction!= 3)) s.direction = 1;
            if ((key == KeyEvent.VK_LEFT)&&(s.direction!= 1)) s.direction = 3;

        }
    }
}
