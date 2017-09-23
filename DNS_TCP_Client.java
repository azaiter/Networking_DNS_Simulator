import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * The DNS_TCP_Client implements a TCP client for simple DNS program.
 * Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server
 * and the server will reply with IP of the domain. Sending zero as response will terminate the connection of the client.
 *
 * @author Abdulrahman Zaiter
 * @version 1.0
 * @since 2017-09-04
 */
public class DNS_TCP_Client {
    public static void main(String[] args) {
        // Declare variables.
        Scanner sc = new Scanner(System.in);
        String domainName;
        System.out.printf("Domain Name Finder - TCP Client.\n");

        try{
            // Initialize socket, input stream and output stream
            Socket socket = new Socket(DNS_Utils.TCP_SERVER_HOST, DNS_Utils.TCP_SERVER_PORT);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Keep reading domain name from user, send it to the server, and receive ip from the server.
            while(true){
                // Read domain name from client
                System.out.printf("Type in the domain name (Zero to exit): ");
                domainName = sc.next();
                // Send it to server
                outputStream.writeObject(domainName);
                // If zero, no further data needed to be read as response, otherwise, read server response.
                if(domainName.equals("0")) break;
                // Read Server response.
                System.out.printf("Sent: %s\n", domainName);
                System.out.printf("The ip returned from server is: %s\n\n", inputStream.readObject().toString());
            }
            // Close the socket.
            socket.close();
        } catch (Exception e){
            System.out.println("I/O Error: Server disconnected ?");
        }
    }
}