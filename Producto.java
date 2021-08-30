
public class Producto 
{
	private int codigo;
	private String tipoUnidad;
	private String descripcion;
	private int precio;
	private int impuesto;
	private int importe;
	
	public Producto()
	{}

	public Producto( int codigo, String tipoUnidad, String descripcion, int precio, int impuesto, int importe) {
		super();
		
		this.codigo = codigo;
		this.tipoUnidad = tipoUnidad;
		this.descripcion = descripcion;
		this.precio = precio;
		this.impuesto = impuesto;
		this.importe = importe;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(int impuesto) {
		this.impuesto = impuesto;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

}
