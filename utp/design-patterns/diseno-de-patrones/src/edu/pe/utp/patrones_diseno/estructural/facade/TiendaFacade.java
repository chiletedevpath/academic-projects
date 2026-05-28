package edu.pe.utp.patrones_diseno.estructural.facade;

public class TiendaFacade {
    private SysStockProductos sysStockProductos;
    private SysPago sysPago;
    private SysFactura sysFactura;
    private SysNotificacion sysNotificacion;

    // Constructor que genera los subsistemas
    public TiendaFacade() {
        sysStockProductos = new SysStockProductos();
        sysPago = new SysPago();
        sysFactura = new SysFactura();
        sysNotificacion = new SysNotificacion();
    }

    // Metodo simplificado
    public void realizarCompras() {
        System.out.println("*** BIENVENIDO ***");

        sysStockProductos.verificarStock();

        sysPago.procesarPago();

        sysFactura.generarFactura();

        sysNotificacion.enviarNotificacion();

        System.out.println("*** Gracias por su compra ***");
    }

}
