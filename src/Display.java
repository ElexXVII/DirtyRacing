import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Display extends JPanel
{
    BufferedImage bp;
    Graphics bg;

    BufferedImage map;
    int W, H;

    public Display()
    {
        super();
        Global.display = this;

        loadImage();
    }

    private void loadImage()
    {
        File f = new File("map.png");

        try
        {
            map = ImageIO.read(f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setPanelSize()
    {
        W = getWidth();
        H = getHeight();

        createBufferedPainting();
    }

    private void createBufferedPainting()
    {
        bp = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
        bg = bp.getGraphics();
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(bp, 0, 0, null);
    }

    public void drawMap()
    {
        bg.setColor(Color.ORANGE);
        bg.drawImage(map, 0, 0, W, H, null);
    }

    public void drawCar(BufferedImage image, double x, double y)
    {
        bg.drawImage(image, (int)x, (int)y, 50, 50, null);
    }
}
