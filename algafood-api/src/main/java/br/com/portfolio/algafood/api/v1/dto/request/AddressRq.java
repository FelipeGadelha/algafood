package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Address;
import br.com.portfolio.algafood.domain.entity.City;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AddressRq(
        @NotBlank String cep,
        @NotBlank String place,
        @NotBlank String number,
        String complement,
        @NotBlank String district,
        @NotNull Long cityId
) {
    public Address convert() {
        return Address.builder()
                .cep(cep)
                .place(place)
                .number(number)
                .complement(complement)
                .district(district)
                .city(City.builder().id(cityId).build())
                .build();
    }
}
