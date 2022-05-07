package thirdSet.serialization.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.BitSet;

public class UserExternalizable implements Externalizable {
    private boolean isActive;
    private boolean isAdmin;
    private boolean isModerator;
    private boolean isVIP;
    private boolean isMuted;
    private boolean isBanned;
    private Website website;
    private static final long serialVersionUID = 5602506335384173830L;

    public UserExternalizable(boolean isActive, boolean isAdmin, boolean isModerator,
                              boolean isVIP, boolean isMuted, boolean isBanned,
                              Website website) {
        this.isActive = isActive;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.isVIP = isVIP;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
        this.website = website;
    }

    public UserExternalizable(boolean isActive, boolean isAdmin, boolean isModerator,
                              boolean isVIP, boolean isMuted, boolean isBanned) {
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
        if (website != null) out.writeObject(website);
        //System.out.println("serialized: " + bitSet);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        BitSet bitSet = BitSet.valueOf(new byte[]{in.readByte()});
       // System.out.println("bitSet deserialized " + bitSet);
       // System.out.println(Arrays.toString(bitSet.toByteArray()));
        //System.out.println(bitSet.size());
        isActive = bitSet.get(0);
        System.out.println("isActive: " + isActive);
        isAdmin = bitSet.get(1);
        isModerator = bitSet.get(2);
        isVIP = bitSet.get(3);
        isMuted = bitSet.get(4);
        isBanned = bitSet.get(5);
        try{
            website = (Website) in.readObject();
        } catch (Exception e) {
            System.out.println("serialized object doesn't contain not null website field");
        }
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "UserExternalizable{" +
                "isActive=" + isActive +
                ", isAdmin=" + isAdmin +
                ", isModerator=" + isModerator +
                ", isVIP=" + isVIP +
                ", isMuted=" + isMuted +
                ", isBanned=" + isBanned +
                ", website=" + (website != null ? website.getName() : "null") +
                '}';
    }
}
