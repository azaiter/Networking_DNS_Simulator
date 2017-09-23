import javafx.util.Pair;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The DNS_Utils interface implements a repository of constant settings and helper functions for HW1 program.
 *
 * @author Abdulrahman Zaiter
 * @version 1.0
 * @since 2017-09-04
 */
public interface DNS_Utils {
    // declaring constants.
    int TCP_SERVER_PORT = 7878;
    String TCP_SERVER_HOST = "localhost";
    int UDP_SERVER_PORT = 7879;
    int UDP_CLIENT_PORT = 7880;
    String UDP_SERVER_HOST = "localhost";
    String TCP_RAF_FILENAME = "TCP_Count.tmp";
    String UDP_RAF_FILENAME = "UDP_Count.tmp";


    /**
     * A function that writes an integer in RAF at specific position
     * @param randomAccessFile this is the RAF instance
     * @param inCnt this is the new integer to be written
     * @param pos this is the position in the RAF to have the integer to be written at
     */
    static void setClientCount(RandomAccessFile randomAccessFile, int inCnt, int pos){
        try {
            randomAccessFile.seek(pos);
            randomAccessFile.writeInt(inCnt);
        } catch (Exception e){
            System.out.println("ERROR: can't write on the RAF file.");
        }
    }

    /**
     * A method that returns an integer in a RAF at specific position
     * @param randomAccessFile this is the RAF instance
     * @param pos this is the position in the RAF to have the integer read from
     * @return an integer in a RAF at specific position
     */
    static int getClientCount(RandomAccessFile randomAccessFile, int pos){
        int valueToReturn = 0;
        try {
            randomAccessFile.seek(pos);
            valueToReturn = randomAccessFile.readInt();
        } catch (Exception e){
            System.out.println("Can't read the RAF file.");
        }
        return valueToReturn;
    }


    /**
     * A method that sends a UDP string request using datagram packets and socket.
     * @param requestString the string request to be used.
     * @param hostString the string of the hostname of the target device.
     * @param port the port used in the UDP communication
     * @param socket the socket instance used to initialize UDP connection
     */
    static void sendUDPRequest(String requestString, String hostString, int port, DatagramSocket socket){
        try {
            byte[] buf = requestString.getBytes();
            InetAddress inetAddress = InetAddress.getByName(hostString);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, port);
            socket.send(packet);
        }
        catch (UnknownHostException e){
            System.out.println("ERROR: Can't parse host string to IP.");
        }
        catch (IOException e){
            System.out.println("ERROR: Can't send UDP packet.");
        }
    }

    /**
     * A method that receives a UDP string request from UDP datagram server.
     * @param socket the socket instance used to initialize the UDP connection
     * @param bufferSize the buffer size used to store read data
     * @return a string of the request sent from the server
     */
    static Pair<String, DatagramPacket> receiveUDPRequest(DatagramSocket socket, int bufferSize){
        try {
            byte[] receiveBuffer = new byte[bufferSize];
            DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(packet);
            return new Pair<>(new String(receiveBuffer, 0, packet.getLength()), packet);
        }
        catch (IOException e) {
            System.out.println("ERROR: Can't send UDP packet.");
            return new Pair<>("I/O ERROR", null);
        }
    }
}
