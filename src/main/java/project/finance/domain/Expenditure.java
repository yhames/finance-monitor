package project.finance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import project.finance.category.ExpenditureCategory;
import project.finance.category.PaymentType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class Expenditure {
    @Id
    @GeneratedValue
    @Column(name = "EXPENDITURE_ID")
    private Long id;

    @Column(name = "EXPENDITURE_CATEGORY")
    private ExpenditureCategory category;

    @Column(name = "EXPENDITURE_TITLE")
    private String title;

    @Column(name = "EXPENDITURE_DETAIL")
    private String detail;

    @Column(name = "EXPENDITURE_VALUE")
    private Long value;

    @Column(name = "EXPENDITURE_DATE")
    private LocalDate date;

    @Column(name = "EXPENDITURE_PAYMENT")
    private PaymentType payment;
}
