package modelo;

public class Producto {
	
	private Integer id;
	private String descripcion;
	private String nombre;
	private Integer cantidad;
	 
	public Producto(String nombre, String descripcion, Integer cantidadInt) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidadInt;
	}

	public Producto(int id, String nombre, String descripcion, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;	
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setId(int id) {
		this.id = id;
		
	}
	
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		
		return String.format("{Id: %s, nombre: %s, descripcion: %s, cantidad: %d}", this.id, this.nombre, this.descripcion, this.cantidad);
	}

	
	
	
	
}