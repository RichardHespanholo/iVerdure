package br.com.iverdura.iverdura.service;

import br.com.iverdura.iverdura.enums.EnumFormaPagamento;
import br.com.iverdura.iverdura.enums.EnumFornecedor;
import br.com.iverdura.iverdura.enums.EnumPerfilUsuario;
import br.com.iverdura.iverdura.enums.EnumStatusPedido;
import br.com.iverdura.iverdura.exception.BusinessException;
import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.model.Pedido;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import br.com.iverdura.iverdura.repository.IPedidoRepository;
import br.com.iverdura.iverdura.repository.IProdutoRepository;
import br.com.iverdura.iverdura.response.EstoqueResponse;
import br.com.iverdura.iverdura.response.PedidoFornecedorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class PedidoService {

    @Autowired
    private IPedidoRepository ipedidoRepository;

    @Autowired
    private IFornecedorRepository ifornecedorRepository;

    @Autowired
    private IProdutoRepository iprodutoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CustomSequencesService customSequencesService;


    public PedidoService(IPedidoRepository ipedidoRepository,IFornecedorRepository ifornecedorRepository,IProdutoRepository iprrodutoRepository,ProdutoService produtoService,CustomSequencesService customSequencesService){
        this.ipedidoRepository = ipedidoRepository;
        this.ifornecedorRepository = ifornecedorRepository;
        this.iprodutoRepository = iprrodutoRepository;
        this.produtoService = produtoService;
        this.customSequencesService = customSequencesService;
    }

    public List<Pedido> consultaPedidos(String cpf){

        try {

            return ipedidoRepository.findAllByCpfUsuario(cpf);

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar pedidos por cfp.", ex);
        }
    }

    public List<Pedido> consultaTodosPedidos(){

        try {

            return ipedidoRepository.findAll();

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar todos pedidos.", ex);
        }

    }

    public void updateStatus(String status,Long idPedido) {

        try{

            if (EnumStatusPedido.parse(status.toUpperCase()) == null) {
                throw new BusinessException("Status cadastrados [PENDENTE,ENTREGUE].");
            }
            Pedido pedido = ipedidoRepository.findAllByIdPedido(idPedido);
            if(pedido == null){
                throw new BusinessException("Pedido não encontrado.");
            }
            pedido.setStatus(status.toUpperCase());
            ipedidoRepository.save(pedido);

        }catch(Exception ex){

            throw new BusinessException("Erro ao atualizar status : "+ ex.getMessage() , ex);

        }

    }


    public void salvarPedido(Pedido pedido){

        try {

           // if(EnumFormaPagamento.parse(pedido.getPagamento().getTipoPagamento()) == null){
           //     throw new BusinessException("Forma de pagamento inválida.");
           // }
            pedido.setStatus("PENDENTE");
            pedido.setIdPedido(customSequencesService.proximaSequencia("pedido"));

            produtoService.removeQtdProduto(pedido.getProdutos());
            ipedidoRepository.save(pedido);

        }catch(Exception ex){

            throw new BusinessException("Erro ao salvar pedido: "+ ex.getMessage(), ex);
        }
    }

    public List<EstoqueResponse> verificaEstoque(List<Produto> produtos){

    List<EstoqueResponse> estoqueResponseList = new ArrayList<>();

    try {
        for (Produto p : produtos) {

            EstoqueResponse estoqueResponse = new EstoqueResponse();

            Produto produto = iprodutoRepository.findAllByIdProdutoAndIdFornecedor(p.getIdProduto(), p.getIdFornecedor());

            if(produto == null){
                throw new BusinessException("O produto "+p.getNome()  +" não esta cadastrado.");
            }

            if (produto.getQtd() < p.getQtd()) {
                estoqueResponse.setIdFornecedor(produto.getIdFornecedor());
                estoqueResponse.setIdProduto(produto.getIdProduto());
                estoqueResponse.setNomeProduto(produto.getNome());
                estoqueResponseList.add(estoqueResponse);
            }

        }

        return estoqueResponseList;
    }catch(Exception ex){
        throw new BusinessException("Erro ao verificar estoque: "+ex.getMessage(), ex);
    }


}




    public Pedido consultaPedidoId(Long idPedido){

        try {

            Pedido pedido = ipedidoRepository.findAllByIdPedido(idPedido);

            if(pedido == null){
                throw new BusinessException("Pedido não encontrado.");
            }

            return pedido;
        }catch(Exception ex){

            throw new BusinessException("Erro consultar pedido: "+ ex.getMessage(), ex);
        }
    }




    public List<PedidoFornecedorResponse> verificaPedidoMinimo(List<Produto> produtos){

        Set<Long> fornecedores = fornecedoresPedido(produtos);
        List<PedidoFornecedorResponse> pedidoFornecedorList = new ArrayList<>();


        try {

            fornecedores.forEach(elem -> {

                PedidoFornecedorResponse pedidoFornecedorResponse = new PedidoFornecedorResponse();

                Double total = produtos.stream().filter(p -> p.getIdFornecedor() == elem.longValue()
                ).collect(Collectors.toList()).stream().mapToDouble(Produto::getPreco).sum();

                Fornecedor fornecedor = ifornecedorRepository.findAllByIdFornecedor(elem.longValue());

                if(fornecedor == null){
                   throw  new BusinessException("O fornecedor de codigo: "+ elem.longValue()+ " não esta cadastrado.");
                }

                pedidoFornecedorResponse.setIdFornecedor(elem.longValue());
                pedidoFornecedorResponse.setPedidoMinimo(fornecedor.getPedidoMinimo());
                pedidoFornecedorResponse.setNomeFantasia(fornecedor.getNomeFantasia());
                pedidoFornecedorResponse.setTotalPedido(total);

                pedidoFornecedorList.add(pedidoFornecedorResponse);

            });

        }catch(Exception ex){

            throw new BusinessException("Erro ao varificar pedido minimo: "+ ex.getMessage() , ex);
        }
        return pedidoFornecedorList;
    }


    private Set<Long> fornecedoresPedido(List<Produto> produtos){

        Set<Long> fornecedores = new HashSet<>();

        for (Produto p: produtos) {

            fornecedores.add(p.getIdFornecedor());
        }

        return fornecedores;
    }



}
