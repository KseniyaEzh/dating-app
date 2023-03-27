package ru.pnzgu.fvt.moipvm.vi19.br2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.PersonInfo;

import java.util.Optional;

@Repository
public interface PeopleInfoRepository extends JpaRepository<PersonInfo, Integer> {
//    Optional<PersonInfo> findByUserId(int user_id);
}
