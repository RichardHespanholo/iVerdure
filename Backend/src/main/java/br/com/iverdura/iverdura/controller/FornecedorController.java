package br.com.iverdura.iverdura.controller;


import br.com.iverdura.iverdura.model.Fornecedor;
import br.com.iverdura.iverdura.response.FornecedorResponse;
import br.com.iverdura.iverdura.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<FornecedorResponse> consultarFornecedores(){

       return  fornecedorService.consultaFornecedores();

    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    private String cadastrarFornecedor(@Valid @RequestBody Fornecedor fornecedor){

        if(fornecedorService.buscaFornecedor(fornecedor.getEmail(),fornecedor.getCnpj()) == null){
            fornecedorService.salvarFornecedor(fornecedor);
            return "Salvo com sucesso.";
        }

        return "Fornecedor já existe cadastrado";
    }


    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/remover", method = RequestMethod.GET)
    public String removerFornecedor(@RequestParam Long idFornecedor){

        fornecedorService.consultaFornecedor(idFornecedor);
        fornecedorService.removerFornecedor(idFornecedor);

        return "Fornecedor removido com sucesso.";
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/consulta", method = RequestMethod.GET)
    public FornecedorResponse consultaFornecedor(@RequestParam Long idFornecedor){

        return fornecedorService.consultaFornecedor(idFornecedor);


    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/remover",method = {RequestMethod.POST})
    public String removerUsuario(@RequestParam Long idFornecedor){

        fornecedorService.removeFornecedor(idFornecedor);
        return "Fornecedor removido com sucesso!";
    }


}
