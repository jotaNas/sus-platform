package com.fiap.cardioradar.application.query;

import java.time.YearMonth;
import java.util.UUID;

public record ConsultarRiscoMunicipalQuery(
        UUID municipioId,
        YearMonth competencia
) {
}
