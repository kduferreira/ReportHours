package com.github.kduferreira.reportHours.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

    @Entity
    @Data
    public class RelatorioHoras {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String projeto;
        private LocalTime horarioInicio;
        private LocalTime horarioFim;
        @NotBlank
        private BigDecimal valorHora;



    }
