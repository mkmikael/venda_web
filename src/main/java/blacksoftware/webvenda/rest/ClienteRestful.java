/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blacksoftware.webvenda.rest;

import blacksoftware.webvenda.model.Cliente;
import blacksoftware.webvenda.model.Pedido;
import blacksoftware.webvenda.model.Ramo;
import blacksoftware.webvenda.model.enums.Situacao;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Avell G1511
 */
@Path("cliente")
public class ClienteRestful {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente test() {
        Cliente cliente = new Cliente();
        cliente.setNomeFantasia("BalckSoftware LTDA");
        cliente.setCnpj("123123123213");
        cliente.setBairro("Centro");
        cliente.setCidade("Barcarena");
        cliente.setCodigo("000001");
        cliente.setEndereco("Rua Laurival Cunha");
        cliente.setInscricaoEstadual("6541256");
        cliente.setLimite(new BigDecimal(1000));
        cliente.setRamo(new Ramo(1L, "002", "VAREJO"));
        cliente.setSituacao(Situacao.EM_DIA);
        cliente.getPedidos().add(new Pedido(1L, "00000000001", new Date(), new Date(), BigDecimal.TEN, cliente));
        return cliente;
    }
}
