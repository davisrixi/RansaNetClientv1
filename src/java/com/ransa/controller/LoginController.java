/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ransa.controller;

import com.ransa.domain.Area;
import com.ransa.domain.TipoDocumento;
import com.ransa.domain.Usuario;
import com.ransa.service.AreaRansa;
import com.ransa.service.SerieRansa;
import com.ransa.service.UsuarioRansa;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author NP375105
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public ModelAndView getLogin(@ModelAttribute Usuario usuario) {
        return new ModelAndView("login");
    }

    @RequestMapping("acceder")
    public ModelAndView acceder(@ModelAttribute Usuario usuario, HttpSession sesion) {

        boolean result = false;
        result = iniciarSesion(usuario.getUsuario(), usuario.getPassword());

        if (result) {

            System.out.println("--> Entró");
            UsuarioRansa user = (UsuarioRansa) datosUsuario(usuario.getUsuario(), usuario.getPassword(), "C");
            sesion.setAttribute("usuario", user);
            sesion.setAttribute("nombre", user.getNombresUsuario().getValue() + " " + user.getApellidosUsuario().getValue());

            return new ModelAndView("home");
        } else {
            return new ModelAndView("login");
        }
    }

    @RequestMapping("pedido")
    public ModelAndView accederPedido(HttpSession sesion) {

        UsuarioRansa usuario = (UsuarioRansa) sesion.getAttribute("usuario");
        System.out.println("pedido cliente: " + usuario.getIdCliente());
        //Cargar áreas
        List<Area> areas = cargarAreas(usuario);
        sesion.setAttribute("areas", areas);

        //Cargar tipos de documentos
        List<TipoDocumento> tiposDocumento = cargarTiposDocumentos();
        System.out.println("tiposDocumento: " + tiposDocumento.size());
        sesion.setAttribute("tiposDocumento", tiposDocumento);

        return new ModelAndView("pedido");
    }

    @RequestMapping("recojo")
    public ModelAndView accederRecojo(HttpSession sesion) {

        UsuarioRansa usuario = (UsuarioRansa) sesion.getAttribute("usuario");
        System.out.println("recojo cliente: " + usuario.getIdCliente());
        //Cargar áreas
        List<Area> areas = cargarAreas(usuario);
        sesion.setAttribute("areas", areas);

        return new ModelAndView("recojo");
    }

    private List<TipoDocumento> cargarTiposDocumentos() {

        List<SerieRansa> tiposDocumento = (List<SerieRansa>) getTipoDocumento();
        List<TipoDocumento> listTiposDocumento = new ArrayList<TipoDocumento>();

        for (SerieRansa s : tiposDocumento) {
            TipoDocumento documento = new TipoDocumento();
            documento.setCodigo(s.getIdSerie());
            documento.setDescripcion(s.getNomSerie().getValue());
            listTiposDocumento.add(documento);
        }

        return listTiposDocumento;
    }

    private List<Area> cargarAreas(UsuarioRansa usuario) {

        List<AreaRansa> areas = (List<AreaRansa>) getListaArea(usuario.getIdCliente());
        List<Area> listAreas = new ArrayList<Area>();

        for (AreaRansa a : areas) {
            Area area = new Area();
            area.setIdArea(a.getIdArea());
            area.setIdCliente(a.getIdCliente());
            area.setNombre(a.getNomArea().getValue());
            listAreas.add(area);
        }

        return listAreas;
    }

    private static Boolean iniciarSesion(java.lang.String usuario, java.lang.String clave) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.iniciarSesion(usuario, clave);
    }

    private static java.util.List<java.lang.Object> getComboArea(java.lang.String tabla, java.lang.Integer filtro) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.getComboArea(tabla, filtro);
    }

    private static UsuarioRansa datosUsuario(java.lang.String usuario, java.lang.String clave, java.lang.String tipo) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.datosUsuario(usuario, clave, tipo);
    }

    private static java.util.List<com.ransa.service.AreaRansa> getListaArea(java.lang.Integer idCliente) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.getListaArea(idCliente);
    }

    private static java.util.List<com.ransa.service.SerieRansa> getTipoDocumento() {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.getTipoDocumento();
    }

}
