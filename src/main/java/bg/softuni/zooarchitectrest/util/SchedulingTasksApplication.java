package bg.softuni.zooarchitectrest.util;

import bg.softuni.zooarchitectrest.model.weather.Root;
import bg.softuni.zooarchitectrest.model.weather.WeatherCodeEnum;
import bg.softuni.zooarchitectrest.model.entity.Habitat;
import bg.softuni.zooarchitectrest.repository.HabitatRepository;
import bg.softuni.zooarchitectrest.service.HabitatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {
    private final HabitatService habitatService;
    private final RestClient weatherRestClient;
    private final HabitatRepository habitatRepository;

    public SchedulingTasksApplication(HabitatService habitatService, @Qualifier("weatherRestClient") RestClient weatherRestClient, HabitatRepository habitatRepository) {
        this.habitatService = habitatService;
        this.weatherRestClient = weatherRestClient;
        this.habitatRepository = habitatRepository;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void updateHabitatWeather() {
        List<Habitat> habitats = habitatService.getAll();
        habitats.forEach(habitat -> {
            Root root = weatherRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/forecast")
                            .queryParam("latitude", habitat.getLatitude())
                            .queryParam("longitude", habitat.getLongitude())
                            .queryParam("current", "temperature_2m,weather_code")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(Root.class);
            assert root != null;
            habitat.setTemperature(root.getCurrent().getTemperature_2m());
            habitat.setWeatherCondition(WeatherCodeEnum.fromCode(root.getCurrent().getWeather_code()));
            habitatRepository.save(habitat);
        });
    }
}
