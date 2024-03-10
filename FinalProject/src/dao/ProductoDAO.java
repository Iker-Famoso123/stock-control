package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.ConnectionFactory;
import modelo.Producto;

public class ProductoDAO {	
	
	final private Connection con;
	 
	public ProductoDAO(Connection con) {
		this.con = con;
	}
	
	
	public void guardar(Producto producto) {
		final Connection con = new ConnectionFactory().recuperaConexion();
		try(con){
			 final PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO (nombre, descripcion, cantidad) "
			 		+ "VALUES (?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			 
			 
			 
			 try(statement){
				 
					 
				 ejecutaRegistro(producto, statement);	

				 
				 //con.commit();
				 
				 
				 }catch(SQLException e) {
					 throw new RuntimeException(e);
				 }	 
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void ejecutaRegistro(Producto producto, PreparedStatement statement)
			throws SQLException {
		
		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());
		 
		 
		 statement.execute();

		 final ResultSet resultSet = statement.getGeneratedKeys();
		 
		 try(resultSet) {
			 while(resultSet.next()) {
				 producto.setId(resultSet.getInt(1));
				 System.out.println(String.format("Fue insertado el producto %s", producto));
				 
			 }
		 
		 }
		
	}

	public List<Producto> listar() {
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con){
		
			final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
			
			try(statement){
			
				statement.execute("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
				
				ResultSet resultSet = statement.getResultSet();
				
				List<Producto> resultado = new ArrayList<>();
				
				while(resultSet.next()) {
					Producto fila = new Producto(resultSet.getInt("ID"), 
							 resultSet.getString("NOMBRE"),
							 resultSet.getString("DESCRIPCION"),
							 resultSet.getInt("CANTIDAD"));
					
					resultado.add(fila);
				}
				 
				//System.out.println(result);
			
				return resultado;
			}
		
		
		}catch(SQLException e) {
			throw new RuntimeException (e);
		}
	}

	public int modificar(Producto producto) {
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con){
			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET NOMBRE = ? , DESCRIPCION =  ?, CANTIDAD =  ? "
					+ "WHERE ID = ?");
			try(statement){
				statement.setString(1, producto.getNombre());
				statement.setString(2, producto.getDescripcion());
				statement.setInt(3, producto.getCantidad());
				statement.setInt(4, producto.getId());
				
				statement.execute();
				
				
				
				
				return statement.getUpdateCount();
				
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException (e);
		}
		
		
		
		
		
	}

	public int eliminar(Integer id) {
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con){
		
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");
			
			try(statement){
			
				statement.setInt(1, id);
				
				statement.execute();
				
				return statement.getUpdateCount();
			}
			
		
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException (e);
		}
		
	}

}