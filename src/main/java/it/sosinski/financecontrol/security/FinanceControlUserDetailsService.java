package it.sosinski.financecontrol.security;

import it.sosinski.financecontrol.repository.AccountRepository;
import it.sosinski.financecontrol.repository.entity.Account;
import it.sosinski.financecontrol.repository.entity.Privilege;
import it.sosinski.financecontrol.repository.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FinanceControlUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public FinanceControlUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        Account account = accountOptional.orElseThrow(
                () -> new UsernameNotFoundException("Account not found with email: " + email)
        );

        return new User(
                account.getEmail(),
                account.getPassword(),
                getAuthorities(account.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final Set<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(final Set<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
