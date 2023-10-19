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
        String[] parts = message.split(" ", 2);
        if (parts.length == 2 && parts[0].equalsIgnoreCase("shuffle")) {
            List<Character> characters = new ArrayList<>();
            for (char c : parts[1].toCharArray()) {
                characters.add(c);
            }
            Collections.shuffle(characters);
            StringBuilder shuffledMessage = new StringBuilder();
            for (char c : characters) {
                shuffledMessage.append(c);
            }
            return "shuffle " + shuffledMessage.toString(); // return the shuffled message
        }
        return message;
    }
    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                System.out.println("waiting for next client");
                Socket incoming_client = serverSocket.accept();
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
        message = processCommands(message, id);

        if (!message.startsWith("User[")) {
            message = String.format("User[%d]: %s", id, message);
        }

        
        // loop over clients and send out the message
        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from list", client.getId()));
                it.remove();
                broadcast("User[" + id + "]: Disconnected", id);
            }
        }
    }

    
    public String processCommands(String message, long threadId) {
        if (message.startsWith("shuffle")) {
            String messageToShuffle = message.substring("shuffle".length()).trim();
            System.out.println("Original message: " + messageToShuffle);
    
            ArrayList<Character> chars = new ArrayList<Character>(messageToShuffle.length());
            for (char c : messageToShuffle.toCharArray()) {
                chars.add(c);
            }
            Collections.shuffle(chars);
            char[] shuffled = new char[chars.size()];
            for (int i = 0; i < shuffled.length; i++) {
                shuffled[i] = chars.get(i);
            }
            String shuffledMessage = new String(shuffled);
            System.out.println("Shuffled message: " + shuffledMessage);
    
            return shuffledMessage;
        }
        return message; 
    }
     public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}