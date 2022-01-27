package it.sosinski.financecontrol.service;

import it.sosinski.financecontrol.core.exception.AccountAlreadyExistsException;
import it.sosinski.financecontrol.core.exception.RoleNotFoundException;
import it.sosinski.financecontrol.repository.AccountRepository;
import it.sosinski.financecontrol.repository.RoleRepository;
import it.sosinski.financecontrol.repository.entity.Account;
import it.sosinski.financecontrol.repository.entity.Role;
import it.sosinski.financecontrol.service.mapper.AccountMapper;
import it.sosinski.financecontrol.web.dto.NewAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    public static final String ROLE_NAME_USER = "USER";
    private final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository,
                          AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.accountMapper = accountMapper;
    }

    public void register(NewAccountDto newAccountDto) throws RoleNotFoundException, AccountAlreadyExistsException {
        LOGGER.info("register(" + newAccountDto + ")");

        if (emailExists(newAccountDto.getEmail())) {
            throw new AccountAlreadyExistsException("Account already exists with email: " + newAccountDto.getEmail());
        }

        Account account = mapNewDtoToEntity(newAccountDto);
        Role role = getUserRole();
        account.addRole(role);
        saveAccount(account);

        LOGGER.info("register(...)");
    }

    private boolean emailExists(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

    private Account mapNewDtoToEntity(NewAccountDto newAccountDto) {
        return accountMapper.fromNewDtoToEntity(newAccountDto);
    }

    private Role getUserRole() throws RoleNotFoundException {
        Optional<Role> roleOptional = roleRepository.findByName(ROLE_NAME_USER);
        Role role = roleOptional.orElseThrow(
                () -> new RoleNotFoundException("Role not found for name: " + ROLE_NAME_USER));

        return role;
    }

    private void saveAccount(Account account) {
        accountRepository.save(account);
    }

}
