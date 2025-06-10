package mate.academy.rickandmorty.controller;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.RickyCharacterClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/character")
public class RickyCharacterController {
    private final RickyCharacterClient rickyCharacterClient;

    @GetMapping("/test")
    public String testClient() {
        rickyCharacterClient.getCharacter(1L);
        return "Done!";
    }
}
