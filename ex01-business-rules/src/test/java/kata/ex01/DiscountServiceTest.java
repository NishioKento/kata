package kata.ex01;

import kata.ex01.model.Driver;
import kata.ex01.model.HighwayDrive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.STANDARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author kawasima
 */
class DiscountServiceTest {
    private DiscountService discountService;

    private Driver driver(int usingCount) {
        Driver driver = new Driver();
        driver.setCountPerMonth(usingCount);
        return driver;
    }

    @BeforeEach
    void setUp() {
        discountService = new DiscountServiceImpl();
    }

    @Test
    void test平日朝夕割引() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2016, 3, 31, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2016, 4, 1, 6, 30));
        drive.setDriver(driver(10));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(50);
    }

    @ParameterizedTest
    @MethodSource("dateProvider")
    void test休日は休日割が適用される(LocalDateTime enteredAt, LocalDateTime exitedAt, int usingCount, int excepted) {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(enteredAt);
        drive.setExitedAt(exitedAt);
        drive.setDriver(driver(usingCount));
        drive.setVehicleFamily(STANDARD);
        drive.setRouteType(RURAL);

        assertThat(discountService.calc(drive)).isEqualTo(excepted);

    }

    static Stream<Arguments> dateProvider() {
        return Stream.of(
                // 出たのが休日のパターン
                arguments(
                        LocalDateTime.of(2016, 4, 1, 23, 0),
                        LocalDateTime.of(2016, 4, 2, 6, 30),
                        10,
                        30
                ),
                // 入ったのが休日のパターン
                arguments(
                        LocalDateTime.of(2016, 4, 3, 23, 0),
                        LocalDateTime.of(2016, 4, 4, 6, 30),
                        10,
                        30
                ),
                // 休日にならないパターン
                arguments(
                        LocalDateTime.of(2016, 4, 4, 23, 0),
                        LocalDateTime.of(2016, 4, 5, 6, 30),
                        10,
                        0
                )
        );
    }

}
