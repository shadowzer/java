package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entity.Record;
import server.repository.RecordRepository;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public List<Record> getAll() {
        return recordRepository.findAll();
    }

    public Record getByID(int id) {
        return recordRepository.findOne(id);
    }

    public Record save(Record record) {
        return recordRepository.saveAndFlush(record);
    }

    public void remove(int id) {
        recordRepository.delete(id);
    }
}
