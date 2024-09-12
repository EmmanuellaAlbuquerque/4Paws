package app.com._paws.domain.models;

import app.com._paws.domain.dtos.ServiceTypeDTO;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;

    String description;

    Double basePrice;

    @JsonIgnore
    @OneToMany(mappedBy = "examType", fetch = FetchType.LAZY)
    List<Exam> exams;

    public ExamType(ServiceTypeDTO examTypeDTO) {
        this.name = examTypeDTO.name();
        this.description = examTypeDTO.description();
        this.basePrice = examTypeDTO.basePrice();
    }
}