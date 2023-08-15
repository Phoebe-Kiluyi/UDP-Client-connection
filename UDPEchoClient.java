import java.io.*;
import java.net.*;
import java.util.*;
public class UDPEchoClient{
private static InetAddress host;
private static final int PORT = 1234;
private static DatagramSocket datagramSocket;
private static DatagramPacket inPacket, outPacket;
private static byte [] buffer;
public static void main(String [] args){
try{
host = InetAddress.getLocalHost();
}
catch(UnknownHostException unEx){
System.out.println("Host ID not found!");
System.exit(1);
}
accessServer();
}
private static void accessServer(){
try{
datagramSocket = new DatagramSocket(); //Step 1
//set up a stream for keyboard entry
Scanner userEntry = new Scanner(System.in);
String message="", response="";
do{
System.out.println("Enter a message: ");
message = userEntry.nextLine();
if(!message.equals("***CLOSE***")){
outPacket = new 
DatagramPacket(message.getBytes(),
message.length(), host, PORT); //Step 2
datagramSocket.send(outPacket); //Step 3
buffer = new byte[256]; //Step 4
inPacket = new DatagramPacket(buffer, 
buffer.length); //Step 5
datagramSocket.receive(inPacket); //Step 6
response = new String(inPacket.getData(), 0,
inPacket.getLength()); //Step 7
System.out.println("\n SERVER> " + response);
}
}while(!message.equals("***CLOSE***"));
}
catch(IOException ioEx){
ioEx.printStackTrace();
}
finally{
System.out.println("\n Closing connection .. *");//Step 8
datagramSocket.close();
}
}
}