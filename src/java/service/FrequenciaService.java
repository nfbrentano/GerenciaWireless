package service;

import dao.FrequenciaDao;
import java.util.List;
import model.Frequencia;

public class FrequenciaService {

    private final FrequenciaDao dao;

    public FrequenciaService() {
        this.dao = new FrequenciaDao();
    }

    public List<Frequencia> listar() {
        return dao.getAllFrequencias();
    }

    public Frequencia getById(int id) {
        return dao.getFrequenciaByCodigo(id);
    }

    public void salvar(Frequencia frequencia) {
        if (frequencia.getIdfrequencia() == 0) {
            dao.insertFrequencia(frequencia);
        } else {
            dao.updateFrequencia(frequencia);
        }
    }

    public void excluir(int id) {
        dao.deleteFrequencia(id);
    }
}
