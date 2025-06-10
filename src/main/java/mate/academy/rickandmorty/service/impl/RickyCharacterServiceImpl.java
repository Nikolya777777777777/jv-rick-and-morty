package mate.academy.rickandmorty.service.impl;

import mate.academy.rickandmorty.dto.internal.RickyCharacterDto;
import mate.academy.rickandmorty.mapper.RickyCharacterMapper;
import mate.academy.rickandmorty.model.RickyCharacter;
import mate.academy.rickandmorty.repository.RickyCharacterRepository;
import mate.academy.rickandmorty.service.RickyCharacterService;
import org.springframework.stereotype.Service;

@Service
public class RickyCharacterServiceImpl implements RickyCharacterService {
    private final RickyCharacterRepository rickyCharacterRepository;
    private final RickyCharacterMapper rickyCharacterMapper;

    public RickyCharacterServiceImpl(RickyCharacterRepository rickyCharacterRepository,
                                     RickyCharacterMapper rickyCharacterMapper) {
        this.rickyCharacterRepository = rickyCharacterRepository;
        this.rickyCharacterMapper = rickyCharacterMapper;
    }

    @Override
    public RickyCharacterDto save(RickyCharacter rickyCharacter) {
        return rickyCharacterMapper.toDto(rickyCharacterRepository.save(rickyCharacter));
    }
}
