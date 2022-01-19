package it.sosinski.financecontrol.repository;

import it.sosinski.financecontrol.repository.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Integer> {

}
