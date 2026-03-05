package service;

import dao.EstadoDao;
import model.Estado;
import java.util.List;

public class EstadoService {
    private EstadoDao dao;

    public EstadoService() {
        this.dao = new EstadoDao();
    }

    public List<Estado> listar() {
        return dao.getAllEstados();
    }

    public void salvar(Estado estado) {
        if (estado.getIdestado() == 0) {
            dao.insertEstado(estado);
        } else {
            dao.updateEstado(estado);
        }
    }

    public void excluir(int id) {
        dao.deleteEstado(id);
    }

    public Estado getById(int id) {
        return dao.getEstadoByCodigo(id);
    }
}
