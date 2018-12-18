

/*

package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class FornecedorServiceTest {


    @Mock
    IFornecedorRepository ifornecedorRepository;

    FornecedorService forncedorService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        forncedorService = new FornecedorService(ifornecedorRepository);
    }


    @Test
    public void consultaFornecedores(){

        when(ifornecedorRepository.findAll()).thenReturn(new ArrayList<Fornecedor>());

        forncedorService.consultaFornecedores();

    }


    @Test
    public void salvarFornecedor(){

        when(ifornecedorRepository.save(any())).thenReturn(new Fornecedor());


        forncedorService.salvarFornecedor(any());

    }

    @Test
    public void consultaFornecedor(){

        when(ifornecedorRepository.findAllByIdFornecedor(anyLong())).thenReturn(new Fornecedor());

        forncedorService.consultaFornecedor(anyLong());

    }

    @Test
    public void removerFornecedor(){


       doNothing().when(ifornecedorRepository).removeByIdFornecedor(anyLong());

        forncedorService.removerFornecedor(anyLong());


    }




}

*/