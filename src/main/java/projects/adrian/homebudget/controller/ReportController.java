package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.ReportDto;
import projects.adrian.homebudget.service.ReportService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.REPORT_API_URL)
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReports(@RequestBody ReportDto reportDto) {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<ReportDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(reportService.findById(uuid));
    }


    @PostMapping
    public ResponseEntity<ReportDto> createReport(@RequestBody ReportDto reportDto) {
        return new ResponseEntity<>(reportService.saveReport(reportDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<ReportDto> updateReport(@PathVariable UUID uuid, @RequestBody ReportDto reportDto) {
        return new ResponseEntity<>(reportService.saveReport(reportDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<ReportDto> deleteReport(@PathVariable UUID uuid) {
        reportService.deleteReport(uuid);
        return ResponseEntity.noContent().build();
    }
}
