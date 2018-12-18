package br.com.iverdura.iverdura.controller;

import br.com.iverdura.iverdura.response.AutenticacaoResponse;
import br.com.iverdura.iverdura.service.LoginService;
import br.com.iverdura.iverdura.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public AutenticacaoResponse login(@RequestParam String email, @RequestParam String senha,@RequestParam Long tipo){
        System.out.println(email);
        return loginService.autenticaUsuarios(email, senha,tipo);

    }


}
