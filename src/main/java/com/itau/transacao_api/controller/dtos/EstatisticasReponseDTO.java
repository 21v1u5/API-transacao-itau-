package com.itau.transacao_api.controller.dtos;

public record EstatisticasReponseDTO(Long count,
                                     Double sum,
                                     Double avg,
                                     Double min,
                                     Double max) {
}
