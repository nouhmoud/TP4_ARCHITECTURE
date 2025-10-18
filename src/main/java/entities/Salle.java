package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "salles")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "salle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Machine> machines;

    public Salle() {}

    public Salle(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public String toString() {
        return "Salle{id=" + id + ", code='" + code + "'}";
    }
}
