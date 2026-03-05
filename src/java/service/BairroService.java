package service;

import dao.BairroDao;
import model.Bairro;
import java.util.List;

public class BairroService {
    private BairroDao dao;

    public BairroService() {
        this.dao = new BairroDao();
    }

    public List<Bairro> listar() {
        return dao.getAllBairros();
    }

    public void salvar(Bairro bairro) {
        if (bairro.getIdbairro() == 0) {
            dao.insertBairro(bairro);
        } else {
            dao.updateBairro(bairro);
        }
    }

    public void excluir(int id) {
        dao.deleteBairro(id);
    }

    public Bairro getById(int id) {
        return dao.getBairroByCodigo(id);
    }
}
