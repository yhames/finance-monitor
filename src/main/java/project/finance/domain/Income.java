package project.finance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Income {

    @Id
    @GeneratedValue
    @Column(name = "INCOME_ID")
    private Long id;

    @Column(name = "INCOME_TITLE")
    private String title;

    @Column(name = "INCOME_DETAIL")
    private String detail;

    @Column(name = "INCOME_VALUE")
    private Long value;

    @Column(name = "INCOME_DATE")
    private LocalDate date;
}
