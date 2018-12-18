package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.enums.EnumPerfilUsuario;
import br.com.iverdura.iverdura.exception.BusinessException;
import br.com.iverdura.iverdura.response.AutenticacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {


    @Autowired
    FornecedorService fornecedorService;

    @Autowired
    UsuarioService usuarioService;

    public LoginService(FornecedorService fornecedorService){
        this.usuarioService = usuarioService;

    }


    public AutenticacaoResponse autenticaUsuarios(String email, String senha, Long tipo) {

        AutenticacaoResponse autenticacaoResponse = null;

        try {

            if(EnumPerfilUsuario.parse(tipo) == null) throw new BusinessException("Tipo de pefil nao cadastrado.");

            if(tipo == EnumPerfilUsuario.FORNECEDOR.getId()) autenticacaoResponse = fornecedorService.autenticaUsuarioFornecedor(email,senha);

            if(tipo == EnumPerfilUsuario.CLIENTE.getId()) autenticacaoResponse = usuarioService.autenticaUsuarioComum(email,senha);

            return autenticacaoResponse;
        }catch (Exception ex){
            throw new BusinessException("Erro ao fazer  Login: "+ex.getMessage(), ex);
        }
    }


}
