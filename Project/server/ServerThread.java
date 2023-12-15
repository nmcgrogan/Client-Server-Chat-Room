package Project.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import Project.common.Constants;
import Project.common.Payload;
//import Project.common.Payload.UserStatus;
import Project.common.PayloadType;
import Project.common.Phase;
import Project.common.RoomResultPayload;
/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    private Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
     private Server server;// ref to our server so we can call methods on i
    // more easily
    private Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myClientId;
    private TextStyling textStyling = new TextStyling();
    private Map<Long, String> clientIdToUsername = new HashMap<>();
    private Set<Long> mutedClients = new HashSet<>();
    private static final String MUTE_LIST_DIR = System.getProperty("user.home") + File.separator + "mute_lists";
   
    

    
    
    /*nm874 */
        private void saveMuteList() {
            try {
                // Create the directory if it doesn't exist
                File muteListDir = new File(MUTE_LIST_DIR);
                if (!muteListDir.exists()) {
                    muteListDir.mkdirs();
                    logger.info("Mute list directory created: " + muteListDir.getAbsolutePath());
                }
        
                // Specify the path for the mute list file
                String muteListFilePath = MUTE_LIST_DIR + File.separator + clientName + "mute_list.txt";
                logger.info("Generated mute list file path: " + muteListFilePath); // Logging the path
        
                // Create a mute list file
                File muteFile = new File(muteListFilePath);
        
                // Prepare data to be written
                String data = mutedClients.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));
                logger.info("Data to be written to mute list: " + data); // Logging the data
        
                // Use FileWriter to write data
                try (FileWriter writer = new FileWriter(muteFile)) {
                    writer.write(data);
                    logger.info("Mute list data written to file: " + muteFile.getAbsolutePath());
                }
                logger.info("Mute list saved at: " + muteFile.getAbsolutePath());
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to save mute list", e);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "An error occurred while saving the mute list", ex);
            }
        }

// Load mute list from file (when server starts)
private void loadMuteList() {
    try {
        File muteFile = new File(MUTE_LIST_DIR, clientName + "mute_list.txt");
        if (muteFile.exists()) {
            mutedClients.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(muteFile))) {
                String data = reader.readLine();
                if (data != null && !data.isEmpty()) {
                    Arrays.stream(data.split(","))
                    .map(String::trim)
                        .map(Long::parseLong)
                    .forEach(mutedClients::add);
                }
            }
        }
    } catch (IOException e) {
        // Handle file I/O errors gracefully
        e.printStackTrace();
    }
}
    

    public Set<Long> getMutedClients() {
        return new HashSet<>(mutedClients);
    }

  // Setter for mutedClients
public void muteClient(Long clientId) {
    updateMuteList("/mute " + getClientNameById(clientId), clientId, true);
}
public void unmuteClient(Long clientId) {
    updateMuteList("/unmute " + getClientNameById(clientId), clientId, false);

}
    

    public void setClientId(long id) {
        myClientId = id;
    }

    public long getClientId() {
        return myClientId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ServerThread(Socket myClient, Room room,Server server) {
        this.server = server;
        logger.info("ServerThread created");
        // get communication channels to single client
        this.client = myClient;
        this.currentRoom = room;
        loadMuteList();

    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            logger.warning("Invalid name being set");
            return;
        }
        clientName = name;
    clientIdToUsername.put(myClientId, name);
}

    public String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
        } else {
            logger.info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myClientId, getClientName(), false);
        logger.info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

   /*  // send methods
    public boolean sendGridReset(){
        Payload p = new Payload();
        p.setPayloadType(PayloadType.GRID_RESET);
        return send(p);
    }
    public boolean sendCells(List<CellData> cells){
        CellPayload cp = new CellPayload();
        cp.setCellData(cells);
        return send(cp);
    }

    public boolean sendGridDimensions(int x, int y){
        PositionPayload pp = new PositionPayload();
        pp.setCoord(x, y);
        pp.setPayloadType(PayloadType.GRID); //override default payload type
        return send(pp);
    }
    public boolean sendCurrentTurn(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.TURN);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendCharacter(long clientId, Character character) {
        CharacterPayload cp = new CharacterPayload();
        cp.setCharacter(character);
        cp.setClientId(clientId);
        return send(cp);
    }
*/
    public boolean sendPhaseSync(Phase phase) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.PHASE);
        p.setMessage(phase.name());
        return send(p);
    }

    public boolean sendReadyStatus(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.READY);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        if (message != null) {
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        if (mutedClients.contains(clientId)) {
            // Skip sending if the recipient has muted the sender
            return true;
        }
            // Process the message for styling
        message = textStyling.processAllStyles(message);

        p.setMessage(message);
        return send(p);
        }
    
    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        // p.setMessage(isConnected ? "connected" : "disconnected");
        p.setMessage(String.format("%s the room %s", (isConnected ? "Joined" : "Left"), currentRoom.getName()));
        return send(p);
    }

    private boolean send(Payload payload) {
        try {
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            logger.info("Error sending message to client (most likely disconnected)");
            e.printStackTrace(); // or handle the exception appropriately
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            logger.info("Message was attempted to be sent before outbound stream was opened: " + payload);
            ne.printStackTrace(); // or handle the exception appropriately
            return false; // false since the client might not have been ready
        }
    }

    // end send methods
    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would
                                                                     // likely mean a disconnect)
            ) {

                logger.info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            e.printStackTrace();
            logger.info("Client disconnected");
        } finally {
            isRunning = false;
            logger.info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }
/*Nm874
 * 12/7/23
 */
    void processPayload(Payload p) {
        // Handling different payload types
        switch (p.getPayloadType()) {
            case WHISPER:
            processWhisperCommand(p.getMessage(), p.getClientId());
            break;
            case MUTE:
            muteClient(p.getClientId());
            break;
            case UNMUTE:
            unmuteClient(p.getClientId());
            break;
            case ROLL:
                // Extract command and clientId from Payload and process
                processRollCommand(p.getMessage(), p.getClientId());
                break;
            case FLIP:
                processFlipCommand(p.getClientId());
                break;
            case CONNECT:
                setClientName(p.getClientName());
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
            case MESSAGE:
                // Add logging before the logic that handles messages
                if (p.getMessage().startsWith("/")) {
                    // Process as command
                    processCommand(p.getMessage(), p.getClientId());
                } else {
                    // Process as regular message
                    if (currentRoom != null) {
                        currentRoom.sendMessage(this, p.getMessage());
                    } else {
                        logger.log(Level.INFO, "Migrating to lobby on message with null room");
                        Room.joinRoom(Constants.LOBBY, this);
                    }
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            case READY:
                // Additional case logic here
                break;
            default:
                break;
        }
    }
    /*Nm874
 * 12/7/23
 */
private void processCommand(String command, long clientId) {
        if (command.startsWith("@")) {
            processWhisperCommand(command, clientId);
        } else if (command.toLowerCase().startsWith("/mute ")) {
            updateMuteList(command, clientId, true);
        } else if (command.toLowerCase().startsWith("/unmute ")) {
            updateMuteList(command, clientId, false);
        }
    if (command.equalsIgnoreCase("/flip")) {
        processFlipCommand(clientId);
    }
    else if (command.startsWith("/roll")) {
        processRollCommand(command, clientId);}
}
private void processWhisperCommand(String message, long clientId) {
    // Assuming the message format is "/whisper username message"
    String[] parts = message.split(" ", 3);
    if (parts.length >= 3 && parts[0].equalsIgnoreCase("/whisper")) {
        String targetUsername = parts[1];
        String whisperMessage = parts[2];
        Long recipientClientId = findClientIdByUsername(targetUsername);
        if (recipientClientId != null) {
            sendMessage(recipientClientId, "[Whisper from " + getClientNameById(clientId) + "]: " + whisperMessage);
            sendMessage(clientId, "[You whisper to " + targetUsername + "]: " + whisperMessage);
        } else {
            sendMessage(clientId, "User " + targetUsername + " not found.");
        }
    }
}
 /*Nm874
 * 12/12/23
 */
private synchronized void updateMuteList(String command, long clientId, boolean mute) {
    String[] parts = command.split(" ", 2);
    if (parts.length == 2) {
        String targetUsername = parts[1].trim();
        Long targetClientId = findClientIdByUsername(targetUsername);
        ServerThread targetClientThread = Server.INSTANCE.findClientThreadById(targetClientId);

        if (targetClientId != null) {
            if (mute) {
                mutedClients.add(targetClientId);
                saveMuteList(); // Save the updated mute list
                // Inform the client that the mute operation was successful
                sendMessage(clientId, "You have muted " + targetUsername + ".");
                targetClientThread.sendMessage(targetClientId, "You have been muted by " + getClientNameById(clientId) + ".");
            } else {
                mutedClients.remove(targetClientId);
                saveMuteList(); // Save the updated mute list
                // Inform the client that the unmute operation was successful
                sendMessage(clientId, "You have unmuted " + targetUsername + ".");
                targetClientThread.sendMessage(targetClientId, "You have been unmuted by " + getClientNameById(clientId) + ".");
            }
        } else {
            // Inform the client that the target user was not found
            sendMessage(clientId, "User " + targetUsername + " not found.");
        }
    } else {
        // Inform the client that the command format is invalid
        sendMessage(clientId, "Invalid command format.");
    }
}


private Long findClientIdByUsername(String username) {
    // Iterate through all clients to find the matching username
    for (ServerThread client : getCurrentRoom().getClients()) {
        if (client.getClientName().equalsIgnoreCase(username)) {
            return client.getClientId();
        }
    }
    return null;
}
protected String getClientNameById(long id) {
    // Check if the user list has the client ID.
    if (clientIdToUsername.containsKey(id)) {
        // If it does, return the client's name.
        return clientIdToUsername.get(id);
    }
    // If the ID is the default, it means the server is the sender.
    if (id == Constants.DEFAULT_CLIENT_ID) {
        return "[Server]";
    }
    // If the client ID was not found, return an indicator such as "Unknown User."
    return "[Unknown User]";
}


private void processRollCommand(String command, long clientId) {
    String[] parts = command.split(" ");
    if (parts.length > 1) {
        // Assuming the format is /roll 2d6 or /roll 10
        String rollParams = parts[1];
        sendMessage(clientId, "Roll result: " + performRoll(rollParams));
    } else {
        // Handle error or default case
        sendMessage(clientId, "Invalid roll command format.");
    }
}
 /*Nm874
 * 12/7/23
 */
private void processFlipCommand(long clientId) {
    Random random = new Random();
    String result = random.nextBoolean() ? "Heads" : "Tails";
    String response = "Flip result: " + result;
    sendMessage(clientId, response);
}

private String performRoll(String rollParams) {
    Random random = new Random();
    int total = 0;
    if (rollParams.matches("\\d+")) {
        // Single number roll, e.g., /roll 20
        int max = Integer.parseInt(rollParams);
        total = random.nextInt(max) + 1;
    } else if (rollParams.matches("\\d+d\\d+")) {
        // Dice roll, e.g., /roll 2d6
        String[] diceParts = rollParams.split("d");
        int diceCount = Integer.parseInt(diceParts[0]);
        int diceSides = Integer.parseInt(diceParts[1]);
        for (int i = 0; i < diceCount; i++) {
            total += random.nextInt(diceSides) + 1;
        }
    } else {
        // Invalid format
        return "Invalid roll command.";
    }
    return String.valueOf(total);
}

private void cleanup() {
    logger.info("Thread cleanup() start");
    try {
        client.close();
    } catch (IOException e) {
        logger.info("Client already closed");
    }
    logger.info("Thread cleanup() complete");
}}