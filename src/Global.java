public class Global
{
    public static Game game;
    public static Display display;

    public static void waitFor(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();e.printStackTrace();
        }
    }
}
