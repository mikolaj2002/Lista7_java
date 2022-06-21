import java.net.Socket;

/**
 * Klasa obsługująca połączenie serwera z jednym klientem.
 */
public class MultiThreadInteger extends MultiThread<Integer>
{   
    /**
     * Konstruktor klasy MultiThreadInteger.
     * @param socket Referencja na socket'a obsługującego połączenie z klientem.
     */
    public MultiThreadInteger(Socket socket)
    {
        super(socket);
    }

    /**
     * Funkcja konwertuje łańcuch znaków na zadnay typ Integer.
     * @param str Łańcuch, który ma być przekonwertowany.
     * @return Przekonwertowany łańcuch znaków.
     */
    @Override
    public Integer getVal(String str) throws NumberFormatException
    {
        try { return Integer.parseInt(str); }
        catch (final NumberFormatException ex)
        { throw ex; }
    }
}
