package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickyCharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.RickyCharacterMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickyCharacterClient {
    private static final String BASE_URL_FIND_CHARACTER_BY_ID = "https://rickandmortyapi.com/api/character/%s";
    private final ObjectMapper objectMapper;
    private final RickyCharacterService characterService;
    private final RickyCharacterMapper rickyCharacterMapper;

    public RickyCharacterResponseDataDto getCharacter(Long id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL_FIND_CHARACTER_BY_ID.formatted(String.valueOf(id));

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
            RickyCharacterResponseDataDto rickyCharacterResponseDataDto = objectMapper
                    .readValue(response.body(), RickyCharacterResponseDataDto.class);
            characterService.save(rickyCharacterMapper.toEntity(rickyCharacterResponseDataDto));
            return rickyCharacterResponseDataDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
