package corp.model;

import java.util.Objects;

public class Employee extends Worker{
    public static final String TYPE = "Employee";
    private String function;
    private int level;

    public Employee (String name, String surname, String position, String function, int level){
        super(name, surname, position);
        this.function = function;
        this.level = level;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public String toCsv() {
        return (TYPE+"; ")+getName()+"; "+getSurname()+"; "+getPosition()+"; "+getFunction()+"; "+getLevel();
    }

    @Override
    public String toString() {
        return function+" "+level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return level == employee.level &&
                Objects.equals(function, employee.function);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), function, level);
    }

}
