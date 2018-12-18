package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.CustomSequence;
import br.com.iverdura.iverdura.model.PedidoFornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class CustomSequencesService {

    @Autowired
    private MongoOperations mongo;


    CustomSequence  customSequence;


    public CustomSequencesService(MongoOperations mongo){
        this.mongo = mongo;
    }


    public Long proximaSequencia(String seqName)
    {
        CustomSequence counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                CustomSequence.class);
        return counter.getSeq();
    }


}
