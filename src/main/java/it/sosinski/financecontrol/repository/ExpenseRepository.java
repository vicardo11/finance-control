package it.sosinski.financecontrol.repository;

import it.sosinski.financecontrol.repository.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByAccount_AccountId(Long id);
}
