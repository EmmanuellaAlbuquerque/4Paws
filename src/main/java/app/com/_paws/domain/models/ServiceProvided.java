package app.com._paws.domain.models;

import app.com._paws.domain.dtos.ServiceDTO;
import app.com._paws.utils.Identifiable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "services")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvided implements Identifiable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Double price;

    @OneToMany(mappedBy = "serviceProvided", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public ServiceProvided(ServiceDTO serviceDTO) {
        this.name = serviceDTO.name();
        this.price = serviceDTO.price();
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}