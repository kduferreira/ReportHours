package com.github.kduferreira.reportHours.Service;

import com.github.kduferreira.reportHours.Domain.DTO.RelatorioHorasDTO;
import com.github.kduferreira.reportHours.Domain.RelatorioHoras;
import com.github.kduferreira.reportHours.exception.NegocioException;
import com.github.kduferreira.reportHours.infra.Repository.RelatorioHorasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RelatorioHorasService {
    private final RelatorioHorasRepository repository;

    public  RelatorioHorasDTO search (Long id) {
        Optional<RelatorioHoras> optionalRelatorioHoras = repository.findById(id);
        if(optionalRelatorioHoras.isPresent()) {
            RelatorioHoras relatorio = optionalRelatorioHoras.get();
            Duration tempoTrabalho = calcularTempoTrabalhado(relatorio);
            BigDecimal custo = calcularCusto(relatorio);
            return new RelatorioHorasDTO(relatorio, tempoTrabalho, custo);

        }
        return null;
    }
    @Transactional
    public RelatorioHorasDTO save (RelatorioHorasDTO relatorioHorasDTO) {
        RelatorioHoras relatorio = new RelatorioHoras();
        relatorio.setProjeto(relatorioHorasDTO.getProjeto());
        relatorio.setHorarioInicio(relatorioHorasDTO.getHorarioInicio());
        relatorio.setHorarioFim(relatorioHorasDTO.getHorarioFim());
        relatorio.setValorHora(relatorioHorasDTO.getValorHora());

        relatorio = repository.save(relatorio);

        Duration tempoTrabalho = calcularTempoTrabalhado(relatorio);
        BigDecimal custo = calcularCusto(relatorio);
        return new RelatorioHorasDTO(relatorio, tempoTrabalho, custo);
    }
public Duration calcularTempoTrabalhado(RelatorioHoras relatorio) {
    LocalTime horarioInicio = relatorio.getHorarioInicio();
    LocalTime horarioFim = relatorio.getHorarioFim();

    // Realize os cálculos para obter a duração do tempo trabalhado
    Duration tempoTrabalhado = Duration.between(horarioInicio,horarioFim);
    return tempoTrabalhado;
}
    public BigDecimal calcularCusto (RelatorioHoras relatorioHoras) {
        Duration tempoTrabalhado = calcularTempoTrabalhado(relatorioHoras);
        BigDecimal valorHora = relatorioHoras.getValorHora();
        BigDecimal horasTrabalhadas = BigDecimal.valueOf(tempoTrabalhado.toHours());
        return valorHora.multiply(horasTrabalhadas);
    }
    @Transactional
    public void delete (Long id) {
        repository.deleteById(id);
    }
}
