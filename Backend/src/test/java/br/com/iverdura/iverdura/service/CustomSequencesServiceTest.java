package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.CustomSequence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class CustomSequencesServiceTest {



    @Mock
    MongoOperations mongo;


    CustomSequencesService customSequencesService;


    CustomSequence customSequence;




    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        customSequencesService = new CustomSequencesService(mongo);
    }


    @Test
    public void proximaSequencia(){


     //  when(mongo.findAndModify(any(), any(), any(), CustomSequencesService.class)).thenReturn(new OngoingStubbing<CustomSequence>);


      //  CustomSequence customSequence = new CustomSequence();

      //  customSequence.setSeq(2l);
      //  customSequence.setId("produto");


       // when(mongo.findAndModify(Query.class,Update.class,FindAndModifyOptions.class,CustomSequence.class)).thenReturn(any());


       // customSequencesService.proximaSequencia("produto");

    }



}







