package service;

import dao.PaisDao;
import model.Pais;
import java.util.List;

public class PaisService {
    private PaisDao dao;

    public PaisService() {
        this.dao = new PaisDao();
    }

    public List<Pais> listar() {
        return dao.getAllPaises();
    }

    public void salvar(Pais pais) {
        if (pais.getIdpais() == 0) {
            dao.insertPais(pais);
        } else {
            dao.updatePais(pais);
        }
    }

    public void excluir(int id) {
        dao.deletePais(id);
    }

    public Pais getById(int id) {
        return dao.getPaisByCodigo(id);
    }
}
