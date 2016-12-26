package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

}