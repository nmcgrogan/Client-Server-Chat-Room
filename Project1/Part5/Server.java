package Project1.Part5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    int port = 3001;
    // connected clients
    // private List<ServerThread> clients = new ArrayList<ServerThread>();
    private List<Room> rooms = new ArrayList<Room>();
    private Room lobby = null;// default room

    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket incoming_client = null;
            System.out.println("Server is listening on port " + port);
            // Reference server statically
            Room.server = this;// all rooms will have the same reference
            // create a lobby on start
            lobby = new Room("Lobby");
            rooms.add(lobby);
            do {
                System.out.println("waiting for next client");
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, lobby);
                    sClient.start();

                    joinRoom("lobby", sClient);
                    incoming_client = null;

                }
            } while ((incoming_client = serverSocket.accept()) != null);
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }

    /***
     * Helper function to check if room exists by case insensitive name
     * 
     * @param roomName The name of the room to look for
     * @return matched Room or null if not found
     */
    private Room getRoom(String roomName) {
        for (int i = 0, l = rooms.size(); i < l; i++) {
            if (rooms.get(i).getName().equalsIgnoreCase(roomName)) {
                return rooms.get(i);
            }
        }
        return null;
    }

    /***
     * Attempts to join a room by name. Will remove client from old room and add
     * them to the new room.
     * 
     * @param roomName The desired room to join
     * @param client   The client moving rooms
     * @return true if reassign worked; false if new room doesn't exist
     */
    protected synchronized boolean joinRoom(String roomName, ServerThread client) {
        Room newRoom = roomName.equalsIgnoreCase("lobby")?lobby:getRoom(roomName);
        Room oldRoom = client.getCurrentRoom();
        if (newRoom != null) {
            if (oldRoom != null) {
                System.out.println(client.getName() + " leaving room " + oldRoom.getName());
                oldRoom.removeClient(client);
            }
            System.out.println(client.getName() + " joining room " + newRoom.getName());
            newRoom.addClient(client);
            return true;
        } else {
            client.sendMessage("Server",
                    String.format("Room %s wasn't found, please try another", roomName));
        }
        return false;
    }

    /***
     * Attempts to create a room with given name if it doesn't exist already.
     * 
     * @param roomName The desired room to create
     * @return true if it was created and false if it exists
     */
    protected synchronized boolean createNewRoom(String roomName) {
        if (getRoom(roomName) != null) {
            // TODO can't create room
            System.out.println(String.format("Room %s already exists", roomName));
            return false;
        } else {
            Room room = new Room(roomName);
            rooms.add(room);
            System.out.println("Created new room: " + roomName);
            return true;
        }
    }

    protected synchronized void removeRoom(Room r) {
        if (rooms.removeIf(room -> room == r)) {
            System.out.println("Removed empty room " + r.getName());
        }
    }

    protected synchronized void broadcast(String message) {
        if (processCommand(message)) {

            return;
        }
        // loop over rooms and send out the message
        Iterator<Room> it = rooms.iterator();
        while (it.hasNext()) {
            Room room = it.next();
            if (room != null) {
                room.sendMessage(null, message);
            }
        }
    }

    private boolean processCommand(String message) {
        System.out.println("Checking command: " + message);
        // TODO
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

/**
     * Handles the roll command with two different formats.
     * /roll X will roll a number between 1 and X.
     * /roll XdY will roll Y-sided dice X times.
     * UCID: [nm874]
     * Date: [11/12/23]
     * @param command The roll command from the client.
     * @return Result of the roll.
     */
    public String handleRollCommand(String command) {
        final Pattern rollPattern = Pattern.compile("^/roll (\\d+)(d(\\d+))?$");
        Matcher matcher = rollPattern.matcher(command);

        if (matcher.find()) {
            if (matcher.group(3) != null) {
                // Roll dice in the format XdY (e.g., 2d6)
                int rolls = Integer.parseInt(matcher.group(1));
                int sides = Integer.parseInt(matcher.group(3));
                return rollDice(rolls, sides);
            } else {
                // Roll a number from 1 to X (e.g., /roll 20)
                int max = Integer.parseInt(matcher.group(1));
                return "Rolled: " + rollNumber(1, max, null);
            }
        } else {
            return "Invalid roll command";
        }
    }

    private String rollDice(int rolls, int sides) {
        Random random = new Random();
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < rolls; i++) {
            if (i > 0) results.append(", ");
            results.append(rollNumber(1, sides, random));
        }
        return "Rolled dice: " + results;
    }

    private int rollNumber(int min, int max, Random random) {
        return random.nextInt(max - min + 1) + min;
    }

/**
 * Handles the flip command to simulate a coin toss.
 * @return Result of the coin flip.
 */
public String handleFlipCommand() {
    Random random = new Random();
    return random.nextBoolean() ? "Heads" : "Tails";
}
/**
 * Controller for handling various text commands, including text formatting.
 * <p>
 * This method checks if the input text contains specific patterns for text formatting
 * such as bold, italics, color, and underline, and applies the corresponding transformations.
 * Add more formatting options here as needed.
 * </p>
 *
 * @param text The input text potentially containing formatting commands.
 * @return The formatted text.
 */
public String handleTextFormatting(String text) {
    // Processing for Bold text: *text* -> <b>text</b>
    text = text.replaceAll("\\*(.*?)\\*", "<b>$1</b>");

    // Processing for Italics text: _text_ -> <i>text</i>
    text = text.replaceAll("_(.*?)_", "<i>$1</i>");

    // Processing for Colored text: {#hexcode}text{} -> <span style='color:#hexcode;'>text</span>
    // This will match hex color codes
    text = text.replaceAll("\\{#([0-9A-Fa-f]{6})\\}(.*?)\\{\\}", "<span style='color:#$1;'>$2</span>");

    // Processing for Underlined text: __text__ -> <u>text</u>
    text = text.replaceAll("__(.*?)__", "<u>$1</u>");

    return text;
}}