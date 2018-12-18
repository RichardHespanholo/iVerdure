package br.com.iverdura.iverdura.repository;

import br.com.iverdura.iverdura.model.PedidoFornecedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface IPedidoFornecedor extends MongoRepository<PedidoFornecedor,String> {

    List<PedidoFornecedor> findByIdFornecedor(Long idFornecedor);

    PedidoFornecedor findByIdPedido(Long idPedido);

}
