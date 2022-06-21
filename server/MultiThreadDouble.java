import java.net.Socket;

/**
 * Klasa obsługująca połączenie serwera z jednym klientem.
 */
public class MultiThreadDouble extends MultiThread<Double>
{
    /**
     * Konstruktor klasy MultiThreadDouble.
     * @param socket Referencja na socket'a obsługującego połączenie z klientem.
     */
    public MultiThreadDouble(Socket socket)
    {
        super(socket);
    }

    /**
     * Funkcja konwertuje łańcuch znaków na zadnay typ Double.
     * @param str Łańcuch, który ma być przekonwertowany.
     * @return Przekonwertowany łańcuch znaków.
     */
    @Override
    public Double getVal(String str) throws NumberFormatException
    {
        try { return Double.parseDouble(str); }
        catch (final NumberFormatException ex)
        { throw ex; }
    }
}
