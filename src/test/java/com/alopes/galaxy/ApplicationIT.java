package com.alopes.galaxy;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.alopes.galaxy.models.dtos.WeatherForecastSummary;
import com.alopes.galaxy.models.entities.WeatherForecast;
import com.alopes.galaxy.services.SchedulerService;
import lombok.val;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIT {

    @LocalServerPort
    int port;

    @Autowired
    private SchedulerService schedulerService;

    @BeforeEach
    public void setup() throws InterruptedException {
        schedulerService.triggerSummaryJobs();
        // Wait until everything is calculated.
        Thread.sleep(5000);
    }

    @Test
    void Should_Return_Summary() {
        val summary = given().port(port).get("/weather-forecast/summary").andReturn()
                .as(WeatherForecastSummary.class);

        assertNotNull(summary);
    }

    @Test
    void Should_Get_Specific_Day() {
        val dailyForecast = given().port(port).get("/weather-forecast/244").andReturn()
                .as(WeatherForecast.class);

        assertNotNull(dailyForecast);
    }
}
