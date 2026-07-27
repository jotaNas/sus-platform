package br.com.fiap.providers;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.fiap.dtos.erro.ErroRespostaDTO;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GerenciadorExcecoes implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        // Captura o erro disparado pelo Enum (Sigla de UF inválida)
        if (exception instanceof IllegalArgumentException illegalEx) {
            int status = Response.Status.BAD_REQUEST.getStatusCode();
            return Response.status(status)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErroRespostaDTO(illegalEx.getMessage(), status, LocalDateTime.now()))
                    .build();
        }

        // O governo (DEMAS) retornou um erro HTTP (Ex: 404, 500, 400)
        if (exception instanceof WebApplicationException webEx) {
            int status = webEx.getResponse().getStatus();
            String mensagem = "Erro na comunicação com a API do Ministério da Saúde.";
            
            if (status == 404) {
                mensagem = "Recurso ou filtro informado não foi encontrado na base do SUS.";
            } else if (status == 400) {
                mensagem = "Parâmetros de requisição inválidos enviados para o governo.";
            }

            return Response.status(status)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErroRespostaDTO(mensagem, status, LocalDateTime.now()))
                    .build();
        }

        // Erro de conversão de dados (Ex: o erro do OffsetDateTime que vimos antes)
        if (exception instanceof InvalidFormatException formatEx) {
            int status = Response.Status.BAD_REQUEST.getStatusCode();
            String mensagem = "Erro de formato no campo: " + formatEx.getPath().get(0).getFieldName() 
                            + ". Verifique o tipo de dado enviado.";
            
            return Response.status(status)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(new ErroRespostaDTO(mensagem, status, LocalDateTime.now()))
                    .build();
        }

        // Qualquer outro erro inesperado no sistema (Fallback)
        int statusInterno = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        return Response.status(statusInterno)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ErroRespostaDTO("Ocorreu um erro interno inesperado no servidor.", statusInterno, LocalDateTime.now()))
                .build();
    }
}
