package thirdSet.serialization.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserExternalizable implements Externalizable {
    private final boolean isActive;
    private final boolean isAdmin;
    private final boolean isModerator;
    private final boolean isVIP;
    private final boolean isMuted;
    private final boolean isBanned;
    private static final long serialVersionUID = 5602506335384173830L;

    public UserExternalizable(boolean isActive, boolean isAdmin, boolean isModerator, boolean isVIP, boolean isMuted, boolean isBanned) {
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.isVIP = isVIP;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
