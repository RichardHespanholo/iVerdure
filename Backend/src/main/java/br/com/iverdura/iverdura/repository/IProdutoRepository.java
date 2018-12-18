package br.com.iverdura.iverdura.repository;

import br.com.iverdura.iverdura.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IProdutoRepository extends MongoRepository<Produto,Long> {

    Produto findAllByIdProdutoAndIdFornecedor(Long idProduto,Long idFornecedor);

    List<Produto>  findAllByIdFornecedor(Long idFornecedor);

    void removeByIdFornecedorAndIdProduto(Long idFornecedor,Long idProduto);




}
