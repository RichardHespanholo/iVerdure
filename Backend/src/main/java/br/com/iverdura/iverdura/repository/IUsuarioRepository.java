package br.com.iverdura.iverdura.repository;

import br.com.iverdura.iverdura.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUsuarioRepository extends MongoRepository<Usuario,String> {

     Usuario findAllByEmailAndSenha(String email, String senha);
     Usuario findAllByEmailOrCpf(String email,String senha);
     Usuario findByCpf(String cpf);
     void removeByCpf(String cpf);

}
