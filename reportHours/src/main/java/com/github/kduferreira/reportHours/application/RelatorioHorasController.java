package com.github.kduferreira.reportHours.application;
import com.github.kduferreira.reportHours.Domain.DTO.RelatorioHorasDTO;
import com.github.kduferreira.reportHours.Domain.RelatorioHoras;
import com.github.kduferreira.reportHours.Service.RelatorioHorasService;
import com.github.kduferreira.reportHours.infra.Repository.RelatorioHorasRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("relatorio")
public class RelatorioHorasController {

    private final RelatorioHorasRepository repository;
    private final RelatorioHorasService service;

    @GetMapping
    public List<RelatorioHoras> search(){
        return repository.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<RelatorioHorasDTO> save (@RequestBody @Valid RelatorioHorasDTO relatorioHorasDTO) {
        RelatorioHorasDTO relatorioSalvo = service.save(relatorioHorasDTO);
        return ResponseEntity.ok(relatorioSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioHorasDTO> search(@PathVariable Long id) {
        RelatorioHorasDTO relatorioEncontrado = service.search(id);
        if (relatorioEncontrado != null) {
            return ResponseEntity.ok(relatorioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<RelatorioHorasDTO> atualizar (@Valid   @PathVariable Long id, @RequestBody RelatorioHorasDTO relatorioHorasDTO) {

        if(!repository.existsById(id)) {
            return  ResponseEntity.notFound().build();
        }

    relatorioHorasDTO.setId(id);
        relatorioHorasDTO = service.save(relatorioHorasDTO);
        return ResponseEntity.ok(relatorioHorasDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        if(!repository.existsById(id)) {
            return  ResponseEntity.notFound().build();
        }
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
