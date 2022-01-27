package it.sosinski.financecontrol.service.mapper;

import it.sosinski.financecontrol.repository.entity.Account;
import it.sosinski.financecontrol.web.dto.NewAccountDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountMapper.class);

    private static final ModelMapper mapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;

    public AccountMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Account fromNewDtoToEntity(NewAccountDto newAccountDto) {
        LOGGER.info("fromNewDtoToEntity(" + newAccountDto + ")");

        Account account = mapper.map(newAccountDto, Account.class);
        account.setPassword(passwordEncoder.encode(newAccountDto.getPassword()));

        LOGGER.info("fromNewDtoToEntity(...) = " + account);
        return account;
    }
}
