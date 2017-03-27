package pl.budyn.eman_app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by hlibe on 29.12.2016.
 */
@Entity
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 1, max = 15)
    private String name;

    @NotNull
    private Float salary;

    private String description;

    public Job() {
    }

    public Job(String name, Float salary) {
        this.name = name;
        this.salary = salary;
        this.description = "No description :(";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                '}';
    }
}

