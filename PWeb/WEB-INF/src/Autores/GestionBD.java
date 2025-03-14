package Autores;

import java.sql.*;

public class GestionBD {

	private static final String URL_BD = "jdbc:mysql://localhost:3306/libros";
	private static final String CONTRASENNA = "";
	private static final String USUARIO = "root";
	private static Connection conexion;

	public static void abrirConexion() throws SQLException {

		conexion = DriverManager.getConnection(URL_BD, USUARIO, CONTRASENNA);

	}

	public static void cerrarConexion() throws SQLException {

		conexion.close();

	}

	public static int insertarAutor(String nombreAutor, String nac, String fnacAutor) throws SQLException {

		int filasAfectadas;
		Statement sentencia = conexion.createStatement();

		filasAfectadas = sentencia.executeUpdate("INSERT INTO autor (Nombre, Nacionalidad, Fnacimiento) VALUES  " + "('"
				+ nombreAutor + "', '" + nac + "', '" + fnacAutor + "')");

		return filasAfectadas;

	}

	public static int insertarLibro(int isbn, String editorial, String titulo, String idioma, int id_autor, String tipo)
			throws SQLException {

		int filasAfectadas;

		Statement sentencia = conexion.createStatement();
		filasAfectadas = sentencia.executeUpdate("INSERT INTO libro VALUES " + "('" + isbn + "', '" + editorial + "', '"
				+ titulo + "', '" + idioma + "', " + id_autor + ", '" + tipo + "')");

		return filasAfectadas;
	}

	public static ResultSet consultarAutores() throws SQLException {

		ResultSet resultadoQuery;
		Statement sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from autor");

		return resultadoQuery;
	}

	public static ResultSet consultarLibros() throws SQLException {

		ResultSet resultadoQuery;
		Statement sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery(
				"select L.Isbn, L.Editorial, L.Titulo, L.Idioma, A.Nombre, L.Tipo from libro L INNER JOIN autor A ON L.Id_Autor = A.Id_Autor");

		return resultadoQuery;

	}

	public static ResultSet consultarAutoresPorNombre(String nombre) throws SQLException {

		ResultSet resultadoQuery;
		Statement sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from autor WHERE Nombre LIKE '%" + nombre + "%'");

		return resultadoQuery;
	}

}
