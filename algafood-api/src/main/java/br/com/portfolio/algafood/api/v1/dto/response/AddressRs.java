package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.api.v1.dto.View;
import br.com.portfolio.algafood.domain.entity.Address;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Objects;



public class AddressRs {

    @JsonView(View.Detail.class)
    private String cep;
    @JsonView(View.Detail.class)
    private String place;
    @JsonView(View.Detail.class)
    private String number;
    @JsonView(View.Detail.class)
    private String complement;
    @JsonView(View.Detail.class)
    private String district;
    @JsonView(View.Detail.class)
    private CityRs city;

    public AddressRs(Address address) {
        if (Objects.nonNull(address)) {
            this.cep = address.getCep();
            this.place = address.getPlace();
            this.number = address.getNumber();
            this.complement = address.getComplement();
            this.district = address.getDistrict();
            this.city = new CityRs(address.getCity());
        }
    }
    public String getCep() { return cep; }
    public String getPlace() { return place; }
    public String getNumber() { return number; }
    public String getComplement() { return complement; }
    public String getDistrict() { return district; }
    public CityRs getCity() { return city; }
}
