import java.io.*;
import java.net.*;

public class Server
{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(4444))
        {
            System.out.println("Server is listening on port 4444");
 
            while (true)
            {
                Socket socket = serverSocket.accept();

                InputStream input = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                OutputStream output = socket.getOutputStream();
                PrintWriter out = new PrintWriter(output, true);

                String line = in.readLine();

                if (line.equals("Integer"))
                    new MultiThreadInteger(socket).start();
                else if (line.equals("Double"))
                    new MultiThreadDouble(socket).start();
                else if (line.equals("String"))
                    new MultiThreadString(socket).start();
                else
                {
                    out.println("Unrecognised type");
                    socket.close();
                    continue;
                }

                System.out.println("New client connected: " + line);
            }
 
        }
        catch (final IOException ex)
        {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}