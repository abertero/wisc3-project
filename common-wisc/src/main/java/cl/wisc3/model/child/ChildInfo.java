package cl.wisc3.model.child;

import cl.wisc3.config.JPA;
import cl.wisc3.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.List;

@Entity
public class ChildInfo extends BaseEntity {

    private String firstName;
    private String lastName;
    private String motherName;
    private String fatherName;
    @Lob
    private String description;
    private int birthDay;
    private int birthMonth;
    private int birthYear;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public static ChildInfo findByAltKey(String altKey) {
        return JPA.findByAltKey(ChildInfo.class, altKey);
    }

    public static List<ChildInfo> findAll() {
        return JPA.findAll(ChildInfo.class, ORDER);
    }
}
