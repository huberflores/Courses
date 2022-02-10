package ee.ut.cs.ds.sample.rpc2;

import java.lang.reflect.Method;
import java.util.stream.*;
import java.lang.*;
import java.net.*;
import java.io.*;

public class Server implements Runnable {
    int portNumber; // port number to use. Hostname/IP is assumed to be the same (localhost).
    volatile boolean exit = false; // flag to exit while loop

    public Server(int port){
        portNumber = port;
    }
    // Unpack message and invoke method with correct args and instance environment.
    Object processMessage(Message msg) throws Exception { 
        Class<?>[] paramTypes = Stream.of(msg.paramValues).map(Object::getClass).toArray(Class<?>[]::new);
        Method toExecute = msg.classInstance.getClass().getDeclaredMethod(
                msg.methodName,
                paramTypes);
        return toExecute.invoke(
                msg.classInstance,
                msg.paramValues[0],
                msg.paramValues[1]);
    }

    // Can be called by Thread or run as a blocking call
    public void run() {
        ServerSocket serverSocket; 
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        while (!exit) {
            try {
                System.out.println("Server stub: Waiting for client...");
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                System.out.println("Server stub: Connected to client stub!");
                Message msg;
                while ((msg = (Message)in.readObject()) != null) {
                    System.out.format(
                            "Server stub: Received method '%s' from class '%s' ...\n", 
                            msg.methodName, msg.classInstance.getClass().getName());
                    Object result = processMessage(msg);
                    out.writeObject(result);
                    System.out.println("Server stub: Message processed, returning results...");
                }
            } catch (EOFException e) {
                System.out.println("Server stub: Processed all messages, closing connection.\n");
            } catch (Exception e) {
                System.out.println("Server stub: Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Method to stop while loop
    public void stop(){
        exit = true;
        System.out.println("Server stub: Closing server.");
    }

    // Extremely basic main method
    public static void main(String[] args){
        if (args.length != 1) {
            System.err.println("Usage: java Server <port number>");
            System.exit(1);
        }
        Server server = new Server(Integer.parseInt(args[0]));
        server.run();
    }
}
