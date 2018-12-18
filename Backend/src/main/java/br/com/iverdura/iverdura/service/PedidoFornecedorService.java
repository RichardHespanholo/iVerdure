package br.com.iverdura.iverdura.service;

import br.com.iverdura.iverdura.enums.EnumStatusPedido;
import br.com.iverdura.iverdura.exception.BusinessException;
import br.com.iverdura.iverdura.model.Pedido;
import br.com.iverdura.iverdura.model.PedidoFornecedor;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.repository.ICustomSequenceRepository;
import br.com.iverdura.iverdura.repository.IPedidoFornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class PedidoFornecedorService {

    @Autowired
    IPedidoFornecedor iPedidoFornecedor;

    @Autowired
    ICustomSequenceRepository iCustomSequenceRepository;

    @Autowired
    CustomSequencesService customSequencesService;

    public PedidoFornecedorService(IPedidoFornecedor iPedidoFornecedor, ICustomSequenceRepository iCustomSequenceRepository, CustomSequencesService customSequencesService){
        this.iPedidoFornecedor = iPedidoFornecedor;
        this.iCustomSequenceRepository =  iCustomSequenceRepository;
        this.customSequencesService = customSequencesService;
    }

    public void montaPedidoFornecedor(Pedido pedido){

        Set<Long> fornecedores = fornecedoresPedido(pedido.getProdutos());
        List<PedidoFornecedor> pedidoFornecedorList = new ArrayList<>();
        PedidoFornecedor pedidoFornecedor;

        try {
            for (Long fornecedor : fornecedores) {
                pedidoFornecedor = new PedidoFornecedor();

                pedidoFornecedor.setCpfUsuario(pedido.getCpfUsuario());
                pedidoFornecedor.setDataEntrega(pedido.getDataEntrega());
                pedidoFornecedor.setEndereco(pedido.getEndereco());
                pedidoFornecedor.setDataPedido(pedido.getDataPedido());
                pedidoFornecedor.setIdFornecedor(fornecedor.longValue());
                pedidoFornecedor.setPagamento(pedido.getPagamento());
                pedidoFornecedor.setStatus(pedido.getStatus());
                pedidoFornecedor.setNomeCliente(pedido.getNomeCliente());
                pedidoFornecedor.setValorEntrega(pedido.getValorEntrega());
                pedidoFornecedor.setValorTotal(pedido.getProdutos().stream().filter(obj -> fornecedor.longValue() == obj.getIdFornecedor()).collect(Collectors.toList()).stream().mapToDouble(Produto::getPreco).sum());
                pedidoFornecedor.setProdutos(pedido.getProdutos().stream().filter(obj -> fornecedor.longValue() == obj.getIdFornecedor()).collect(Collectors.toList()));
                pedidoFornecedor.setIdPedido(customSequencesService.proximaSequencia("pedido_fornecedor"));
                pedidoFornecedorList.add(pedidoFornecedor);
            }
            iPedidoFornecedor.saveAll(pedidoFornecedorList);
        }catch(Exception ex){
            throw new BusinessException("Erro ao montar pedidos para o fornecedor: "+ ex.getMessage() , ex);
        }
    }


    private Set<Long> fornecedoresPedido(List<Produto> produtos){
        Set<Long> fornecedores = new HashSet<>();

        for (Produto p: produtos) {
            fornecedores.add(p.getIdFornecedor());
        }

        return fornecedores;
    }


    public List<PedidoFornecedor> consultaPedidoFornecedorIdFornecedor(Long idFornecedor){

        try {
            return iPedidoFornecedor.findByIdFornecedor(idFornecedor);
        }catch (Exception ex){
            throw new BusinessException("Erro ao consultar pedidos de um fornecedor.", ex);

        }
    }



    public PedidoFornecedor consultaPedidoFornecedorId(Long idPedido){

        try {
            PedidoFornecedor   pedidoFornecedor = iPedidoFornecedor.findByIdPedido(idPedido);

             if(pedidoFornecedor == null){
                 throw new BusinessException("Pedido não encontrado.");
             }

             return pedidoFornecedor;
        }catch (Exception ex){
            throw new BusinessException("Erro ao consultar pedido: "+ ex.getMessage(), ex);

        }
    }


    public void atualizaStatus(Long idPedido){

        try {
            PedidoFornecedor   pedidoFornecedor =  iPedidoFornecedor.findByIdPedido(idPedido);
            pedidoFornecedor.setStatus(EnumStatusPedido.ENTREGUE.getStatus());
            iPedidoFornecedor.save(pedidoFornecedor);
        }catch (Exception ex){
            throw new BusinessException("Erro ao atualizar pedido: "+ ex.getMessage(), ex);

        }
    }

}
