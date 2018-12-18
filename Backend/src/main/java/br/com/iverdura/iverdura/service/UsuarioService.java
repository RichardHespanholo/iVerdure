package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.enums.EnumPerfilUsuario;
import br.com.iverdura.iverdura.exception.BusinessException;
import br.com.iverdura.iverdura.model.Endereco;
import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.model.Usuario;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import br.com.iverdura.iverdura.repository.IUsuarioRepository;
import br.com.iverdura.iverdura.response.AutenticacaoResponse;
import br.com.iverdura.iverdura.response.DadosUsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iusuarioRepository;

    @Autowired
    public IFornecedorRepository ifornecedorRepository;

    @Autowired
    CustomSequencesService customSequencesService;

    public UsuarioService(CustomSequencesService customSequencesService, IUsuarioRepository iusuarioRepository, IFornecedorRepository ifornecedorRepository){
        this.iusuarioRepository = iusuarioRepository;
        this.ifornecedorRepository = ifornecedorRepository;
        this.customSequencesService = customSequencesService;
    }


    private void verificaSenha(String novaSenha){
        try {
            if (novaSenha.length() < 8 || novaSenha.length() > 15) {

                throw new BusinessException("A senha deve conter de 8 a 15 caracteres");
            }
        }catch(Exception ex){
            throw new BusinessException(ex.getMessage(), ex);
        }
    }



    public void salvarUsuario(Usuario usuario) {

        try {
            Long tipo = Long.valueOf(usuario.getTipo());

            if(tipo != EnumPerfilUsuario.CLIENTE.getId()){
                throw new BusinessException("Perfil não encontrado");
            }
            usuario.setIdUsuario(customSequencesService.proximaSequencia("usuario"));
            iusuarioRepository.save(usuario);

        }catch (Exception ex){
            throw new BusinessException("Erro ao salvar usuário: "+ex.getMessage(), ex);
        }
    }

    public void alteraSenha(String cpf,String novaSenha){
        try{

            Usuario user = iusuarioRepository.findByCpf(cpf);
            verificaSenha(novaSenha);
            if(user == null){
                throw new BusinessException("Usuário não encontrado.");
            }
            user.setSenha(novaSenha);
            iusuarioRepository.save(user);
        }catch(Exception ex){
            throw new BusinessException("Erro ao atualizar senha: "+ex.getMessage(), ex);
        }

    }


    public AutenticacaoResponse autenticaUsuarioComum(String email, String senha){

        try {

            AutenticacaoResponse autenticacaoResponse=null;

            Usuario user = iusuarioRepository.findAllByEmailAndSenha(email, senha);

            if (user != null) {
                autenticacaoResponse = new AutenticacaoResponse();
                autenticacaoResponse.setNome(user.getNome());
                autenticacaoResponse.setTipo(EnumPerfilUsuario.CLIENTE.getId());
                autenticacaoResponse.setId(user.getIdUsuario());
                autenticacaoResponse.setEmail(user.getEmail());
                autenticacaoResponse.setCpf(user.getCpf());
            }
            if (autenticacaoResponse == null) {
                throw new BusinessException("Usuário não cadastrado.");
            }
            return autenticacaoResponse;
        }catch(Exception ex){
            throw new BusinessException(ex.getMessage(), ex);

        }
    }



    public Usuario buscaUsuario(String email,String cpf){

        try {
            return iusuarioRepository.findAllByEmailOrCpf(email,cpf);
        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar usuario", ex);
        }
    }

    public void removeUsuario(String cpf){

        try {

            if(iusuarioRepository.findByCpf(cpf) == null){
                throw new BusinessException("Usuário não cadastrado.");
            }
             iusuarioRepository.removeByCpf(cpf);
        }catch(Exception ex){

            throw new BusinessException("Erro ao remover usuario: " + ex.getMessage(), ex);
        }
    }


    public DadosUsuarioResponse dadosUsuario(String cpf){

        try{

            Usuario user =   iusuarioRepository.findByCpf(cpf);

            if(user == null){
                throw new BusinessException("Usuário não cadastrado.");
            }
            DadosUsuarioResponse dadosUsuarioResponse = new DadosUsuarioResponse();
            Endereco endereco = new Endereco();

            dadosUsuarioResponse.setEndereco(user.getEndereco());
            dadosUsuarioResponse.setNome(user.getNome());
            dadosUsuarioResponse.setSobrenome(user.getSobrenome());
            dadosUsuarioResponse.setTelefone(user.getTelefone());

            return dadosUsuarioResponse;


        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar dados do usuário: "+ex.getMessage(), ex);

        }


    }


}
