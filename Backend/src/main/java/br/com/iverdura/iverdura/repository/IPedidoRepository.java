package br.com.iverdura.iverdura.repository;

import br.com.iverdura.iverdura.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IPedidoRepository extends MongoRepository<Pedido,String> {

    List<Pedido> findAllByCpfUsuario(String cpf);

    Pedido findAllByIdPedido(Long idPedido);



}
