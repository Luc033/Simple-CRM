package com.luc.cadastrocliente.entity.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record ErrorResponse(
        @JsonProperty("status")
        String error,

        @JsonProperty("message")
        String message,

        @JsonProperty("details")
        List<ErroDetalhe> details,

        @JsonProperty("timestamp")
        LocalDateTime timestamp,

        @JsonProperty("path")
        String path,

        @JsonProperty("requestId")
        UUID requestId
) {
}
