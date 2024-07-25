package bg.softuni.zooarchitectrest.service;

import bg.softuni.zooarchitectrest.model.dto.HabitatCreationDTO;
import bg.softuni.zooarchitectrest.model.entity.Habitat;
import bg.softuni.zooarchitectrest.repository.HabitatRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HabitatServiceTests {

    @Mock
    private HabitatRepository habitatRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private HabitatService habitatService;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        closeable.close();
    }
    @Test
    public void testGetAll() {
        List<Habitat> habitats = new ArrayList<>();
        when(habitatRepository.findAll()).thenReturn(habitats);

        List<Habitat> result = habitatService.getAll();

        assertEquals(habitats, result);
    }

    @Test
    public void testSave() {
        HabitatCreationDTO habitatCreationDTO = new HabitatCreationDTO();
        Habitat habitat = new Habitat();

        when(modelMapper.map(any(HabitatCreationDTO.class), eq(Habitat.class))).thenReturn(habitat);

        habitatService.save(habitatCreationDTO);

        ArgumentCaptor<Habitat> habitatArgumentCaptor = ArgumentCaptor.forClass(Habitat.class);
        verify(habitatRepository).save(habitatArgumentCaptor.capture());
        assertEquals(habitat, habitatArgumentCaptor.getValue());
    }

    @Test
    public void testGetHabitatById() {
        Habitat habitat = new Habitat();
        when(habitatRepository.findById(anyLong())).thenReturn(Optional.of(habitat));

        Habitat result = habitatService.getHabitatById(1L);

        assertEquals(habitat, result);
    }
}
