package projects.adrian.homebudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projects.adrian.homebudget.mapper.ReportMapper;
import projects.adrian.homebudget.model.dto.ReportDto;
import projects.adrian.homebudget.model.entity.ReportEntity;
import projects.adrian.homebudget.repository.ReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class ReportServiceTest {
    private ReportService reportService;
    private ReportMapper reportMapper;
    private ReportRepository reportRepository;

    @BeforeEach
    void setUp() {
        reportRepository = Mockito.mock(ReportRepository.class);
        reportMapper = Mockito.mock(ReportMapper.class);
        reportService = new ReportService(reportRepository, reportMapper);
    }

    @Test
    void getAllReports_AllReportsFound_passed() {
        //given
        ReportEntity reportEntity = Mockito.mock(ReportEntity.class);
        ReportDto reportDto = Mockito.mock(ReportDto.class);

        Mockito.when(reportRepository.findAll()).thenReturn(List.of(reportEntity));
        Mockito.when(reportMapper.toDto(any())).thenReturn(reportDto);

        //when
        List<ReportDto> result = reportService.getAllReports();

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reportDto, result.get(0));
    }

    @Test
    void findById_ReportFoundById_passed() {
        //given
        UUID reportId = UUID.randomUUID();
        ReportEntity reportEntity = Mockito.mock(ReportEntity.class);
        ReportDto reportDto = Mockito.mock(ReportDto.class);

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.of(reportEntity));
        Mockito.when(reportMapper.toDto(any())).thenReturn(reportDto);

        //when
        ReportDto result = reportService.findById(reportId);

        //then
        assertNotNull(result);
        assertEquals(reportDto, result);
    }

    @Test
    void findById_ReportNotFoundById_throwException() {
        //given
        UUID reportId = UUID.randomUUID();

        Mockito.when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        //when + then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reportService.findById(reportId));
        assertEquals("Can not find report by given id " + reportId, exception.getMessage());
    }

    @Test
    void saveReport_ReportSaved_passed() {
        //given
        ReportEntity reportEntity = Mockito.mock(ReportEntity.class);
        ReportDto reportDto = Mockito.mock(ReportDto.class);

        Mockito.when(reportMapper.toEntity(any())).thenReturn(reportEntity);
        Mockito.when(reportRepository.save(any())).thenReturn(reportEntity);
        Mockito.when(reportMapper.toDto(any())).thenReturn(reportDto);

        //when
        ReportDto result = reportService.saveReport(reportDto);

        //then
        assertNotNull(result);
        assertEquals(reportDto, result);
    }

    @Test
    void deleteReport_ReportDeleted_passed() {
        //given
        UUID reportId = UUID.randomUUID();

        //when
        reportService.deleteReport(reportId);

        //then
        Mockito.verify(reportRepository).deleteById(reportId);
    }

}
