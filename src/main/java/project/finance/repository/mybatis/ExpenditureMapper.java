package project.finance.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import project.finance.domain.Expenditure;
import project.finance.request.ExpenditureUpdate;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExpenditureMapper {

    void save(Expenditure expenditure);

    void update(@Param("id") Long id, @Param("updateParam") ExpenditureUpdate updateParam);

    Optional<Expenditure> findById(Long id);

    List<Expenditure> findAll();
}
