package br.com.portfolio.algafood.api.v1.model.xml;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.portfolio.algafood.domain.entity.Kitchen;

@JacksonXmlRootElement(localName = "kitchens")
public class KitchensWrapper {
	
	@JsonProperty("kitchen")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Kitchen> kitchens;
	
	@Deprecated
	public KitchensWrapper() { }
	
	public KitchensWrapper(List<Kitchen> kitchens) {
		this.kitchens = kitchens;
	}
	
	public List<Kitchen> getKitchens() {
		return kitchens;
	}
	

}
