package shorturl.classes;

public class Parameters {
 
    public static final String homePage = "index.jsp";
    /*  User Class */

    public static final String userIDProp = "usuario_id";
    public static final String userUsuarioProp = "usuario_usuario";
    public static final String userPasswordProp = "usuario_contrasena";
    public static final String userEmailProp = "usuario_apellido";
    public static final String userPhotoProp = "usuario_apellido";
    public static final String userRoleProp = "usuario_role";

    public static final String userSessionProp = "usuario_session";

    public static final String contextUsers = "usuarios";
    /*  Producto Class */

    public static final String productoCodigoProp = "producto_codigo";
    public static final String productoNombreProp = "producto_nombre";
    public static final String productoDescripcionProp = "producto_descripcion";
    public static final String productoCantidadProp = "producto_cantidad"; //cantidad restante
    public static final String productoPrecioProp = "producto_precio";

    public static final String contextProducts = "productos";
    public static final String contextCart = "carrito";
    public static final String contextItemCompra = "ItemCompra";
    /* QUERIES USUARIO*/
    public static final String usuarioQuerySelectAll = "SELECT * from usuarios";
    public static final String usuarioQuerySelectByusername = "SELECT * from usuarios WHERE usuario=?";
    public static final String usuarioQueryInsert = "INSERT INTO usuarios (usuario, nombre, apellidos, clave) VALUES(?,?,?,?)";
    public static final String usuarioQueryUpdatebyusername = "UPDATE usuarios SET usuario=?, nombre=?, apellidos=?, clave=? where usuario = ? ";
    public static final String usuarioQueryDeletebyusername = "DELETE FROM usuarios where usuario = ?";
    /*QUERIES PRODUCTO*/
    public static final String productoQuerySelectAll = "SELECT * from productos";
    public static final String productoQuerySelectBycodigo = "SELECT * from productos WHERE codigo=?";
    public static final String productoQueryInsert = "INSERT INTO productos (codigo, nombre, descripcion, cantidadr,precio) VALUES(?,?,?,?,?)";
    public static final String productoQueryUpdatebycodigo = "UPDATE productos SET codigo=?, nombre=?, descripcion=?, cantidadr=?,precio=? where codigo = ? ";
    public static final String productoQueryDeletebycodigo = "DELETE FROM productos where codigo = ?";

    /* DB Permisions  */
    public static final String dbUrl = "jdbc:h2:tcp://localhost/~/practica4";
    public static final String dbUser = "sa";
    public static final String dbPassword = "";

}
