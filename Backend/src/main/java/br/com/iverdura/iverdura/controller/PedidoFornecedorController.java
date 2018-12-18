package br.com.iverdura.iverdura.controller;


import br.com.iverdura.iverdura.model.Pedido;
import br.com.iverdura.iverdura.model.PedidoFornecedor;
import br.com.iverdura.iverdura.service.PedidoFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1/pedido_fornecedor")
public class PedidoFornecedorController {

    @Autowired
    PedidoFornecedorService pedidoFornecedorService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consulta/idFornecedor/{idFornecedor}", method = RequestMethod.GET)
    public List<PedidoFornecedor> consultarPedidosFornecedor(@PathVariable(value = "idFornecedor")  Long idFornecedor){

        return pedidoFornecedorService.consultaPedidoFornecedorIdFornecedor(idFornecedor);


    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/consulta/idPedido/{idPedido}", method = RequestMethod.GET)
    public PedidoFornecedor consultarPedidosFornecedorId(@PathVariable(value = "idPedido")  Long idPedido){

        return pedidoFornecedorService.consultaPedidoFornecedorId(idPedido);

    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/atualiza/status", method = RequestMethod.GET)
    public String atualizaStatusPedido(@RequestParam  Long idPedido){

         pedidoFornecedorService.atualizaStatus(idPedido);

        return "Pedido atualizado.";
    }


}


