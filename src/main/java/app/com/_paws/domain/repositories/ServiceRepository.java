package app.com._paws.domain.repositories;

import app.com._paws.domain.models.ServiceProvided;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceProvided, Integer> {

}