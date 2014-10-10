/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ransa.controller;

import com.ransa.domain.PedidoRecojo;
import com.ransa.service.UsuarioRansa;
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
public class RecojoController {

    @RequestMapping("registrarRecojo")
    public @ResponseBody
    String registrarRecojo(@ModelAttribute PedidoRecojo recojo, HttpSession sesion) {

        System.out.println("Entro a registrar recojo");

        UsuarioRansa usuario = (UsuarioRansa) sesion.getAttribute("usuario");
        String result = insertarPedido(2, usuario.getIdCliente(), usuario.getIdUsuario());

        System.out.println("-->" + result.substring(result.indexOf("-") + 1, result.length()));
        System.out.println("-->" + result.substring(0, result.indexOf("-")));

        int idPedido = Integer.parseInt(result.substring(result.indexOf("-") + 1, result.length()));
        insertaPedidoDetalle(idPedido, 33910, 35687, recojo.getComentario(), recojo.getCantidadCajas(), usuario.getIdUsuario());

        String mensaje = result.substring(0, result.indexOf("-"));

        sesion.setAttribute("codPedido", result.substring(0, result.indexOf("-")));

        return mensaje;

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

}
