import javafx.util.Pair;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
        } catch (Exception e){e.printStackTrace();}
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
        } catch (Exception e){e.printStackTrace();}
        return valueToReturn;
    }


    /**
     * A method that sends a UDP string request using datagram packets and socket.
     * @param requestString the string request to be used.
     * @param hostString the string of the hostname of the target device.
     * @param port the port used in the UDP communication
     * @param socket the socket instance used to initialize UDP connection
     * @throws Exception an exception is thrown if the socket hasn't had a proper UDP connection.
     */
    static void sendUDPRequest(String requestString, String hostString, int port, DatagramSocket socket) throws Exception{
        byte[] buf = requestString.getBytes();
        InetAddress inetAddress = InetAddress.getByName(hostString);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, inetAddress, port);
        socket.send(packet);
    }

    /**
     * A method that receives a UDP string request from UDP datagram server.
     * @param socket the socket instance used to initialize the UDP connection
     * @param bufferSize the buffer size used to store read data
     * @return a string of the request sent from the server
     * @throws Exception an exception is thrown if the socket hasn't had a proper UDP connection
     */
    static Pair<String, DatagramPacket> receiveUDPRequest(DatagramSocket socket, int bufferSize) throws Exception{
        byte[] receiveBuffer = new byte[bufferSize];
        DatagramPacket packet = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(packet);
        return new Pair<>(new String(receiveBuffer, 0, packet.getLength()), packet);
    }
}
