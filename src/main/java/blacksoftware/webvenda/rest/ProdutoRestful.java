/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.rest;

import blacksoftware.webvenda.model.Fornecedor;
import blacksoftware.webvenda.model.Grupo;
import blacksoftware.webvenda.model.Produto;
import blacksoftware.webvenda.model.Estoque;
import blacksoftware.webvenda.model.Unidade;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Avell G1511
 */
@Path("produto")
public class ProdutoRestful {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        List<Produto> produtoList = new ArrayList<>();
        Produto produto = new Produto();
        produto.setCodigo("00001");
        produto.setFornecedor(new Fornecedor(1l, "001", "INDAIA"));
        produto.setGrupo(new Grupo(1L, "AGUA"));
        produto.setNome("GARRAFA DE AGUA 20 LT");
        produto.getEstoques().add(new Estoque(1L, produto, new Unidade(1L, "CXA", BigDecimal.ONE, BigDecimal.TEN), 100));
        produto.getEstoques().add(new Estoque(1L, produto, new Unidade(1L, "UNI", BigDecimal.ONE, BigDecimal.TEN), 100));
        Produto produto1 = new Produto();
        produto1.setCodigo("00001");
        produto1.setFornecedor(new Fornecedor(1l, "001", "INDAIA"));
        produto1.setGrupo(new Grupo(1L, "AGUA"));
        produto1.setNome("GARRAFA DE AGUA 20 LT");
        produto1.getEstoques().add(new Estoque(1L, produto, new Unidade(1L, "CXA", BigDecimal.ONE, BigDecimal.TEN), 100));
        produto1.getEstoques().add(new Estoque(1L, produto, new Unidade(1L, "UNI", BigDecimal.ONE, BigDecimal.TEN), 100));
        produtoList.add(produto);
        produtoList.add(produto1);
        return Response.ok(new GenericEntity<>(produtoList, new Type() {
        })).build();
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto get() {
        Produto produto = new Produto();
        produto.setCodigo("00001");
        produto.setFornecedor(new Fornecedor(1l, "001", "INDAIA"));
        produto.setGrupo(new Grupo(1L, "AGUA"));
        produto.setNome("GARRAFA DE AGUA 20 LT");
        produto.getEstoques().add(new Estoque(1L, produto, new Unidade(1L, "CXA", BigDecimal.ONE, BigDecimal.TEN), 100));
        produto.getEstoques().add(new Estoque(1L, produto, new Unidade(1L, "UNI", BigDecimal.ONE, BigDecimal.TEN), 100));
        return produto;
    }
}
