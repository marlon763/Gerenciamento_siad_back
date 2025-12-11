package br.com.siad.api.DTO.filiais;

import br.com.siad.api.enums.FilialTypes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterFilialDTO(

        @NotBlank(message = "Campo nome é obrigatório!")
        String name,

        @NotNull(message = "Campo tipo é obrigatório!")
        FilialTypes type

) {}
