package project.finance.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import project.finance.domain.Expenditure;
import project.finance.request.ExpenditureUpdate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// TODO : NamedParameterJdbcTemplate, SimpleJdbcInsert 추가
@Slf4j
@Repository
public class ExpenditureRepositoryJdbc implements ExpenditureRepository {

    //    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public ExpenditureRepositoryJdbc(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("expenditure")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Expenditure expenditure) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(expenditure);
        jdbcInsert.executeAndReturnKey(param);
    }

    @Override
    public void update(Long id, ExpenditureUpdate updateParam) {
        String sql = "update expenditure" +
                " set category=:category, title=:title, detail=:detail, expenditure_value=:value, expenditure_date=:date, payment=:payment where id=:id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("category", updateParam.getCategory())
                .addValue("title", updateParam.getTitle())
                .addValue("detail", updateParam.getDetail())
                .addValue("value", updateParam.getValue())
                .addValue("payment", updateParam.getPayment())
                .addValue("id", id);    // 이 부분 때문에 MapSqlParameterSource 사용
        template.update(sql, param);
    }

    @Override
    public List<Expenditure> findAll() {
        String sql = "select id, category, title, detail, expenditure_value, expenditure_date, payment from expenditure";
        return template.query(sql, expenditureRowMapper());
    }

    @Override
    public Optional<Expenditure> findById(Long id) {
        String sql = "select category, title, detail, expenditure_value, expenditure_date, payment from expenditure where id=:id";
        Map<String, Object> param = Map.of("id", id);
        try {
            // 이름 지정 파라미터가 1개여서 가장 단순한 Map 사용
            // `param`은 2nd 매개변수로 전달
            Expenditure expenditure = template.queryForObject(sql, param, expenditureRowMapper());
            return Optional.of(expenditure);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Expenditure> expenditureRowMapper() {
        return BeanPropertyRowMapper.newInstance(Expenditure.class);
    }
}