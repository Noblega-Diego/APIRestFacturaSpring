package com.progIII.exam_api.repository;

import com.progIII.exam_api.models.Factura;
import java.sql.Connection;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.progIII.exam_api.repository.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *
 * @author diego
 */
@Repository
public class FacturaRespository {
    
    private Connection conect;
    
    @Autowired
    private DetalleFacturaRepository detalleRepository;
    
    public FacturaRespository(){
        try{
            conect = new Conexion().getConnection();
        }catch(SQLException ex){
            conect = null;
        }
    }
    public List<Factura> getFacturas(){
        List<Factura> facturas = new ArrayList<Factura>();
        PreparedStatement ps =  null;
        ResultSet rs = null;
        try {
            ps = conect.prepareStatement(
                    "SELECT id, fechaComprobante, nroComprobante, montoTotal "
                            + "from factura");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Factura factura = new Factura();
                factura.setId(rs.getLong("id"));
                factura.setFechaComprobante(rs.getDate("fechaComprobante"));
                factura.setNroComprobante(rs.getLong("nroComprobante"));
                factura.setMontoTotal(rs.getDouble("montoTotal"));
                facturas.add(factura);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(ps != null)
                    ps.close();
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return facturas;
    }
    
    public Factura getFactura(long id){
        Factura factura = null;
        PreparedStatement ps =  null;
        ResultSet rs = null;
        try {
            ps = conect.prepareStatement(
                    "SELECT id, fechaComprobante, nroComprobante, montoTotal, letraComprobante, tipoComprobante "
                            + " from factura WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                factura = new Factura();
                factura.setId(rs.getLong("id"));
                factura.setFechaComprobante(rs.getDate("fechaComprobante"));
                factura.setNroComprobante(rs.getLong("nroComprobante"));
                factura.setLetraComprobante(rs.getString("letraComprobante"));
                factura.setTipoComprobante(rs.getString("tipoComprobante"));
                factura.setMontoTotal(rs.getDouble("montoTotal"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(ps != null)
                    ps.close();
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if(factura != null)
            factura.setDetalles(detalleRepository.getDetalles( factura.getId() ));
        return factura;
    }
}
