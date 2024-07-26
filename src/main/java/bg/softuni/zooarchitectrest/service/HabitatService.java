package bg.softuni.zooarchitectrest.service;

import bg.softuni.zooarchitectrest.model.dto.HabitatCreationDTO;
import bg.softuni.zooarchitectrest.model.entity.Habitat;
import bg.softuni.zooarchitectrest.repository.HabitatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitatService {
    private final HabitatRepository habitatRepository;

    private final ModelMapper modelMapper;

    public HabitatService(HabitatRepository habitatRepository, ModelMapper modelMapper) {
        this.habitatRepository = habitatRepository;
        this.modelMapper = modelMapper;
    }

    public List<Habitat> getAll() {
        return habitatRepository.findAll();
    }

    public Habitat save(HabitatCreationDTO habitatCreationDTO) {
        Habitat habitat = modelMapper.map(habitatCreationDTO, Habitat.class);
        return habitatRepository.save(habitat);
    }

    public Habitat getHabitatById(long habitatId) {
        return habitatRepository.findById(habitatId).orElseThrow(() -> new IllegalArgumentException("Habitat with id " + habitatId + " not found"));
    }

    public void deleteHabitat(Long id) {
        habitatRepository.deleteById(id);
    }

    public void deleteAll() {
        habitatRepository.deleteAll();
    }
}
