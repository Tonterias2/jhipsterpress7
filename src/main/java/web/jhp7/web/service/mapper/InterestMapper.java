package web.jhp7.web.service.mapper;

import org.mapstruct.Mapper;

import web.jhp7.web.domain.Interest;
import web.jhp7.web.service.dto.InterestDTO;

/**
 * Mapper for the entity Interest and its DTO InterestDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InterestMapper extends EntityMapper<InterestDTO, Interest> {



    default Interest fromId(Long id) {
        if (id == null) {
            return null;
        }
        Interest interest = new Interest();
        interest.setId(id);
        return interest;
    }
}
