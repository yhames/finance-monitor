package project.finance.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import project.finance.category.ExpenditureCategory;
import project.finance.category.PaymentType;
import project.finance.domain.Expenditure;
import project.finance.request.ExpenditureUpdate;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExpenditureRepositoryJdbc implements ExpenditureRepository {

    private final JdbcTemplate template;

    public ExpenditureRepositoryJdbc(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Expenditure expenditure) {
        String sql = "insert into expenditure(category, title, detail, value, date, payment) values (?,?,?,?,?,?)";
        // 자동 증가 키
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, expenditure.getCategory().toString());
            ps.setString(2, expenditure.getTitle());
            ps.setString(3, expenditure.getDetail());
            ps.setLong(4, expenditure.getValue());
            ps.setDate(5, Date.valueOf(expenditure.getDate()));
            ps.setString(6, expenditure.getPayment().toString());
            return ps;
        }, keyHolder);
    }

    @Override
    public void update(Long id, ExpenditureUpdate updateParam) {
        String sql = "update expenditure set category=?, title=?, detail=?, value=?, date=?, payment=? where id=?";
        template.update(sql,
                updateParam.getCategory(),
                updateParam.getTitle(),
                updateParam.getDetail(),
                updateParam.getValue(),
                updateParam.getDate(),
                updateParam.getPayment(),
                id);
    }

    @Override
    public List<Expenditure> findAll() {
        String sql = "select id, category, title, detail, value, date, payment from expenditure";
        return template.query(sql, expenditureRowMapper());
    }

    @Override
    public Optional<Expenditure> findById(Long id) {
        String sql = "select category, title, detail, value, date, payment from expenditure where id=?";
        try {
            Expenditure expenditure = template.queryForObject(sql, expenditureRowMapper(), id);
            return Optional.of(expenditure);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Expenditure> expenditureRowMapper() {
        return ((rs, num) -> {
            Expenditure expenditure = Expenditure.builder()
                    .category(ExpenditureCategory.valueOf(rs.getString("category")))
                    .title(rs.getString("title"))
                    .detail(rs.getString("detail"))
                    .value(rs.getLong("value"))
                    .date(rs.getDate("date").toLocalDate())
                    .payment(PaymentType.valueOf(rs.getString("payment")))
                    .build();
            return expenditure;
        });
    }
}
