package controller;

import java.util.List;

import dao.ProductoDAO;
import factory.ConnectionFactory;
import modelo.Producto;


public class ProductoController {
	
	private ProductoDAO productoDAO;
	
	public ProductoController() {
		this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(Producto producto){
		return productoDAO.modificar(producto);
	}
	
	public int eliminar(Integer id){
		return productoDAO.eliminar(id);
	}
	
	public List<Producto> listar(){
		return productoDAO.listar();		
	}
	
	public void guardar(Producto producto){
		productoDAO.guardar(producto); 
	}
		
		
}
