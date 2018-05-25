import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SnakeGameMain extends JPanel implements ActionListener {
//Abdr helper. Здесь я просто добавил несколько констант
    public static JFrame jFrame;
    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static int  speed = 10;
    //Abdr helper. Здесь находятся сами яблоки (я сделал их без создания класса)
    Snake s = new Snake(5,6,5,5);
    Apple apple = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    Apple apple1 = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    Apple apple2 = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    Apple apple3 = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    Apple apple4 = new Apple(Math.abs((int) (Math.random()*SnakeGameMain.WIDTH - 1)),Math.abs((int) (Math.random()*SnakeGameMain.HEIGHT - 1)));
    //Abdr helper.Таймер который обновляет картинку в игре при каждом движении змейки (FPS)
    Timer timer = new Timer(1000/speed,this);

    //Abdr helper. Запуск этого таймера
    public SnakeGameMain(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);

    }

    //Abdr helper. Этот метод отвечает за расскраску
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
        g.setColor(Color.black);
        g.fillOval(apple1.posX*SCALE+4,apple1.posY*SCALE+4,SCALE-8,SCALE-8);
        g.setColor(Color.red);
        g.fillOval(apple2.posX*SCALE+4,apple2.posY*SCALE+4,SCALE-8,SCALE-8);
        g.setColor(Color.YELLOW);
        g.fillOval(apple3.posX*SCALE+4,apple3.posY*SCALE+4,SCALE-8,SCALE-8);
        g.setColor(Color.BLUE);
        g.fillOval(apple4.posX*SCALE+4,apple4.posY*SCALE+4,SCALE-8,SCALE-8);

        for(int l = 1; l < s.lenght;l++){
            g.setColor(Color.red);
            g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE - 6, SCALE - 6);
            g.setColor(Color.BLUE);
            g.fillRect(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE - 6, SCALE - 6);

        }

    }

    //Abdr helper. Здесь находится сам интерфейс( По умолчанию разрешение 600х600 )
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

    //Abdr helper. Здесь сама игра. Т.е. увеличение змейки при поедании яблок, а так же эфеекты при поедании других яблок
    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        if ((s.sX[0] == apple.posX)&&(s.sY[0] == apple.posY)){
            apple.setRandomPosition();
            apple1.setRandomPosition();
            apple2.setRandomPosition();
            apple3.setRandomPosition();
            apple4.setRandomPosition();
            s.lenght++;

        }
        for(int l = 1; l < s.lenght;l++){
            if ((s.sX[l] == apple.posX)&&(s.sY[l] == apple.posY)){
                apple.setRandomPosition();


            }
            if ((s.sX[0] == s.sX[l])&&(s.sY[0] == s.sY[l])){
                timer.stop();
                JOptionPane.showMessageDialog(null,"GG.Your score is " + s.lenght);
                jFrame.setVisible(false);
                s.lenght = 2;
                s.direction = 0;
                apple.setRandomPosition();
                apple1.setRandomPosition();
                apple2.setRandomPosition();
                apple3.setRandomPosition();
                apple4.setRandomPosition();
                jFrame.setVisible(true);
                timer.start();

            }
        }
        if ((s.sX[0] == apple1.posX)&&(s.sY[0] == apple1.posY)){
            apple1.setRandomPosition();
            timer.stop();
            JOptionPane.showMessageDialog(null,"YOu are poisoned, your score is "+ s.lenght);
            jFrame.setVisible(false);
            s.lenght = 2;
            s.direction = 0;
            apple1.setRandomPosition();
            jFrame.setVisible(true);
            timer.start();
        }
        if ((s.sX[0] == apple2.posX)&&(s.sY[0] == apple2.posY)) {
            s.lenght=s.lenght/2;
            apple2.setRandomPosition();
        }
        if ((s.sX[0] == apple3.posX)&&(s.sY[0] == apple3.posY)) {
            speed = speed*2;
            apple3.setRandomPosition();

        }
        if ((s.sX[0] == apple4.posX)&&(s.sY[0] == apple4.posY)) {
            apple2.setRandomPosition();
            apple.setRandomPosition();
            apple3.setRandomPosition();
            apple4.setRandomPosition();
            apple1.setRandomPosition();
        }
        if (s.lenght == 1){
            timer.stop();
            JOptionPane.showMessageDialog(null, "Youre too small mate, your score is " + s.lenght);
            jFrame.setVisible(false);
            s.lenght = 2;
            s.direction = 0;
            apple1.setRandomPosition();
            apple4.setRandomPosition();
            apple3.setRandomPosition();
            apple2.setRandomPosition();
            jFrame.setVisible(true);
            timer.start();
        }
        //Abdr helper. Just a test ( TO DO )
        /*
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
        */
        //apple.setRandomPosition();
        repaint();
    }
    //Abdr helper. Управление самой змейки
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
