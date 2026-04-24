package br.com.fiap3espb.auto_escola_3espb.adapter.out.client.viacep;

import br.com.fiap3espb.auto_escola_3espb.exception.type.instrucao.ValidacaoException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class ViaCepClient {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://viacep.com.br")
            .build();

    public void validarCepExistente(String cepComOuSemMascara) {
        String digitos = cepComOuSemMascara.replaceAll("\\D", "");
        ViaCepResponse r = consultarCep(digitos);
        if(r == null || Boolean.TRUE.equals(r.erro())) {
            throw new ValidacaoException("CEP não encontrado ou inválido segundo a base ViaCEP.");
        }
    }

    private ViaCepResponse consultarCep(String cepNumeros) {
        try {
            return restClient.get()
                    .uri("/ws/{cep}/json/", cepNumeros)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(ViaCepResponse.class);
        } catch (RestClientException ex) {
            throw new ValidacaoException("Não foi possível validar o CEP na API externa (ViaCEP).");
        }
    }
}
