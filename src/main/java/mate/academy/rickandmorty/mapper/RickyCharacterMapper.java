package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.RickyCharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.RickyCharacterDto;
import mate.academy.rickandmorty.model.RickyCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface RickyCharacterMapper {

    @Mapping(target = "externalId", source = "externalId", qualifiedByName = "stringToLong")
    RickyCharacter toEntity(RickyCharacterResponseDataDto rickyCharacterResponseDataDto);

    RickyCharacterDto toDto(RickyCharacter rickyCharacter);

    @Named("stringToLong")
    static Long stringToLong(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(id);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Can not format spring number id: " + id + " to Long");
        }
    }
}
