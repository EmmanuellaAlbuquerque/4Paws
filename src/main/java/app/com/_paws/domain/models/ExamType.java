package app.com._paws.domain.models;

import app.com._paws.domain.dtos.servicetype.ServiceTypeDTO;
import app.com._paws.utils.Identifiable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "exam_types")
@NoArgsConstructor
public class ExamType implements ServiceType, Identifiable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String description;

    @Column(name = "base_price", nullable = false)
    Double basePrice;

    @JsonIgnore
    @OneToMany(mappedBy = "examType", fetch = FetchType.LAZY)
    List<Exam> exams;

    public ExamType(ServiceTypeDTO examTypeDTO) {
        this.name = examTypeDTO.getName();
        this.description = examTypeDTO.getDescription();
        this.basePrice = examTypeDTO.getBasePrice();
    }
}