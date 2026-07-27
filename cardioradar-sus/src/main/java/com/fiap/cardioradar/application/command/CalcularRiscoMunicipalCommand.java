package com.fiap.cardioradar.application.command;

import com.fiap.cardioradar.domain.PressaoMedicamento;

import java.time.YearMonth;
import java.util.UUID;

public record CalcularRiscoMunicipalCommand(
        UUID municipioId,
        YearMonth competencia,
        PressaoMedicamento pressaoMedicamento

) {
}
