package br.com.portfolio.algafood.api.v1.dto.response;

import br.com.portfolio.algafood.domain.model.Address;

import java.util.Objects;

public class AddressRs {

    private String cep;
    private String place;
    private String number;
    private String complement;
    private String district;
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
