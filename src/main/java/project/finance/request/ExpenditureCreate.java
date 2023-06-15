package project.finance.request;

import lombok.Getter;
import lombok.Setter;
import project.finance.category.ExpenditureCategory;
import project.finance.category.PaymentType;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenditureCreate {
    private ExpenditureCategory category;
    private String title;
    private String detail;
    private Long value;
    private LocalDate date;
    private PaymentType payment;
}
