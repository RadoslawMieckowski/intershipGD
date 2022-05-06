package thirdSet.serialization.models;

import java.io.Serializable;

public class User implements Serializable {
    private final boolean isActive;
    private final boolean isAdmin;
    private final boolean isModerator;
    private final boolean isVIP; private final boolean isMuted; private final boolean isBanned;

    public User(boolean isActive, boolean isAdmin, boolean isModerator, boolean isVIP, boolean isMuted, boolean isBanned) {
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.isVIP = isVIP;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
    }
}
