/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ransa.controller;

import com.ransa.domain.PedidoDocumento;
import com.ransa.domain.Inventario;
import com.ransa.service.InventarioRansa;
import com.ransa.service.UsuarioRansa;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Darien
 */
@Controller
public class PedidoController {

    /*
                para insertar pedido envio: 1
                devuelve el idPedido - codpedido
                
                usp_ins_pedidodetalle:
                   
        idPedido: el que devuelve la cabecera
        idInventario > este campo está en la grilla
        idDetalle > este campo está en la grilla
        descripcion
        cantCajas
        idUsuario
        
     */
    @RequestMapping("getDocumentos")
    public ModelAndView getDocumentos(@ModelAttribute PedidoDocumento bus, HttpSession sesion) {

        System.out.println("Entro a buscar documentos...");

        String area = bus.getArea();
        String tipoDocumento = bus.getTipoDocumento();
        String documento = bus.getNroDocumento();

        if (documento.equals("")) {
            documento = "%";
        }

        System.out.println("bus.area:" + bus.getArea());
        System.out.println("bus.tipoDocumento:" + bus.getTipoDocumento());
        System.out.println("bus.nroDocumento:" + bus.getNroDocumento());

        List<InventarioRansa> inventarios = (List<InventarioRansa>) getBusDocumentoRegistro(area, tipoDocumento, documento);
        List<Inventario> listInventario = new ArrayList<Inventario>();
        for (InventarioRansa inventario : inventarios) {
            Inventario inv = new Inventario();
            inv.setId(inventario.getIdInventario());
            inv.setIdDetalle(inventario.getIdDetalleInventario());
            inv.setNroDocumento(inventario.getNroDocumento().getValue());
            inv.setNroSerie(inventario.getNroSerie().getValue());
            inv.setObservacion(inventario.getObservacion().getValue());
            listInventario.add(inv);
        }

        sesion.setAttribute("busqueda", bus);
        sesion.setAttribute("inventarios", listInventario);

        System.out.println("inventario.size:" + listInventario.size());

        return new ModelAndView("pedido");

    }

    @RequestMapping("agregarDocumento")
    public ModelAndView agregarDocumento(HttpSession sesion, HttpServletRequest req) {

        System.out.println("Entro a agregar documento");

        UsuarioRansa usuario = (UsuarioRansa) sesion.getAttribute("usuario");

        String[] seleccionados = (String[]) req.getParameterValues("x");
        ArrayList<Inventario> listInventario = (ArrayList<Inventario>) sesion.getAttribute("inventarios");
        List<Inventario> listAgregados = new ArrayList<Inventario>();
        System.out.println("seleccionados.length:" + seleccionados.length);

        for (String seleccionado : seleccionados) {
            for (Inventario inv : listInventario) {
                if ((inv.getId() + "-" + inv.getIdDetalle()).equals(seleccionado)) {
                    System.out.println("inv.nroDocumento:" + inv.getNroDocumento());
                    listAgregados.add(inv);
                }
            }
        }

        sesion.setAttribute("agregados", listAgregados);

        return new ModelAndView("pedido");

    }

    @RequestMapping("registrarPedido")
    public @ResponseBody
    String registrarPedido(HttpSession sesion) {
        UsuarioRansa usuario = (UsuarioRansa) sesion.getAttribute("usuario");
        List<Inventario> agregados = (ArrayList<Inventario>) sesion.getAttribute("agregados");

        String result = insertarPedido(1, usuario.getIdCliente(), usuario.getIdUsuario());
        System.out.println("-->" + result.substring(result.indexOf("-") + 1, result.length()));

        int idPedido = Integer.parseInt(result.substring(result.indexOf("-") + 1, result.length()));

        System.out.println("idPedido:" + idPedido);
        System.out.println("agregados.size:" + agregados.size());

        for (Inventario agregado : agregados) {
            System.out.println("IdInventario:" + agregado.getId());
            System.out.println("IdInventarioDetalle:" + agregado.getIdDetalle());
            String mensaje = insertaPedidoDetalle(idPedido, agregado.getId(), agregado.getIdDetalle(), "", 0, usuario.getIdUsuario());
        }

        System.out.println(result.substring(0, result.indexOf("-")));

        return result.substring(0, result.indexOf("-"));

    }

    private static String insertarPedido(java.lang.Integer tipoPedido, java.lang.Integer idCliente, java.lang.Integer idUsuario) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.insertarPedido(tipoPedido, idCliente, idUsuario);
    }

    private static String insertaPedidoDetalle(java.lang.Integer idPedido, java.lang.Integer idInventario, java.lang.Integer idDetalleInv, java.lang.String desc, java.lang.Integer cantCajas, java.lang.Integer idUsuario) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.insertaPedidoDetalle(idPedido, idInventario, idDetalleInv, desc, cantCajas, idUsuario);
    }

    private static java.util.List<com.ransa.service.InventarioRansa> getBusDocumentoRegistro(java.lang.String idarea, java.lang.String idserie, java.lang.String nrodoc) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.getBusDocumentoRegistro(idarea, idserie, nrodoc);
    }

}
