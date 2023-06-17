package project.finance.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.finance.category.ExpenditureCategory;
import project.finance.category.PaymentType;
import project.finance.domain.Expenditure;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class ExpenditureRepositoryJdbcTest {

    @Autowired
    ExpenditureRepository expenditureRepository;

    @Test
    @DisplayName("save")
    void save() {

        LocalDate date = LocalDate.of(2023, 6, 14);
        Expenditure expenditure = Expenditure.builder()
                .category(ExpenditureCategory.FOOD)
                .title("test expenditure")
                .detail("test expenditure")
                .expenditureValue(10000L)
                .payment(PaymentType.CREDIT)
                .expenditureDate(date)
                .build();

        expenditureRepository.save(expenditure);

        List<Expenditure> result = expenditureRepository.findAll();
        log.debug("expenditure = {}", expenditure);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getCategory()).isEqualTo(ExpenditureCategory.FOOD);
        assertThat(result.get(0).getTitle()).isEqualTo("test expenditure");
        assertThat(result.get(0).getTitle()).isEqualTo("test expenditure");
        assertThat(result.get(0).getDetail()).isEqualTo("test expenditure");
        assertThat(result.get(0).getExpenditureValue()).isEqualTo(10000L);
        assertThat(result.get(0).getExpenditureDate()).isEqualTo(date);
        assertThat(result.get(0).getPayment()).isEqualTo(PaymentType.CREDIT);
    }
}