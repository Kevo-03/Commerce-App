public class NoSuchItemException extends Exception
{
    public NoSuchItemException()
    {

    }

    public NoSuchItemException(String massage)
    {
        super(massage);
    }

    public NoSuchItemException(String massage, Throwable e)
    {
        super(massage,e);
    }

    public NoSuchItemException(Throwable e)
    {
        super(e);
    }
    
}
