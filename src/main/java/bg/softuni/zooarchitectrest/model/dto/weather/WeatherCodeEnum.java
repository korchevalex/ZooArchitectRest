package bg.softuni.zooarchitectrest.model.dto.weather;

import lombok.Getter;

@Getter
public enum WeatherCodeEnum {
    CLEAR_SKY(0, "Clear sky"),
    PARTLY_CLOUDY(1, "Partly cloudy"),
    CLOUDY(2, "Cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    DEPOSITING_RIME_FOG(48, "Depositing rime fog"),
    LIGHT_DRIZZLE(51, "Light drizzle"),
    DRIZZLE(53, "Drizzle"),
    DENSE_DRIZZLE(53, "Dense drizzle"),
    LIGHT_FREEZING_DRIZZLE(56, "Light freezing drizzle"),
    DENSE_FREEZING_DRIZZLE(57, "Dense freezing drizzle"),
    SLIGHT_RAIN(61, "Slight rain"),
    RAIN(63, "Rain"),
    HEAVY_RAIN(65, "Heavy rain"),
    LIGHT_FREEZING_RAIN(66, "Light freezing rain"),
    HEAVY_FREEZING_RAIN(67, "Heavy freezing rain"),
    SLIGHT_SNOWFALL(71, "Slight snowfall"),
    SNOWFALL(73, "Snowfall"),
    HEAVY_SNOWFALL(75, "Heavy snowfall"),
    SNOW_GRAINS(77, "Snow grains"),
    SLIGHT_RAIN_SHOWER(80, "Slight rain shower"),
    RAIN_SHOWER(81, "Rain shower"),
    VIOLENT_RAIN_SHOWER(82, "Violent rain shower"),
    SLIGHT_SNOW_SHOWER(85, "Slight snow shower"),
    HEAVY_SNOW_SHOWER(86, "Heavy snow shower"),
    THUNDERSTORM(95, "Thunderstorm"),
    HEAVY_THUNDERSTORM(96, "Heavy thunderstorm"),
    HEAVY_THUNDERSTORM_WITH_HAIL(96, "Heavy thunderstorm");

    private final int code;
    private final String description;

    WeatherCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

    public static String fromCode(int code) {
        for (WeatherCodeEnum w : WeatherCodeEnum.values()) {
            if (w.getCode() == code) {
                return w.description;
            }
        }
        throw new IllegalArgumentException("Unknown WMO code: " + code);
    }
}
