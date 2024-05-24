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

    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    public ReportDto findById(UUID uuid) {
        Optional<ReportEntity> optionalReportEntity = reportRepository.findById(uuid);
        return optionalReportEntity.map(reportMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find user by given id " + uuid));
    }
}
