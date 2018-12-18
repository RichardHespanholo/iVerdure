package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import br.com.iverdura.iverdura.repository.IProdutoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class ProdutoServiceTest {


    @Mock
    private IProdutoRepository IProdutoRepository;

    @Mock
    private IFornecedorRepository ifornecedorRepository;

    @Mock
    private MongoOperations mongo;

    @Mock
    private CustomSequencesService customSequencesService;

    private ProdutoService produtoService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        produtoService = new ProdutoService(IProdutoRepository, ifornecedorRepository,customSequencesService);
    }



    @Test
    public void listarTodosProdutos(){

        when(IProdutoRepository.findAll()).thenReturn(new ArrayList<Produto>());
        produtoService.listarTodosProdutos();

    }

    @Test
    public void buscaProduto(){

        when(IProdutoRepository.findAllByIdProdutoAndIdFornecedor(anyLong(),anyLong())).thenReturn(new Produto());
        produtoService.buscaProduto(1l,3l);

    }

    @Test
    public void buscarProduto(){

        when(IProdutoRepository.findAllByIdProdutoAndIdFornecedor(anyLong(),anyLong())).thenReturn(new Produto());
        produtoService.buscarProduto(1l,3l);

    }

    @Test
    public void removerProduto(){

        doNothing().when(IProdutoRepository).removeByIdFornecedorAndIdProduto(anyLong(),anyLong());

        produtoService.removerProduto(1l,1l);

    }

    @Test
    public void adicionarQtdProduto(){

        Produto p = new Produto();

        p.setQtd(10l);

        when(IProdutoRepository.findAllByIdProdutoAndIdFornecedor(anyLong(),anyLong())).thenReturn(p);

        when(IProdutoRepository.save(any())).thenReturn(new Produto());



        produtoService.adicionarQtdProduto(1l,10l,3l);

    }

    @Test
    public void verificaEstoque(){

       when(IProdutoRepository.findAllByIdProdutoAndIdFornecedor(anyLong(),anyLong())).thenReturn(new Produto(0l,"",10l,"",0.0,0.0,0l,"",1l));
        Produto p = new Produto();
        p.setQtd(1l);
        p.setIdFornecedor(1l);
        p.setIdProduto(0l);

        produtoService.verificaEstoque(p);
    }

    @Test
    public void salvaProduto(){

        Produto p = new Produto();

        p.setIdFornecedor(1l);
        p.setIdProduto(2l);

        when(ifornecedorRepository.findAllByIdFornecedor(anyLong())).thenReturn(new Fornecedor());
        when(IProdutoRepository.save(any())).thenReturn(new Produto());
        when(customSequencesService.proximaSequencia("produto")).thenReturn(2l);


        produtoService.salvaProduto(p);
    }


}
