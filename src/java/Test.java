/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Darien
 */
public class Test {

    /*
    public static void main(String[] args) {
        System.out.println("--->" + iniciarSesion("drixi", "123"));
    }
     */
    private static Boolean iniciarSesion(java.lang.String usuario, java.lang.String clave) {
        com.ransa.service.UsuarioRansaServiceImpl service = new com.ransa.service.UsuarioRansaServiceImpl();
        com.ransa.service.UsuarioRansaServiceImplPortType port = service.getUsuarioRansaServiceImplHttpSoap12Endpoint();
        return port.iniciarSesion(usuario, clave);
    }

}
