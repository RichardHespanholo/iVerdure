package br.com.iverdura.iverdura.controller;


import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(value = "/v1/produto")
public class ProdutoController {


    @Autowired
    private ProdutoService produtoService;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public String cadastraProduto(@Valid @RequestBody Produto produto){

        if(produtoService.buscarProduto(produto.getIdFornecedor(),produto.getIdProduto()) == null){
            produtoService.salvaProduto(produto);
            return "Produto salvo.";
        }
        return "O produto já esta cadastrado para este fornecedor.";
    }

    @ResponseBody
    @RequestMapping(value = "/listar/{idFornecedor}",method = RequestMethod.GET)
    public List<Produto> listaProdutos(@PathVariable(value = "idFornecedor") Long idFornecedor ){

        return produtoService.listaProdutos(idFornecedor);

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public List<Produto> listaTodosProdutos(){

        return produtoService.listarTodosProdutos();

    }


    @ResponseBody
    @RequestMapping(value = "/consulta/{idProduto}/{idFornecedor}",method = RequestMethod.GET)
    public Produto consultaProduto(@PathVariable(value = "idProduto") Long idProduto,@PathVariable(value = "idFornecedor") Long idFornecedor){


        return  produtoService.buscaProduto(idFornecedor,idProduto);

    }

    @ResponseBody
    @RequestMapping(value = "/remover/{idProduto}/{idFornecedor}",method = RequestMethod.GET)
    public String removerProduto(@PathVariable(value = "idProduto") Long idProduto,@PathVariable(value = "idFornecedor") Long idFornecedor){

        if(produtoService.buscaProduto(idFornecedor,idProduto) != null){
             produtoService.removerProduto(idFornecedor,idProduto);
             return "Removido com sucesso.";
        }

        return "Produto não cadastrado.";

    }


    @ResponseBody
    @RequestMapping(value = "/adiciona/quantidade",method = RequestMethod.GET)
    public String adicionaQtdProduto(@RequestParam Long idProduto,@RequestParam Long qtd,@RequestParam Long idFornecedor) {

        produtoService.adicionarQtdProduto(idProduto,qtd,idFornecedor);
        return "Quantidade de produtos adicionada no estoque com sucesso.";

    }


    @ResponseBody
    @RequestMapping(value = "/verifica/estoque",method = RequestMethod.POST)
    public String verificarEstoque(@RequestBody Produto produto) {

        produtoService.verificaEstoque(produto);
        return "Produto [ "+produto.getNome()+" ] disponivel no estoque.";

    }

}
