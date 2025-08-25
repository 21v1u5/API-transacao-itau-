package com.itau.transacao_api.business.services;


import com.itau.transacao_api.controller.dtos.EstatisticasReponseDTO;
import com.itau.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasReponseDTO calcularEstatisticasTransacoes(Integer intervalorBusca) {

        log.info("Iniciada busca de estatísticas de transações pelo período de tempo " + intervalorBusca);
        List<TransacaoRequestDTO> transacoes =  transacaoService.buscarTransacoes(intervalorBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().
                mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");

        return new EstatisticasReponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());



    }
}
