package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.enums.EnumPerfilUsuario;
import br.com.iverdura.iverdura.exception.BusinessException;
import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.model.Usuario;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import br.com.iverdura.iverdura.response.AutenticacaoResponse;
import br.com.iverdura.iverdura.response.FornecedorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FornecedorService {

   @Autowired
   public IFornecedorRepository ifornecedorRepository;

    @Autowired
    CustomSequencesService customSequencesService;

    public FornecedorService(IFornecedorRepository ifornecedorRepository,CustomSequencesService customSequencesService) {
        this.ifornecedorRepository = ifornecedorRepository;
        this.customSequencesService = customSequencesService;
    }

    public List<FornecedorResponse> consultaFornecedores(){

        try {

            FornecedorResponse fornecedorResponse;
            List<FornecedorResponse>  listFornecedoresResponse = new ArrayList<>();
            List<Fornecedor>  fornecedores = ifornecedorRepository.findAll();

            for (Fornecedor fornecedor: fornecedores ) {

                fornecedorResponse = new FornecedorResponse();
                fornecedorResponse.setEmail(fornecedor.getEmail());
                fornecedorResponse.setEndereco(fornecedor.getEndereco());
                fornecedorResponse.setIdFornecedor(fornecedor.getIdFornecedor());
                fornecedorResponse.setEndereco(fornecedor.getEndereco());
                fornecedorResponse.setNomeFantasia(fornecedor.getNomeFantasia());
                fornecedorResponse.setPedidoMinimo(fornecedor.getPedidoMinimo());
                fornecedorResponse.setRazaoSocial(fornecedor.getRazaoSocial());
                fornecedorResponse.setTelefone(fornecedor.getTelefone());
                fornecedorResponse.setImg(fornecedor.getImg());

                listFornecedoresResponse.add(fornecedorResponse);
            }
           return listFornecedoresResponse;

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar fornecedores.", ex);
        }

    }

    public void salvarFornecedor(Fornecedor fornecedor){

        try {
            fornecedor.setIdFornecedor(customSequencesService.proximaSequencia("fornecedor"));
            fornecedor.setTipo(EnumPerfilUsuario.FORNECEDOR.getId());
            ifornecedorRepository.save(fornecedor);

        }catch(Exception ex){

            throw new BusinessException("Erro ao salvar fornecedores.", ex);
        }

    }


   public Fornecedor buscaFornecedor(String email,String cnpj){

           try {
               return ifornecedorRepository.findAllByEmailOrCnpj(email,cnpj);
           }catch(Exception ex){

               throw new BusinessException("Erro ao buscar fornecedor", ex);
           }
    }


    public FornecedorResponse consultaFornecedor(Long idFornecedor){

        try {

            FornecedorResponse fornecedorResponse = new FornecedorResponse();
            Fornecedor fornecedor = ifornecedorRepository.findAllByIdFornecedor(idFornecedor);


            if(fornecedor == null){
                  throw new  BusinessException("Fornecedor não econtrado");
            }

            fornecedorResponse.setEmail(fornecedor.getEmail());
            fornecedorResponse.setEndereco(fornecedor.getEndereco());
            fornecedorResponse.setIdFornecedor(fornecedor.getIdFornecedor());
            fornecedorResponse.setEndereco(fornecedor.getEndereco());
            fornecedorResponse.setNomeFantasia(fornecedor.getNomeFantasia());
            fornecedorResponse.setPedidoMinimo(fornecedor.getPedidoMinimo());
            fornecedorResponse.setRazaoSocial(fornecedor.getRazaoSocial());
            fornecedorResponse.setTelefone(fornecedor.getTelefone());
            fornecedorResponse.setImg(fornecedor.getImg());

            return fornecedorResponse;

        }catch(Exception ex){

            throw new BusinessException("Erro ao consultar fornecedores: "+ex.getMessage(), ex);
        }

    }

    public void removerFornecedor(Long idFornecedor){
        try {


            ifornecedorRepository.removeByIdFornecedor(idFornecedor);

        }catch(Exception ex){

            throw new BusinessException("Erro remover o fornecedor.", ex);

        }
    }


    public AutenticacaoResponse autenticaUsuarioFornecedor(String email, String senha){

        try {

            AutenticacaoResponse autenticacaoResponse=null;

            Fornecedor user = ifornecedorRepository.findAllByEmailAndSenha(email, senha);

            if (user != null) {
                autenticacaoResponse = new AutenticacaoResponse();
                autenticacaoResponse.setNome(user.getNomeFantasia());
                autenticacaoResponse.setTipo(EnumPerfilUsuario.FORNECEDOR.getId());
                autenticacaoResponse.setId(user.getIdFornecedor());
                autenticacaoResponse.setEmail(user.getEmail());
            }
            if (autenticacaoResponse == null) {
                throw new BusinessException("Usuário Fornecedor não cadastrado.");
            }
            return autenticacaoResponse;
        }catch(Exception ex){
            throw new BusinessException(ex.getMessage(), ex);

        }

    }


    public void removeFornecedor(Long idFornecedor){

        try {

            if(ifornecedorRepository.findAllByIdFornecedor(idFornecedor) == null){
                throw new BusinessException("Usuário não cadastrado.");
            }
            ifornecedorRepository.removeByIdFornecedor(idFornecedor);
        }catch(Exception ex){

            throw new BusinessException("Erro ao remover usuario: " + ex.getMessage(), ex);
        }
    }


}
