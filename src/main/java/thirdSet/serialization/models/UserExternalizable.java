package thirdSet.serialization.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.BitSet;

public class UserExternalizable implements Externalizable {
    private boolean isActive;
    private boolean isAdmin;
    private boolean isModerator;
    private boolean isVIP;
    private boolean isMuted;
    private boolean isBanned;
    private static final long serialVersionUID = 5602506335384173830L;

    public UserExternalizable(boolean isActive, boolean isAdmin, boolean isModerator, boolean isVIP, boolean isMuted, boolean isBanned) {
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.isVIP = isVIP;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
    }

    public UserExternalizable() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        BitSet bitSet = new BitSet(6);
        if(isActive) bitSet.set(0);
        if (isAdmin) bitSet.set(1);
        if (isModerator) bitSet.set(2);
        if (isVIP) bitSet.set(3);
        if (isMuted) bitSet.set(4);
        if (isBanned) bitSet.set(5);
        out.write(bitSet.toByteArray());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        BitSet bitSet = BitSet.valueOf(new byte[]{in.readByte()});
        if(isActive) bitSet.get(0);
        if (isAdmin) bitSet.get(1);
        if (isModerator) bitSet.get(2);
        if (isVIP) bitSet.get(3);
        if (isMuted) bitSet.get(4);
        if (isBanned) bitSet.get(5);
    }
}
