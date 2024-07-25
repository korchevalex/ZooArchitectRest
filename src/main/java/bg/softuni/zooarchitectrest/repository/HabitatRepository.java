package bg.softuni.zooarchitectrest.repository;

import bg.softuni.zooarchitectrest.model.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
}
