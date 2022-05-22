package thirdSet.serialization.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.BitSet;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserExternalizable implements Externalizable {
    private boolean isActive;
    private boolean isAdmin;
    private boolean isModerator;
    private boolean isVIP;
    private boolean isMuted;
    private boolean isBanned;
    private Website website;
    private static final long serialVersionUID = 5602506335384173830L;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        BitSet bitSet = new BitSet(6);
        bitSet.set(0, isActive);
        bitSet.set(1, isAdmin);
        bitSet.set(2, isModerator);
        bitSet.set(3, isVIP);
        bitSet.set(4, isMuted);
        bitSet.set(5, isBanned);
        out.write(bitSet.toByteArray());
        out.writeObject(website);
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        BitSet bitSet = BitSet.valueOf(new byte[]{in.readByte()});
        isActive = bitSet.get(0);
        isAdmin = bitSet.get(1);
        isModerator = bitSet.get(2);
        isVIP = bitSet.get(3);
        isMuted = bitSet.get(4);
        isBanned = bitSet.get(5);
        try{
            website = (Website) in.readObject();
        } catch (Exception e) {
            System.err.println("unexpected null value of website field!");
            throw e;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserExternalizable that = (UserExternalizable) o;
        return isActive == that.isActive &&
                isAdmin == that.isAdmin &&
                isModerator == that.isModerator &&
                isVIP == that.isVIP &&
                isMuted == that.isMuted &&
                isBanned == that.isBanned &&
                Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive, isAdmin, isModerator, isVIP, isMuted, isBanned, website);
    }
}
