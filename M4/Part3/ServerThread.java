package M4.Part3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private boolean isRunning = false;
    private ObjectOutputStream out; //exposed here for send()
    private Server server; // ref to our server so we can call methods on it more easily
   
    private void info(String message) {
        System.out.println(String.format("Thread[%s]: %s", getId(), message));
    }

    public ServerThread(Socket myClient, Server server) {
        info("Thread created");
        this.client = myClient;
        this.server = server;
    }

    public void disconnect() {
        info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    public boolean send(String message) {
        // added a boolean so we can see if the send was successful
        try {
            out.writeObject(message);
            return true;
        } catch (IOException e) {
            info("Error sending message to client (most likely disconnected)");
            cleanup();
            return false;
        }
    }

    @Override
    public void run() {
        info("Thread starting");
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
            this.out = out;
            isRunning = true;
            String fromClient;
            while (isRunning && (fromClient = (String) in.readObject()) != null) {
                info("Received from client: " + fromClient);

                if (fromClient.startsWith("shuffle")) {
                    String messageToShuffle = fromClient.substring("shuffle".length()).trim();
                    String shuffledMessage = shuffleSimpleMessage(messageToShuffle);
                    server.broadcast("User[" + this.getId() + "]: " + shuffledMessage, this.getId());
                
                } else {
                    String fromClientName = "User[" + this.getId() + "]: ";
                    server.broadcast(fromClientName + fromClient, this.getId());
                }
            }

        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            info("Client disconnected");
        } finally {
            isRunning = false;
            info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    private String shuffleSimpleMessage(String message) {
        List<Character> characters = new ArrayList<>();
        for (char c : message.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder shuffledMessage = new StringBuilder();
        for (char c : characters) {
            shuffledMessage.append(c);
        }
        return shuffledMessage.toString();
    }

    private void cleanup() {
        info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            info("Client already closed");
        }
        info("Thread cleanup() complete");
    }
}
