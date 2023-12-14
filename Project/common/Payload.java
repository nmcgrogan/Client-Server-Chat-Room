package Project.common;

import java.io.Serializable;
import java.util.Map;

public class Payload implements Serializable {
    // read https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;// change this if the class changes

    /**
     * Determines how to process the data on the receiver's side
     * [nm874] 11/13/23
     */
    private PayloadType payloadType;
    private int diceCount;
    private int diceSides;
    private Map<Long, UserStatus> userStatuses;
    public Map<Long, UserStatus> getUserStatuses() {
        return userStatuses;
    }
    public void setUserStatuses(Map<Long, UserStatus> userStatuses) {
        this.userStatuses = userStatuses;
    }

    // Inner class to hold user status
    public static class UserStatus implements Serializable {
        private static final long serialVersionUID = 1L;
        private String username;
        private boolean isMuted;

        public UserStatus(String username, boolean isMuted) {
            this.username = username;
            this.isMuted = isMuted;
        }

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isMuted() {
            return isMuted;
        }

        public void setMuted(boolean isMuted) {
            this.isMuted = isMuted;
        }
    }
    // Getters and setters for diceCount
    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    // Getters and setters for diceSides
    public int getDiceSides() {
        return diceSides;
    }

    public void setDiceSides(int diceSides) {
        this.diceSides = diceSides;
    }

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * Who the payload is from
     */
    private String clientName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private long clientId;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Generic text based message
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Type[%s],ClientId[%s,] ClientName[%s], Message[%s]", getPayloadType().toString(),
                getClientId(), getClientName(),
                getMessage());
    }
}