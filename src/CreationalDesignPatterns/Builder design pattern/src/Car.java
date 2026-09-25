public class Car {
    private String engine;
    private int wheels;
    private int seats;
    private String color;
    private boolean sunroof;
    private boolean navigationSystem;

    public Car(CarBuilder carBuilder){
        this.engine = carBuilder.engine;
        this.wheels = carBuilder.wheels;
        this.seats = carBuilder.seats;
        this.color = carBuilder.color;
        this.sunroof = carBuilder.sunroof;
        this.navigationSystem = carBuilder.navigationSystem;
    }

    public static class CarBuilder {
        private String engine;
        private int wheels = 4;
        private int seats = 4;
        private String color;
        private boolean sunroof;
        private boolean navigationSystem;

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public void setWheels(int wheels) {
            this.wheels = wheels;
        }

        public void setSeats(int seats) {
            this.seats = seats;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
        }

        public void setNavigationSystem(boolean navigationSystem) {
            this.navigationSystem = navigationSystem;
        }

        public Car build(){
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine='" + engine + '\'' +
                ", wheels=" + wheels +
                ", seats=" + seats +
                ", color='" + color + '\'' +
                ", sunroof=" + sunroof +
                ", navigationSystem=" + navigationSystem +
                '}';
    }
}