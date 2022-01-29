package it.sosinski.financecontrol.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = TABLE_NAME_ROLES)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = TABLE_NAME_ACCOUNTS_ROLES,
            joinColumns = {@JoinColumn(name = COLUMN_NAME_ROLE_ID)},
            inverseJoinColumns = {@JoinColumn(name = COLUMN_NAME_ACCOUNT_ID)})
    @ToString.Exclude
    private Set<Account> accounts = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = TABLE_NAME_ROLES_PRIVILEGES,
            joinColumns = {@JoinColumn(name = COLUMN_NAME_ROLE_ID)},
            inverseJoinColumns = {@JoinColumn(name = COLUMN_NAME_PRIVILEGE_ID)})
    @ToString.Exclude
    private Set<Privilege> privileges = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId.equals(role.roleId) && name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, name);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
