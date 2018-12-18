package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.PedidoFornecedor;
import br.com.iverdura.iverdura.repository.ICustomSequenceRepository;
import br.com.iverdura.iverdura.repository.IPedidoFornecedor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class PedidoFornecedorServiceTest {


    @Mock
    IPedidoFornecedor iPedidoFornecedor;

    @Mock
    ICustomSequenceRepository iCustomSequenceRepository;

    @Mock
    CustomSequencesService customSequencesService;

    PedidoFornecedorService pedidoFornecedorService;



    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        pedidoFornecedorService = new PedidoFornecedorService(iPedidoFornecedor,  iCustomSequenceRepository,  customSequencesService);
    }



    @Test
    public void consultaPedidoFornecedorIdFornecedor(){

        when(iPedidoFornecedor.findByIdFornecedor(1l)).thenReturn(new ArrayList<>());

        pedidoFornecedorService.consultaPedidoFornecedorIdFornecedor(1l);
    }


    @Test
    public void  consultaPedidoFornecedorId(){

      when(iPedidoFornecedor.findByIdPedido(1l)).thenReturn(new PedidoFornecedor());

        pedidoFornecedorService.consultaPedidoFornecedorId(1l);
    }

    @Test
    public void atualizaStatus(){

            when(iPedidoFornecedor.findByIdPedido(anyLong())).thenReturn(new PedidoFornecedor());
            when(iPedidoFornecedor.save(any())).thenReturn(new PedidoFornecedor());

        pedidoFornecedorService.atualizaStatus(anyLong());

    }










}
