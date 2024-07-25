package bg.softuni.zooarchitectrest.model.dto.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentUnits {
    private String time;
    private String interval;
    private String temperature_2m;
    private String weather_code;
}
