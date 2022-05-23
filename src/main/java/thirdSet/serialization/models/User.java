package thirdSet.serialization.models;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;
@Builder
@ToString
public class User implements Serializable {
    private final boolean isActive;
    private final boolean isAdmin;
    private final boolean isModerator;
    private final boolean isVIP;
    private final boolean isMuted;
    private final boolean isBanned;
    private static final long serialVersionUID = -4118549117442895705L;
}
