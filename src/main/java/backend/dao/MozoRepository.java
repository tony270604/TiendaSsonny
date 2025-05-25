package backend.dao;

import backend.modelo.Mozo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MozoRepository extends JpaRepository<Mozo, String> {
}
