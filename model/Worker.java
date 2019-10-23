package corp.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Worker implements Serializable{
        private String name;
        private String surname;
        private String position;

    public Worker(String name, String surname, String position){
        this.name = name;
        this.surname = surname;
        this.position = position;
    }
        public abstract String toCsv();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return name+" "+surname+" "+position;
    }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker that = (Worker) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(position, that.position);
    }

        @Override
        public int hashCode() {
        return Objects.hash(name, surname, position);
    }
}
