package com.github.kduferreira.reportHours.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kduferreira.reportHours.Domain.RelatorioHoras;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RelatorioHorasDTO {


    private Long id;
    private String projeto;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;

    private BigDecimal valorHora;


    private String tempoTrabalhado;

    private BigDecimal custo;



    public RelatorioHorasDTO(RelatorioHoras relatorioHoras, Duration tempoTrabalhado, BigDecimal custo) {
        this.id = relatorioHoras.getId();
        this.projeto = relatorioHoras.getProjeto();
        this.horarioInicio = relatorioHoras.getHorarioInicio();
        this.horarioFim = relatorioHoras.getHorarioFim();
        this.valorHora = relatorioHoras.getValorHora();
        this.tempoTrabalhado = formatarTempoTrabalhado(tempoTrabalhado);
        this.custo = custo;
    }


    private  String formatarTempoTrabalhado(Duration tempoTrabalhado) {
        long horas = tempoTrabalhado.toHours();
        long minutos = tempoTrabalhado.toMinutesPart();
        long segundos = tempoTrabalhado.toSecondsPart();
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
}
