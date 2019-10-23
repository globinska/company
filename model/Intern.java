package corp.model;

import java.util.Objects;

public class Intern extends Worker {
    public static final String TYPE = "Intern";
    private String universityName;
    private int yearOfStudies;

    public Intern (String name, String surname, String position, String universityName, int yearOfStudies){
        super(name, surname, position);
        this.universityName = universityName;
        this.yearOfStudies = yearOfStudies;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(int yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }


    @Override
    public String toCsv() {
        return (TYPE+"; ")+getName()+"; "+getSurname()+"; "+getPosition()+"; "+getUniversityName()+"; "+getYearOfStudies();
    }

    @Override
    public String toString() {
        return super.toString()+" "+universityName+" "+getYearOfStudies();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
       Intern intern = (Intern) o;
        return yearOfStudies == intern.yearOfStudies &&
                Objects.equals(universityName, intern.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName, yearOfStudies);
    }

}
