package com.company.myapp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String dbName = "panaderia";
    private static final String dbUser = "root";
    private static final String dbPwd = "ange08lina09";


    public static Producto getProducto(Integer idProducto) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE idProducto = ?";
        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try{
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                producto = new Producto(
                        idProducto,
                        rs.getString("producto"),
                        rs.getInt("precio"),
                        rs.getInt("stock")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
        return producto;
    }
    public static List<Producto> getProductos(){
        List<Producto> productos = new ArrayList<>();

        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd);
        ResultSet rs = conexionDB.consultar("SELECT * FROM productos WHERE stock > 0;");
        if(rs!=null)
            try {
                while(rs.next()) {
                    productos.add(new Producto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4)
                    ));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                conexionDB.cerrar();
            }
        return productos;
    }
    public static int getPrecio(Integer idProducto) {
        int precio = 0;
        String sql = "SELECT precio FROM productos WHERE idProducto = ?";
        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try{
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                precio = rs.getInt("precio");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
        return precio;
    }
    public static String getDetalles(Integer idProducto) {
        String detalles = "";
        String sql = "SELECT * FROM productos WHERE idProducto = ?";
        ConexionDB conexionDB = new ConexionDB(dbName,dbUser,dbPwd, sql);
        PreparedStatement pstmt = conexionDB.getPstmt();
        try{
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                detalles = rs.getString("producto") + " $" +rs.getInt("precio");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            conexionDB.cerrar();
        }
        return detalles;
    }
}