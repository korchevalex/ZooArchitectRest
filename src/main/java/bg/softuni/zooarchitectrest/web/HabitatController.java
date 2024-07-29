package bg.softuni.zooarchitectrest.web;

import bg.softuni.zooarchitectrest.model.dto.HabitatCreationDTO;
import bg.softuni.zooarchitectrest.model.entity.Habitat;
import bg.softuni.zooarchitectrest.service.HabitatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/habitats")

public class HabitatController {
    private final HabitatService habitatService;

    public HabitatController(HabitatService habitatService) {
        this.habitatService = habitatService;
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The habitat details",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Habitat.class)
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "404", description = "If the habitat was not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json"
                                    )
                            }
                    )
            }
    )
    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<Habitat> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(habitatService.getHabitatById(id));
    }

    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Habitat> deleteById(@PathVariable("id") Long id) {
        habitatService.deleteHabitat(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
    @PostMapping("/create")
    public ResponseEntity<Habitat> createOffer(
            @RequestBody HabitatCreationDTO habitatCreationDTO
    ) {
        Habitat habitat = habitatService.save(habitatCreationDTO);
        return ResponseEntity.
                created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(habitat.getId())
                                .toUri()
                ).body(habitat);
    }

    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
    @GetMapping
    public ResponseEntity<List<Habitat>> getAllHabitats() {
        return ResponseEntity.ok(
                habitatService.getAll()
        );
    }

    @Operation(
            security = @SecurityRequirement(
                    name = "bearer-token"
            )
    )
    @DeleteMapping
    public ResponseEntity<Habitat> deleteAllHabitats() {
        habitatService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
