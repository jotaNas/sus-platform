package com.fiap.cardioradar.application.query;

import java.time.YearMonth;

public record ConsultarRankingMunicipiosQuery(
        YearMonth competencia,
        int limite
) {
}
