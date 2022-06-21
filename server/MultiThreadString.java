import java.net.Socket;

/**
 * Klasa obsługująca połączenie serwera z jednym klientem.
 */
public class MultiThreadString extends MultiThread<String>
{
    /**
     * Konstruktor klasy MultiThreadString.
     * @param socket Referencja na socket'a obsługującego połączenie z klientem.
     */
    public MultiThreadString(Socket socket)
    {
        super(socket);
    }

    /**
     * Funkcja konwertuje łańcuch znaków na zadnay typ String.
     * @param str Łańcuch, który ma być przekonwertowany.
     * @return Przekonwertowany łańcuch znaków.
     */
    @Override
    public String getVal(String str)
    {
        return str;
    }
}
