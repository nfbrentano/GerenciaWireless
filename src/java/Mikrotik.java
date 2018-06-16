
import javax.net.SocketFactory;
import me.legrange.mikrotik.ApiConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author natan
 */
abstract class Mikrotik {

    protected void connect() throws Exception {
        con = ApiConnection.connect(SocketFactory.getDefault(), Config.HOST, ApiConnection.DEFAULT_PORT, 2000);
        con.login(Config.USERNAME, Config.PASSWORD);
    }

    protected void disconnect() throws Exception {
        con.close();
    }

    protected ApiConnection con;

}
