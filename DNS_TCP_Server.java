import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * The DNS_TCP_Server implements a TCP server for simple DNS program.
 * Usage: open the server first, then the client and type in the Domain name. A request will be sent to the server
 * and the server will reply with IP of the domain. Sending zero as response will terminate the connection of the client.
 *
 * @author Abdulrahman Zaiter
 * @version 1.0
 * @since 2017-09-04
 */
public class DNS_TCP_Server {
    public static void main(String[] args) {
        System.out.printf("Domain Name Finder - TCP Server\n");
        try {
            // Initialize server socket
            ServerSocket srvSocket = new ServerSocket(DNS_Utils.TCP_SERVER_PORT);
            System.out.printf("Server is Listening on Port %d.\n\n", DNS_Utils.TCP_SERVER_PORT);
            // build a random access file to store clients count
            RandomAccessFile randomAccessFile = new RandomAccessFile("count.tmp", "rw");
            DNS_Utils.setClientCount(randomAccessFile, 0, 0);
            //keep serving clients until the program terminates.
            while(true){
                // accept incoming connection and add a client into the RAF
                Socket socket = srvSocket.accept();
                DNS_Utils.setClientCount(randomAccessFile, DNS_Utils.getClientCount(randomAccessFile, 0)+1, 0);
                // create a thread for each client
                new Thread(()->{
                    try {
                        // initialize input and output streames.
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        ObjectOutput outputStream = new ObjectOutputStream(socket.getOutputStream());
                        System.out.printf("Client Connected\n");
                        // keep serving client's requests.
                        while(true){
                            //read domain name from client
                            String domainName = inputStream.readObject().toString();
                            System.out.printf("Recieved: %s\n", domainName);
                            // if sent request is zero, decrease clients connected and break out of the loop
                            if(domainName.equals("0")){
                                System.out.printf("Client %s, Disconnected.\n", socket.getInetAddress().getHostAddress());
                                DNS_Utils.setClientCount(randomAccessFile, DNS_Utils.getClientCount(randomAccessFile, 0)-1, 0);
                                break;
                            }
                            // otherwise, send the ip of the domain to the client.
                            String ipAddressToSend = InetAddress.getByName(domainName).getHostAddress()
                                    .concat(". Current Client Count is : ")
                                    .concat(Integer.toString(DNS_Utils.getClientCount(randomAccessFile, 0)));
                            System.out.printf("Sending %s ip: %s to %s.\n", domainName, ipAddressToSend,
                                    socket.getInetAddress().getHostAddress());
                            outputStream.writeObject(ipAddressToSend);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        DNS_Utils.setClientCount(randomAccessFile, DNS_Utils.getClientCount(randomAccessFile, 0)-1, 0);
                    }
                }).start();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
