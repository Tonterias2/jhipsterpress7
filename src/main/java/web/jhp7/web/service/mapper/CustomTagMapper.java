package web.jhp7.web.service.mapper;

import web.jhp7.web.domain.*;
import web.jhp7.web.service.dto.CustomTagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tag and its DTO TagDTO.
 */
@Mapper(componentModel = "spring", uses = {PostMapper.class})
public interface CustomTagMapper extends EntityMapper<CustomTagDTO, Tag> {

	

    default Tag fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(id);
        return tag;
    }
}
