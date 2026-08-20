package com.maria.game_store.model.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.maria.game_store.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Client.class, name = "client"),
        @JsonSubTypes.Type(value = Admin.class, name = "admin")
})
@Entity
@Table(name = "tb_users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "nickname", length = 40, unique = true)
    private String nickname;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "email", length = 200, unique = true, nullable = false)
    private String email;

    @Column(name = "role")
    private Role role;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
