import com.sun.javafx.geom.Vec2d;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Vector;

public class Car
{
    Vec2d pos;

    double speed, acc;
    double angle;

    double speedOff = 0.1;
    double accOff = 0.01f;
    double angleOff = 2.5;

    BufferedImage image;
    BufferedImage imageToDraw;

    public Car()
    {
        init();
        initImage();
    }

    private void init()
    {
        pos = new Vec2d(0, 0);

        speed = 0;
        acc = 0;

        angle = 0;
    }

    private void initImage()
    {

        File f = new File("car.png");

        try
        {
            image = ImageIO.read(f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static BufferedImage rotate(BufferedImage img, double angle)
    {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphic = rotatedImage.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(img, null, 0, 0);
        graphic.dispose();

        return rotatedImage;
    }

    public void move(boolean[] key)
    {
        if (key[1] && speed < 3)
        {
            acc += accOff;
        }
        else
        {
            if (speed > 0)
            {
                acc = -0.01;
            }
            else
            {
                acc = 0;
            }
        }
        if (key[3])
        {
            acc = 0;
            if (speed >= 0)
            {
                speed-=0.1;
            }
            else
            {
                speed = 0;
            }
        }

        if (key[0])
        {
            angle -= angleOff;
        }

        if (key[2])
        {
            angle += angleOff;
        }
        angle = angle%360;

        System.out.println(""+angle);
    }

    public void update()
    {
        if (speed < 5)
        {
            speed += acc;
        }

        pos.x += speed*Math.cos(Math.toRadians(angle));
        pos.y += speed*Math.sin(Math.toRadians(angle));
    }

    public void draw()
    {
        imageToDraw = rotate(image, angle);
        Global.display.drawCar(imageToDraw, pos.x, pos.y);
    }
}
