package kata.ex01.model;

import kata.ex01.util.HolidayUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class HighwayDrive implements Serializable {
    private LocalDateTime enteredAt;
    private LocalDateTime exitedAt;
    private VehicleFamily vehicleFamily;
    private RouteType routeType;

    private Driver driver;

    public HighwayDrive() {
    }

    public boolean isHoliday() {

        if (HolidayUtils.isHoliday(enteredAt.toLocalDate())) return true;
        if (HolidayUtils.isHoliday(exitedAt.toLocalDate())) return true;

        return false;

    }

    private LocalDateTime getEnteredAt() {
        return this.enteredAt;
    }

    private LocalDateTime getExitedAt() {
        return this.exitedAt;
    }

    private VehicleFamily getVehicleFamily() {
        return this.vehicleFamily;
    }

    private RouteType getRouteType() {
        return this.routeType;
    }

    private Driver getDriver() {
        return this.driver;
    }

    public void setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
    }

    public void setExitedAt(LocalDateTime exitedAt) {
        this.exitedAt = exitedAt;
    }

    public void setVehicleFamily(VehicleFamily vehicleFamily) {
        this.vehicleFamily = vehicleFamily;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String toString() {
        return "HighwayDrive(enteredAt=" + this.getEnteredAt() + ", exitedAt=" + this.getExitedAt() + ", vehicleFamily=" + this.getVehicleFamily() + ", routeType=" + this.getRouteType() + ", driver=" + this.getDriver() + ")";
    }
}
