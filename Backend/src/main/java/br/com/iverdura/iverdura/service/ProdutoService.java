package br.com.iverdura.iverdura.service;

import br.com.iverdura.iverdura.exception.BusinessException;
import br.com.iverdura.iverdura.model.Produto;
import br.com.iverdura.iverdura.repository.IFornecedorRepository;
import br.com.iverdura.iverdura.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProdutoService {

    @Autowired
    private IProdutoRepository IProdutoRepository;

    @Autowired
    private IFornecedorRepository ifornecedorRepository;

    @Autowired
    private CustomSequencesService  customSequencesService;


    public ProdutoService(IProdutoRepository IProdutoRepository,IFornecedorRepository ifornecedorRepository,CustomSequencesService  customSequencesService){
        this.IProdutoRepository = IProdutoRepository;
        this.ifornecedorRepository = ifornecedorRepository;
        this.customSequencesService = customSequencesService;
    }


    public void salvaProduto(Produto produto){

        try {

            if(ifornecedorRepository.findAllByIdFornecedor(produto.getIdFornecedor()) == null){
                throw new BusinessException("Não existe este fornecedor cadastrado.");
            }
            produto.setIdProduto(customSequencesService.proximaSequencia("produto"));
            IProdutoRepository.save(produto);

        }catch(Exception ex){

            throw new BusinessException("Erro ao salvar produto: "+ex.getMessage(), ex);

        }

    }


    public List<Produto> listarTodosProdutos(){

        try {

           return IProdutoRepository.findAll();

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar todos produtos", ex);

        }
    }


    public Produto buscaProduto(Long idFornecedor, Long idProduto){

        try {

            Produto produto = IProdutoRepository.findAllByIdProdutoAndIdFornecedor(idProduto, idFornecedor);

            if(produto == null){
                throw new BusinessException("Produto não cadastrado.");

            }
            return produto;

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar produto: "+ex.getMessage(), ex);

        }

    }



    public Produto buscarProduto(Long idFornecedor, Long idProduto){

        try {

            Produto produto = IProdutoRepository.findAllByIdProdutoAndIdFornecedor(idProduto, idFornecedor);

            return produto;

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar produto: "+ex.getMessage(), ex);

        }

    }


    public List<Produto> listaProdutos(Long idFornecedor){

        try {

            return IProdutoRepository.findAllByIdFornecedor(idFornecedor);

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar todos os produtos", ex);

        }

    }

    public void removerProduto(Long idFornecedor,Long idProduto){

        try {

            IProdutoRepository.removeByIdFornecedorAndIdProduto(idFornecedor,idProduto);

        }catch(Exception ex){

            throw new BusinessException("Erro ao buscar todos os produtos", ex);

        }
    }


    public void adicionarQtdProduto(Long idProduto,Long qtd,Long idFornecedor ){

        try {
                Produto prod = IProdutoRepository.findAllByIdProdutoAndIdFornecedor(idProduto, idFornecedor);
                if(prod == null){
                    throw new BusinessException("Produto não cadastrado.");
                }
                Long novaQtd = prod.getQtd() + qtd;
                prod.setQtd(novaQtd);
                IProdutoRepository.save(prod);

        }catch(Exception ex){
            throw new BusinessException("Erro ao adicionar quantidade de produto no estoque: "+ ex.getMessage(), ex);
        }
    }




    public void removeQtdProduto(List<Produto> produtos){

        try {

            for (Produto produto : produtos) {

                Produto prod = IProdutoRepository.findAllByIdProdutoAndIdFornecedor(produto.getIdProduto(), produto.getIdFornecedor());
                if (prod == null) {
                    throw new BusinessException("Produto não cadastrado.");
                }
                Long qtd = prod.getQtd() - produto.getQtd();
                prod.setQtd(qtd);
                IProdutoRepository.save(prod);
            }
        }catch(Exception ex){
            throw new BusinessException("Erro ao adicionar quantidade de produto no estoque: "+ ex.getMessage(), ex);
        }
    }


    public void removeQtdProduto(Produto produto){

        try {
            Produto prod = IProdutoRepository.findAllByIdProdutoAndIdFornecedor(produto.getIdProduto(), produto.getIdFornecedor());
            if(prod == null){
                throw new BusinessException("Produto não cadastrado.");
            }

            Long qtd = prod.getQtd() - produto.getQtd();
            if(qtd < 0){
                throw new BusinessException("O produto "+ produto.getNome()+ " tem "+ prod.getQtd()+ " unidades.");
            }
            prod.setQtd(qtd);
            IProdutoRepository.save(prod);
        }catch(Exception ex){
            throw new BusinessException("Erro ao remover quantidade de produto no estoque: "+ ex.getMessage(), ex);
        }
    }

    public void  verificaEstoque(Produto produto){

        try {
            Produto prod = IProdutoRepository.findAllByIdProdutoAndIdFornecedor(produto.getIdProduto(), produto.getIdFornecedor());
            if(prod == null){
                throw new BusinessException("Produto não cadastrado.");
            }

            if(produto.getQtd() > prod.getQtd() ){

                throw new BusinessException("Quantidade disponivel no estoque: "+prod.getQtd()+" .");

            }

        }catch(Exception ex){
            throw new BusinessException("Erro ao adicionar quantidade de produto no estoque: "+ ex.getMessage(), ex);
        }
    }

}
