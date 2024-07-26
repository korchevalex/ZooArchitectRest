package bg.softuni.zooarchitectrest.model.weather;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Root {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private CurrentUnits current_units;
    private Current current;
}
