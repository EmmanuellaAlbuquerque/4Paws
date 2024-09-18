package app.com._paws.controllers;

import app.com._paws.docs.ExamControllerDocs;
import app.com._paws.services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/exams", produces = {"application/json"})
@RequiredArgsConstructor
public class ExamController implements ExamControllerDocs {

    private final ExamService examService;

    @DeleteMapping("{examId}")
    public ResponseEntity<Void> deleteExam(@PathVariable(value = "examId") UUID examId) {
        this.examService.deleteById(examId);
        return ResponseEntity.ok().build();
    }
}