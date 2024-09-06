package app.com._paws.domain.models;

import app.com._paws.domain.dtos.ServiceTypeDTO;
import app.com._paws.utils.Identifiable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "appointment_types")
public class AppointmentType implements ServiceType, Identifiable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;

    String description;

    Double basePrice;

    @OneToMany(mappedBy = "appointmentType")
    List<Appointment> appointments;

    public AppointmentType(ServiceTypeDTO examTypeDTO) {
        this.name = examTypeDTO.name();
        this.description = examTypeDTO.description();
        this.basePrice = examTypeDTO.basePrice();
    }
}