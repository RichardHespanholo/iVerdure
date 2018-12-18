package br.com.iverdura.iverdura.repository;

import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFornecedorRepository extends MongoRepository<Fornecedor,String> {

    Fornecedor findAllByIdFornecedor(Long idFornecedor);

    void removeByIdFornecedor(Long idFornecedor);

    Fornecedor findAllByEmailAndSenha(String email, String senha);

    Fornecedor  findAllByEmailOrCnpj(String email,String cnpj);

   // Fornecedor findAllByEmailAndSenha(String email, String senha);

}
