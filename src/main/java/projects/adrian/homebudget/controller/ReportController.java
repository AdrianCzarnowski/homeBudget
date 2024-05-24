package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.adrian.homebudget.model.dto.ReportDto;
import projects.adrian.homebudget.model.entity.ReportEntity;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import projects.adrian.homebudget.service.ReportService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ReportController.API_URL)
public class ReportController {

    public static final String API_URL = "/api/report";

    private final ReportService reportService;

    @GetMapping
    public List<ReportEntity> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ReportDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(reportService.findById(uuid));
    }
}
