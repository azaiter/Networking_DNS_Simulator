import java.net.DatagramSocket;
import java.util.Scanner;
/**
 * The DNS_UDP_Client implements a UDP client for simple DNS program.
 * Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server
 * and the server will reply with IP of the domain. Sending zero as response will terminate the connection of the client.
 *
 * @author Abdulrahman Zaiter
 * @version 1.0
 * @since 2017-09-04
 */
public class DNS_UDP_Client {
    public static void main(String[] args) throws Exception {
        // get a datagram socket and initialize input scanner
        DatagramSocket socket = new DatagramSocket(DNS_Utils.UDP_CLIENT_PORT);
        Scanner sc = new Scanner(System.in);
        System.out.printf("Domain Name Finder - UDP Client.\n");
        // keep requesting until program termination "requesting zero as domain name"
        while(true) {
            System.out.printf("Type in the domain name (Zero to exit): ");
            String domainName = sc.next();
            // existing condition to break out of the loop and terminate program.
            if(domainName.equals("0")){
                DNS_Utils.sendUDPRequest(domainName, DNS_Utils.UDP_SERVER_HOST, DNS_Utils.UDP_SERVER_PORT, socket);
                socket.close();
                break;
            }
            // send request
            DNS_Utils.sendUDPRequest(domainName, DNS_Utils.UDP_SERVER_HOST, DNS_Utils.UDP_SERVER_PORT, socket);
            System.out.printf("Sent: %s\n", domainName);
            // get response and output it on the screen
            System.out.printf("The ip returned from server is: %s\n\n", DNS_Utils.receiveUDPRequest(socket, 1024).getKey());
        }
    }
}
