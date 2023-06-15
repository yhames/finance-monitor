package project.finance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.finance.request.ExpenditureCreate;
import project.finance.service.ExpenditureService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expenditure")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    @PostMapping("/save")
    public String save(@ModelAttribute ExpenditureCreate item) {
        expenditureService.save(item);
        return "redirect:/";
    }

    @GetMapping
    public void findAll() {

    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id) {
        return "/expenditure/detail";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id) {
        return "/expenditure/update";
    }

    @PatchMapping("/{id}/update")
    public void update(@PathVariable Long id) {

    }

    @GetMapping("/{id}/delete")
    public String deleteForm(@PathVariable Long id) {
        return "/expenditure/delete";
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {

    }
}
