package com.rasmoo.cliente.escola.gradecurricular.dto;

import com.rasmoo.cliente.escola.gradecurricular.validations.GroupsValidations;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MateriaDto extends RepresentationModel<MateriaDto> {

    @NotNull(groups = { GroupsValidations.MateriaDtoId.class })
    private Long id;

    @NotBlank(message = "Informe o nome da matéria")
    private String nome;

    @NotBlank(message = "Informe o código da matéria")
    private String codigo;

    @Min(value = 34, message = "Permitido o mínimo de 34 horas por matéria")
    @Max(value = 120, message = "Permitido o máximo de 120 horas por matéria")
    private Integer horas;

    @Min(value = 1, message = "Permitido o mínimo de 1 vez ao ano")
    @Max(value = 2, message = "Permitido o máximo de 2 vezes ao ano")
    private Integer frequencia;

}
