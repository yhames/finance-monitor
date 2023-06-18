package project.finance.repository;

import lombok.extern.slf4j.Slf4j;
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

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j  // 로그 출력 라이브러리 추가
@Transactional  // 기본 : 로직 성공시 커밋 실행, 테스트 : 로직 성공하면 롤백, 수동 커밋 가능
@SpringBootTest // @SpringBootApplication 설정을 찾아서 설정파일로 등록 -> 테스트에서 원래 코드 사용가능
class ExpenditureRepositoryJdbcTest {

    @Autowired
    ExpenditureRepository expenditureRepository;

    @Test
    @DisplayName("저장기능 테스트")
    void save() {
        LocalDate date = LocalDate.of(2023, 6, 14);
        Expenditure expenditure = Expenditure.builder()
                .category(ExpenditureCategory.FOOD)
                .title("칼국수")
                .detail("황새골칼국수")
                .expenditureValue(10000L)
                .expenditureDate(date)
                .payment(PaymentType.CREDIT)
                .build();

        expenditureRepository.save(expenditure);    // 저장

        List<Expenditure> result = expenditureRepository.findAll(); // 조회

        log.debug("expenditure = {}", expenditure);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getCategory()).isEqualTo(ExpenditureCategory.FOOD);
        assertThat(result.get(0).getTitle()).isEqualTo("칼국수");
        assertThat(result.get(0).getDetail()).isEqualTo("황새골칼국수");
        assertThat(result.get(0).getExpenditureValue()).isEqualTo(10000L);
        assertThat(result.get(0).getExpenditureDate()).isEqualTo(date);
        assertThat(result.get(0).getPayment()).isEqualTo(PaymentType.CREDIT);
    }
}