import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener
{
    Display display;

    //Map map;
    Car car;

    boolean[] key = {false, false, false, false};

    public Game()
    {
        super();

        initFrame();

        init();
        start();
    }

    private void initFrame()
    {
        this.setSize(1000,563);
        this.setResizable(false);
        this.addKeyListener(this);
        Global.game = this;

        display = new Display();
        this.setContentPane(display);

        this.setVisible(true);

        display.setPanelSize();
    }

    private void init()
    {
        car = new Car();
    }

    private void start()
    {
        boolean isFinished = false;

        while (! isFinished)
        {
            car.move(key);
            car.update();

            display.drawMap();
            car.draw();

            display.repaint();

            Global.waitFor(10);
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case 81:
            case 37:
                key[0] = true;
                break;
            case 90:
            case 38:
                key[1] = true;
                break;
            case 68:
            case 39:
                key[2] = true;
                break;
            case 83:
            case 40:
                key[3] = true;
                break;
            case 32:
                car.angleOff = 5;
        }
        //System.out.println(""+e.getKeyCode()+key[0]+key[1]+key[2]+key[3]);
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case 81:
            case 37:
                key[0] = false;
                break;
            case 90:
            case 38:
                key[1] = false;
                break;
            case 68:
            case 39:
                key[2] = false;
                break;
            case 83:
            case 40:
                key[3] = false;
                break;
            case 32:
                car.angleOff = 2.5;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

}
