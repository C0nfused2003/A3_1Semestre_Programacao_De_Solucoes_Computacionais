package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection getConexao() throws Exception {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost/controle_estoque", "root", "Koko120289@"
        );
    }
}
