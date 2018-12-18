package br.com.iverdura.iverdura.controller;


import br.com.iverdura.iverdura.model.Pedido;
import br.com.iverdura.iverdura.model.PedidoFornecedor;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.response.EstoqueResponse;
import br.com.iverdura.iverdura.response.FecharPedidoResponse;
import br.com.iverdura.iverdura.response.PedidoFornecedorResponse;
import br.com.iverdura.iverdura.service.PedidoFornecedorService;
import br.com.iverdura.iverdura.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = "/v1/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoFornecedorService pedidoFornecedorService;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public FecharPedidoResponse fechaPedido(@Valid @RequestBody Pedido pedido){

        List<EstoqueResponse> list = pedidoService.verificaEstoque(pedido.getProdutos());
        FecharPedidoResponse fecharPedidoResponse = new FecharPedidoResponse();
        fecharPedidoResponse.setProdutosIndisponiveis(list);
        if( list.size() == 0){
            pedidoService.salvarPedido(pedido);
            pedidoFornecedorService.montaPedidoFornecedor(pedido);
            fecharPedidoResponse.setMsg("Pedido salvo com sucesso.");
            return fecharPedidoResponse;
        }
        fecharPedidoResponse.setMsg("Existe alguns produtos indisponiveis no estoque.");
        return fecharPedidoResponse;
    }
    

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/minimo/fornecedores", method = RequestMethod.POST)
    public List<PedidoFornecedorResponse> pedidoMinimo(@Valid @RequestBody List<Produto> produtos){

        return pedidoService.verificaPedidoMinimo(produtos);

    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consulta/cpf/{cpf}", method = RequestMethod.GET)
    public List<Pedido> consultarPedidoCpf(@PathVariable(value = "cpf")  String cpf){

        return pedidoService.consultaPedidos(cpf);

    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consulta/id/{idPedido}", method = RequestMethod.GET)
    public Pedido consultarPedidoId(@PathVariable(value = "idPedido")  Long idPedido){

        return pedidoService.consultaPedidoId(idPedido);

    }



    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public List<Pedido> listarPedidos(){

        return pedidoService.consultaTodosPedidos();

    }

    @ResponseBody
    @RequestMapping(value = "/update/status/{status}/{idPedido}",method = RequestMethod.GET)
    public String updateStatusPedido(@PathVariable(value = "status") String status,@PathVariable("idPedido") Long idPedido){

         pedidoService.updateStatus(status,idPedido);
         return "Pedido atualizado.";

    }







}
