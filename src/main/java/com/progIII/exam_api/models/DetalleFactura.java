package com.progIII.exam_api.models;

/**
 *
 * @author diego
 */
public class DetalleFactura {
    private long id;
    private double cantidad;
    private double importe;
    private double descuento;
    private String denominacionArticulo;
    private long idFactura;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getDenominacionArticulo() {
        return denominacionArticulo;
    }

    public void setDenominacionArticulo(String detalle) {
        this.denominacionArticulo = detalle;
    }

    public long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(long idFactura) {
        this.idFactura = idFactura;
    }
    
    
    
}
