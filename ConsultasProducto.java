
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultasProducto {
	
	private static final String URL = "jdbc:derby:Producto";
	private static final String USUARIO = "Medrano";
	private static final String PW		= "123456";
	
	private Connection conexion = null;
	private PreparedStatement insertarProducto = null;
	private PreparedStatement editarProducto = null;
	private PreparedStatement booleanProducto = null;
	private PreparedStatement falseProducto = null;
	
	public ConsultasProducto()
	{
		try
		{
			conexion = DriverManager.getConnection(URL,USUARIO,PW);
			
			insertarProducto = conexion.prepareStatement(
					"INSERT INTO Producto"+
					"(cantidad,tipoUnidad,descripcion,precio,importe)"
					+ "VALUES (?,?,?,?,?)");
			
			editarProducto = conexion.prepareStatement(
					"UPDATE Producto SET cantidad = ? ,tipoUnidad = ? ,descripcion = ? , precio = ? , importe = ?"+
					" WHERE idCodigo = ?");
			falseProducto = conexion.prepareStatement(
					"UPDATE Producto SET Activo = false");
			booleanProducto = conexion.prepareStatement(
					"UPDATE Producto SET Activo = true "
					+ "WHERE idCodigo = ? ");
			
		}catch(SQLException excepcionSql)
		{
			excepcionSql.printStackTrace();
	        System.exit( 1 );
		}
	}
	public int actProducto(String codigo)
	{
		int resultado = 0;
		try
		{
			falseProducto.executeUpdate();
			booleanProducto.setString(1,codigo);		
			resultado = booleanProducto.executeUpdate(); 
		}
		catch ( SQLException excepcionSql )
	      {
	         excepcionSql.printStackTrace();
	         close();
	      } // fin de catch
		return resultado;
	}
	public int addProducto(String cantidad,String tipoUnidad,String descripcion
			,String precio,String importe)
	{
		int resultado = 0;
		try
		{
			insertarProducto.setString(1,cantidad);
			insertarProducto.setString(2,tipoUnidad);
			insertarProducto.setString(3,descripcion);
			insertarProducto.setString(4,precio);
			insertarProducto.setString(5,importe);
			
			resultado = insertarProducto.executeUpdate(); 
		}
		catch ( SQLException excepcionSql )
	      {
	         excepcionSql.printStackTrace();
	         close();
	      } // fin de catch
		return resultado;
	}
	public int editProducto(String cantidad,String tipoUnidad,String descripcion
			,String precio,String importe,String codigo)
	{
		int resultado = 0;
		try
		{
			editarProducto.setString(1, cantidad);
			editarProducto.setString(2, tipoUnidad);
			editarProducto.setString(3, descripcion);
			editarProducto.setString(4, precio);
			editarProducto.setString(5, importe);
			editarProducto.setString(6, codigo);

			resultado = editarProducto.executeUpdate(); 
			falseProducto.executeUpdate();
			
		}catch ( SQLException excepcionSql )
	      {
	         excepcionSql.printStackTrace();
	         close();
	      } // fin de catch
		return resultado;
	}
	 public void close()
	   {
	      try 
	      {
	         conexion.close();
	      } // fin de try
	      catch ( SQLException excepcionSql )
	      {
	         excepcionSql.printStackTrace();
	      } // fin de catch
	   }

	
	
	
	
	
	
	
	
	
	
	
}
