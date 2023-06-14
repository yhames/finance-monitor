package project.finance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.finance.domain.Expenditure;
import project.finance.repository.ExpenditureRepository;
import project.finance.request.ExpenditureRequest;

@Service
@RequiredArgsConstructor
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    public void save(ExpenditureRequest item) {
        Expenditure expenditure = Expenditure.builder()
                .category(item.getCategory())
                .title(item.getTitle())
                .detail(item.getDetail())
                .value(item.getValue())
                .date(item.getDate())
                .payment(item.getPayment())
                .build();
        expenditureRepository.save(expenditure);
    }
}
