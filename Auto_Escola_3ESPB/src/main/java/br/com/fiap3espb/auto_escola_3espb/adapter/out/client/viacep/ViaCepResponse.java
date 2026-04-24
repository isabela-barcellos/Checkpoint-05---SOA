package br.com.fiap3espb.auto_escola_3espb.adapter.out.client.viacep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(Boolean erro) {
}
