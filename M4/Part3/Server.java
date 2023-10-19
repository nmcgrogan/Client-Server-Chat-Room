package M4.Part3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Server {
    int port = 3001;
    // connected clients
    private List<ServerThread> clients = new ArrayList<ServerThread>();
    
    public String shuffleMessage(String message) {
        List<Character> characters = new ArrayList<>();
        for (char c : message.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder shuffledMessage = new StringBuilder();
        for (char c : characters) {
            shuffledMessage.append(c);
        }
        System.out.println("Original: " + message + " | Shuffled: " + shuffledMessage.toString());  // debug line
        return shuffledMessage.toString();
    }
    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            System.out.println("Server is listening on port " + port);
            while (true) {  
                System.out.println("waiting for next client");
                Socket incoming_client = serverSocket.accept(); // <--- Keep this one
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, this);
                    clients.add(sClient);
                    sClient.start();

                    // This section is questionable; unsure of its purpose in the server code.
                    // Commented it out for now.
                    // Client clientInstance = new Client();
                    // clientInstance.setServerThread(sClient);
                }
            }
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }
    protected synchronized void disconnect(ServerThread client) {
		long id = client.getId();
        client.disconnect();
		broadcast("Disconnected", id);
	}
    
    protected synchronized void broadcast(String message, long id) {
        if (message.toLowerCase().startsWith("shuffle ")) {
            String messageToShuffle = message.substring("shuffle ".length());
            String shuffledMessage = shuffleMessage(messageToShuffle);
            // Send back the shuffled message to the client who sent the command
            sendToClient(shuffledMessage, id);
            return; // Exit the method after processing the command
        }
        if(processCommand(message, id)){

            return;
        }
        // let's temporarily use the thread id as the client identifier to
        // show in all client's chat. This isn't good practice since it's subject to
        // change as clients connect/disconnect
        if (!message.startsWith("User[")) {
            message = String.format("User[%d]: %s", id, message);
        }
        // end temp identifier 
        // loop over clients and send out the message
        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from list", client.getId()));
                it.remove();
                broadcast("Disconnected", id);
            }
        }
    }
    protected synchronized void sendToClient(String message, long clientId) {
        for (ServerThread client : clients) {
            if (client.getId() == clientId) {
                System.out.println("Sending to client with ID: " + clientId); 
                client.send(message);
                break;
            }
        }
    }
    private boolean processCommand(String message, long clientId){
        System.out.println("Checking command: " + message);
        if(message.equalsIgnoreCase("disconnect")){
            Iterator<ServerThread> it = clients.iterator();
            while (it.hasNext()) {
                ServerThread client = it.next();
                if(client.getId() == clientId){
                    it.remove();
                    disconnect(client);
                    
                    break;
                }
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // can ignore, will either be index out of bounds or type mismatch
            // will default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}