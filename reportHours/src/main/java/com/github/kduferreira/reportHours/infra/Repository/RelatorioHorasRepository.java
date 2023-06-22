package com.github.kduferreira.reportHours.infra.Repository;

import com.github.kduferreira.reportHours.Domain.RelatorioHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface RelatorioHorasRepository  extends JpaRepository <RelatorioHoras, Long> {

}
