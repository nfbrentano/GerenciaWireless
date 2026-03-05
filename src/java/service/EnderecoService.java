package service;

import dao.EnderecoDao;
import model.Endereco;
import java.util.List;

public class EnderecoService {
    private EnderecoDao dao;

    public EnderecoService() {
        this.dao = new EnderecoDao();
    }

    public List<Endereco> listar() {
        return dao.getAllEnderecos();
     }

    public void salvar(Endereco endereco) {
        if (endereco.getIdendereco() == 0) {
            dao.insertEndereco(endereco);
        } else {
            dao.updateEndereco(endereco);
        }
    }

    public void excluir(int id) {
        dao.deleteEndereco(id);
    }

    public Endereco getById(int id) {
        return dao.getEnderecoByCodigo(id);
    }
}
