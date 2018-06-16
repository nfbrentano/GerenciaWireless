
import java.util.List;
import java.util.Map;
import me.legrange.mikrotik.MikrotikApiException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author natan
 */
public class Comando extends Mikrotik {

    public static void main(String... args) throws Exception {
        Comando ex = new Comando();
        ex.connect();
        ex.test();
        ex.disconnect();
    }

    private void test() throws MikrotikApiException {
        List<Map<String, String>> results = con.execute("/interface/print");
        for (Map<String, String> result : results) {
            System.out.println(result);
        }
    }
}
