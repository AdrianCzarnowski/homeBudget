package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.mapper.ReportMapper;
import projects.adrian.homebudget.model.dto.ReportDto;
import projects.adrian.homebudget.model.entity.ReportEntity;
import projects.adrian.homebudget.repository.ReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service

public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public List<ReportDto> getAllReports() {
        return reportRepository.findAll().stream().map(reportMapper::toDto).toList();
    }

    public ReportDto findById(UUID uuid) {
        Optional<ReportEntity> optionalReportEntity = reportRepository.findById(uuid);
        return optionalReportEntity.map(reportMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find report by given id " + uuid));
    }

    public ReportDto saveReport(ReportDto reportDto) {
        ReportEntity reportEntity = reportMapper.toEntity(reportDto);
        ReportEntity savedEntity = reportRepository.save(reportEntity);
        return reportMapper.toDto(savedEntity);
    }

    public void deleteReport(UUID reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new RuntimeException("Transaction with ID " + reportId + " does not exist");
        }
        reportRepository.deleteById(reportId);
    }

}
