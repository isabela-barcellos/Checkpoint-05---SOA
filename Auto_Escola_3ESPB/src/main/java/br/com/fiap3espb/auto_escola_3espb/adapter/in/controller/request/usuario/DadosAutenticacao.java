package br.com.fiap3espb.auto_escola_3espb.adapter.in.controller.request.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

        @NotBlank
        String login,

        @NotBlank
        String senha) {
}