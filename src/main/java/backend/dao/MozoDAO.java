package backend.dao;

import java.util.List;
import backend.modelo.Mozo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MozoDAO extends JpaRepository<Mozo, String> {

    List<Mozo> findAll();

}
