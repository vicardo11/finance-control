package it.sosinski.financecontrol.repository.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

import static it.sosinski.financecontrol.repository.entity.EntityConstants.TABLE_NAME_ACCOUNTS;

@Getter
@Setter
@ToString
@Entity
@Table(name = TABLE_NAME_ACCOUNTS)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "accounts")
    @ToString.Exclude
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId) && email.equals(account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, email);
    }
}
