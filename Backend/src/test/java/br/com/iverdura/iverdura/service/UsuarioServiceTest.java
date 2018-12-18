/*

package br.com.iverdura.iverdura.service;


import br.com.iverdura.iverdura.model.Usuario;
import br.com.iverdura.iverdura.repository.IUsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class UsuarioServiceTest {



    @Mock
    private IUsuarioRepository iusuarioRepository;


    UsuarioService  usuarioService;



    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        usuarioService = new  UsuarioService(iusuarioRepository);
    }

    @Test
    public void salvarUsuario(){

        Usuario usuario = new Usuario();

        usuario.setTipo(1l);

        when(iusuarioRepository.save(any())).thenReturn(usuario);
        usuarioService.salvarUsuario(usuario);

    }

    @Test
    public void alteraSenha(){

        Usuario usario = new Usuario();

        usario.setSenha("12345678");


        when(iusuarioRepository.findByCpf(anyString())).thenReturn(usario);


        usuarioService.alteraSenha("","432156778");


    }

    @Test
    public void autenticaUsuario(){

        when(iusuarioRepository.findAllByEmailAndSenha(anyString(),anyString())).thenReturn(new Usuario());
        usuarioService.autenticaUsuario(anyString(),anyString());

    }

    @Test
    public void buscaUsuario(){

       when(iusuarioRepository.findAllByEmailOrCpf(anyString(),anyString())).thenReturn(new Usuario());
        usuarioService.buscaUsuario(anyString(),anyString());

    }

    @Test
    public void removeUsuario(){

        when(iusuarioRepository.findByCpf(anyString())).thenReturn(new Usuario());

        doNothing().when(iusuarioRepository).removeByCpf(anyString());


        usuarioService.removeUsuario(anyString());

    }

    @Test
    public void dadosUsuario(){

        when(iusuarioRepository.findByCpf(anyString())).thenReturn(new Usuario());

        usuarioService.dadosUsuario(anyString());

    }





}

*/
