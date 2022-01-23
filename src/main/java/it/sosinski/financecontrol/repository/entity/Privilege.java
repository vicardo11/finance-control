package it.sosinski.financecontrol.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.TABLE_NAME_PRIVILEGES;

@Getter
@Setter
@ToString
@Entity
@Table(name = TABLE_NAME_PRIVILEGES)
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilegeId;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "privileges")
    @ToString.Exclude
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return privilegeId.equals(privilege.privilegeId) && name.equals(privilege.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilegeId, name);
    }
}
