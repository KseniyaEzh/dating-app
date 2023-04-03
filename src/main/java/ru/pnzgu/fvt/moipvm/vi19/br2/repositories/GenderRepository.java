package ru.pnzgu.fvt.moipvm.vi19.br2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
