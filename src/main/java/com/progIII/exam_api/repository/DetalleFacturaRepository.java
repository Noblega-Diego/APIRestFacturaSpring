package com.progIII.exam_api.repository;

import com.progIII.exam_api.models.*;
import java.sql.Connection;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.progIII.exam_api.repository.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author diego
 */
@Repository
public class DetalleFacturaRepository {
    
    private Connection conect;
    
    public DetalleFacturaRepository(){
        try{
            conect = new Conexion().getConnection();
        }catch(SQLException ex){
            conect = null;
        }
    }
    public List<DetalleFactura> getDetalles(long idFacturaVenta){
        List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
        PreparedStatement ps =  null;
        ResultSet rs = null;
        try {
            ps = conect.prepareStatement(
                    "SELECT id, denominacionArticulo, cantidad, importe, descuento "
                            + "from factura_detalle where idfacturaventa = ?");
            ps.setLong(1, idFacturaVenta);
            rs = ps.executeQuery();
            while(rs.next())
            {
                DetalleFactura detalle = new DetalleFactura();
                detalle.setId(rs.getLong("id"));
                detalle.setDenominacionArticulo(rs.getString("denominacionArticulo"));
                detalle.setImporte(rs.getLong("importe"));
                detalle.setDescuento(rs.getDouble("descuento"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalles.add(detalle);
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
        return detalles;
    }
    
}
