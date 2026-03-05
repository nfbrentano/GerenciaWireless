package service;

import dao.CidadeDao;
import model.Cidade;
import java.util.List;

public class CidadeService {
    private CidadeDao dao;

    public CidadeService() {
        this.dao = new CidadeDao();
    }

    public List<Cidade> listar() {
        return dao.getAllCidades();
    }

    public void salvar(Cidade cidade) {
        if (cidade.getIdcidade() == 0) {
            dao.insertCidade(cidade);
        } else {
            dao.updateCidade(cidade);
        }
    }

    public void excluir(int id) {
        dao.deleteCidade(id);
    }

    public Cidade getById(int id) {
        return dao.getCidadeByCodigo(id);
    }
}
