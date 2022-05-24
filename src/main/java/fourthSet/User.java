package fourthSet;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private int id;
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}

