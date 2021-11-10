package br.com.portfolio.algafood.api.v1.dto.request;

import br.com.portfolio.algafood.domain.entity.Address;
import br.com.portfolio.algafood.domain.entity.City;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AddressRq {

    @NotBlank
    private final String cep;
    @NotBlank
    private final String place;
    @NotBlank
    private final String number;
    private final String complement;
    @NotBlank
    private final String district;
    @NotNull
    private final Long cityId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AddressRq(String cep, String place, String number, String complement, String district, Long cityId) {
        this.cep = cep;
        this.place = place;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.cityId = cityId;
    }
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

    public String getCep() {
        return cep;
    }

    public String getPlace() {
        return place;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public Long getCityId() {
        return cityId;
    }
}
