package project.finance.repository;

import org.springframework.stereotype.Repository;
import project.finance.domain.Expenditure;
import project.finance.request.ExpenditureUpdate;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenditureRepository {

    void save(Expenditure expenditure);

    List<Expenditure> findAll();

    Optional<Expenditure> findById(Long id);

    void update(Long id, ExpenditureUpdate updateParam);
}
