package br.com.iverdura.iverdura.repository;

import br.com.iverdura.iverdura.model.CustomSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICustomSequenceRepository  extends MongoRepository<CustomSequence, String> {
}
