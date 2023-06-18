package project.finance.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.finance.domain.Expenditure;
import project.finance.repository.mybatis.ExpenditureMapper;
import project.finance.request.ExpenditureUpdate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExpenditureRepositoryMyBatis implements ExpenditureRepository {

    private final ExpenditureMapper expenditureMapper;

    @Override
    public void save(Expenditure expenditure) {
        expenditureMapper.save(expenditure);
    }

    @Override
    public void update(Long id, ExpenditureUpdate updateParam) {
        expenditureMapper.update(id, updateParam);
    }

    @Override
    public Optional<Expenditure> findById(Long id) {
        return expenditureMapper.findById(id);
    }

    @Override
    public List<Expenditure> findAll() {
        return expenditureMapper.findAll();
    }
}