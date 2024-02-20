//package br.com.portfolio.algafood.api.hateoas;
//
//import br.com.portfolio.algafood.api.v1.controller.StateController;
//import br.com.portfolio.algafood.api.v1.dto.response.StateRs;
//import br.com.portfolio.algafood.domain.model.State;
//import org.springframework.hateoas.CollectionModel;
//import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
//import org.springframework.stereotype.Component;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//
//
//@Component
//public class StateAssembler extends RepresentationModelAssemblerSupport<State, StateRs> {
//
//    public StateAssembler() {
//        super(StateController.class, StateRs.class);
//    }
//
//    @Override
//    public StateRs toModel(State entity) {
//
//        return new StateRs(entity);
//    }
//
//    @Override
//    public CollectionModel<StateRs> toCollectionModel(Iterable<? extends State> entities) {
//        return super.toCollectionModel(entities)
//            .add(linkTo(StateController.class).withSelfRel());
//    }
//}
