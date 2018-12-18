package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.PagamentoCartao;
import br.com.iverdura.iverdura.model.Pedido;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import br.com.iverdura.iverdura.repository.IPedidoRepository;
import br.com.iverdura.iverdura.repository.IProdutoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class PedidoServiceTest {


    @Mock
    private IPedidoRepository ipedidoRepository;

    @Mock
    private IFornecedorRepository ifornecedorRepository;

    @Mock
    private IProdutoRepository iprodutoRepository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private CustomSequencesService customSequencesService;


    PedidoService pedidoFornecedorService;



    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        pedidoFornecedorService = new PedidoService(ipedidoRepository, ifornecedorRepository, iprodutoRepository, produtoService,customSequencesService);


    }

    @Test
    public void consultaPedidos(){

        when(ipedidoRepository.findAllByCpfUsuario(anyString())).thenReturn(new ArrayList<Pedido>());

        pedidoFornecedorService.consultaPedidos(anyString());


    }

    @Test
    public void consultaTodosPedidos(){

        when(ipedidoRepository.findAll()).thenReturn(new ArrayList<>());

        pedidoFornecedorService.consultaTodosPedidos();

    }


    @Test
    public void updateStatus(){

        when(ipedidoRepository.save(any())).thenReturn(new Pedido());
        when(ipedidoRepository.findAllByIdPedido(anyLong())).thenReturn(new Pedido());

        pedidoFornecedorService.updateStatus("pendente",anyLong());

    }

    @Test
    public void salvarPedido(){

        Pedido pedido = new Pedido();
        PagamentoCartao pagamento = new PagamentoCartao();
        pagamento.setTipoPagamento(1l);
        pedido.setPagamento(pagamento);

        when(ipedidoRepository.save(any())).thenReturn(new Pedido());
        doNothing().when(produtoService).removeQtdProduto(anyList());
        when(customSequencesService.proximaSequencia("pedido")).thenReturn(1l);

        pedidoFornecedorService.salvarPedido(pedido);
    }

    @Test
    public void verificaEstoque(){

        List<Produto> produtos = new ArrayList<>();

        Produto p1 = new Produto();
        p1.setQtd(20l);

        Produto p2 = new Produto();
        p2.setQtd(20l);


        when( iprodutoRepository.findAllByIdProdutoAndIdFornecedor(anyLong(),anyLong())).thenReturn(new Produto(0l,"",1l,"",0.0,0.0,10l,"",0l));


        pedidoFornecedorService.verificaEstoque(produtos);

    }

    @Test
    public void consultaPedidoId(){

        when(ipedidoRepository.findAllByIdPedido(anyLong())).thenReturn(new Pedido());

        pedidoFornecedorService.consultaPedidoId(anyLong());

    }

}
