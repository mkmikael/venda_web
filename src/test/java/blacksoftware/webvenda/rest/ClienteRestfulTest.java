package blacksoftware.webvenda.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import blacksoftware.webvenda.model.Cliente;
import blacksoftware.webvenda.model.Pedido;
import blacksoftware.webvenda.model.Ramo;
import blacksoftware.webvenda.model.enums.Situacao;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Avell G1511
 */
public class ClienteRestfulTest {

    public ClienteRestfulTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMarshaller() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Cliente.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
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
        marshaller.marshal(cliente, System.out);
    }

    @Test
    public void testUnmarshaller() throws Exception {
        String xml = "<cliente>\n"
                + "    <bairro>Centro</bairro>\n"
                + "    <cidade>Barcarena</cidade>\n"
                + "    <cnpj>123123123213</cnpj>\n"
                + "    <codigo>000001</codigo>\n"
                + "    <endereco>Rua Laurival Cunha</endereco>\n"
                + "    <inscricaoEstadual>6541256</inscricaoEstadual>\n"
                + "    <limite>1000</limite>\n"
                + "    <nomeFantasia>BalckSoftware LTDA</nomeFantasia>\n"
                + "    <pedidos>\n"
                + "        <pedido>\n"
                + "            <codigo>00000000001</codigo>\n"
                + "            <dataCriacao>1439618223107</dataCriacao>\n"
                + "            <dataFaturamento>1439618223107</dataFaturamento>\n"
                + "            <id>1</id>\n"
                + "            <total>10</total>\n"
                + "        </pedido>\n"
                + "    </pedidos>\n"
                + "    <ramo>\n"
                + "        <codigo>002</codigo>\n"
                + "        <id>1</id>\n"
                + "        <nome>VAREJO</nome>\n"
                + "    </ramo>\n"
                + "    <rate>0</rate>\n"
                + "    <situacao>EM_DIA</situacao>\n"
                + "</cliente>";
        JAXBContext context = JAXBContext.newInstance(Cliente.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Cliente cliente = (Cliente) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println(cliente.getPedidos().get(0).getDataCriacao());
    }
}
