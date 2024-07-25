package bg.softuni.zooarchitectrest.model.dto.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Current {
    private String time;
    private int interval;
    private double temperature_2m;
    private int weather_code;
}
