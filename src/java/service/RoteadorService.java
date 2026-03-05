package service;

import dao.RoteadorDao;
import me.legrange.mikrotik.ApiConnection;
import model.Roteador;
import java.util.List;

public class RoteadorService {
    private RoteadorDao dao;

    public RoteadorService() {
        this.dao = new RoteadorDao();
    }

    public List<Roteador> listarTodos() {
        return dao.getAllRoteadores();
    }

    public void salvar(Roteador roteador) {
        if (roteador.getIdpontoacesso() == 0) {
            dao.insertRoteador(roteador);
        } else {
            dao.updateRoteador(roteador);
        }
    }

    public void excluir(int id) {
        dao.deleteRoteador(id);
    }

    public Roteador buscarPorId(int id) {
        return dao.getRoteadorByCodigo(id);
    }

    public void reiniciar(int id) throws Exception {
        Roteador roteador = dao.getRoteadorByCodigo(id);
        if (roteador != null) {
            try (ApiConnection apiCon = ApiConnection.connect(roteador.getIproteador())) {
                apiCon.login(roteador.getUsuario(), roteador.getPass());
                apiCon.execute("/system/reboot");
            }
        }
    }
}
