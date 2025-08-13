package com.itau.transacao_api.business.services;

import com.itau.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.itau.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {

        log.info("Iniciada o processamento de gravar transações");

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora e hora maiores que a atual");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atual");
        }
        if(dto.valor() < 0){
            log.error("Valor nao pode ser menor que zero");
            throw new UnprocessableEntity("Valor nao pode ser menor que zero");
        }

        listaTransacoes.add(dto);
    }

    public void limparTransacoes(){
        listaTransacoes.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        return listaTransacoes.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();

    }
}
