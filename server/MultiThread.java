import java.io.*;
import java.net.*;

/**
 * Klasa obsługująca połączenie serwera z jednym klientem.
 */
public class MultiThread<T extends Comparable<T>> extends Thread
{
    private Socket socket;
    private BinaryTree<T> tree;

    /**
     * Konstruktor klasy MultiThread.
     * @param socket Referencja na socket'a obsługującego połączenie z klientem.
     */
    public MultiThread(Socket socket)
    {
        this.socket = socket;
        tree = new BinaryTree<T>();
    }

    /**
     * Funkcja konwertuje łańcuch znaków na zadnay typ <T>.
     * @param str Łańcuch, który ma być przekonwertowany.
     * @return Przekonwertowany łańcuch znaków.
     */
    public T getVal(String str)
    {
        return null;
    }

    /**
     * Funkcja uruchamia wątek i zaczyna przyjmować polecenia od klienta.
     */
    @Override
    public void run()
    {
        try
        {
            InputStream input = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
    
            OutputStream output = socket.getOutputStream();
            PrintWriter out = new PrintWriter(output, true);
    
            String line;
            do
            {
                line = in.readLine();
                
                String[] command = line.split(" ");

                T value = getVal("0");
                if (command.length > 1)
                {
                    try { value = getVal(command[1]); }
                    catch (final NumberFormatException ex)
                    {
                        out.println("Conversion error");
                        continue;
                    }
                }

                switch (command[0])
                {
                case "search":
                    out.println(tree.search(value));
                    break;

                case "insert":
                    tree.insert(value);
                    out.println(tree.draw());
                    break;

                case "delete":
                    tree.remove(value);
                    out.println(tree.draw());
                    break;

                case "draw":
                    out.println(tree.draw());
                    break;

                case "exit":
                    break;

                default:
                    out.println("Unrecognised command");
                }
    
            } while (!line.equals("exit"));
            socket.close();
            T temp = getVal("0");
            System.out.println("Client disconnected: " + temp.getClass().getSimpleName());
        }
        catch (final IOException ex)
        {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}