package br.com.iverdura.iverdura.controller;

import br.com.iverdura.iverdura.model.Usuario;
import br.com.iverdura.iverdura.response.AutenticacaoResponse;
import br.com.iverdura.iverdura.response.DadosUsuarioResponse;
import br.com.iverdura.iverdura.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/cadastrar",
            method = {RequestMethod.POST})
    public String cadastrar(@Valid  @RequestBody Usuario usuario){

         if(usuarioService.buscaUsuario(usuario.getEmail(),usuario.getCpf()) == null) {
             usuarioService.salvarUsuario(usuario);
             return "Usuário Salvo!";
         }

        return "Usuário já esta cadastrado.";
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/atualiza",
            method = {RequestMethod.POST})
    public String atualizaUsuario(@Valid  @RequestBody Usuario usuario){

        if(usuarioService.buscaUsuario(usuario.getEmail(),usuario.getCpf()) != null) {
            usuarioService.salvarUsuario(usuario);
            return "Usuário atualizado.";
        }

        return "Usuário não cadastrado.";
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/remover",method = {RequestMethod.POST})
    public String removerUsuario(@RequestParam String cpf){

         usuarioService.removeUsuario(cpf);
         return "Usuario removido com sucesso!";
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/atualiza/senha",method = {RequestMethod.POST})
    public String alteraSenha(@RequestParam String cpf, @RequestParam String novaSenha){

        usuarioService.alteraSenha(cpf, novaSenha);
        return "Senha atualizada.";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consultaDados",method = {RequestMethod.GET})
    public DadosUsuarioResponse alteraSenha(@RequestParam String cpf){
        return usuarioService.dadosUsuario(cpf);

    }






}
