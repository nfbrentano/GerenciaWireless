package service;

import dao.CadastroPessoaDao;
import dao.RoteadorDao;
import me.legrange.mikrotik.ApiConnection;
import model.CadastroPessoa;
import model.Roteador;
import java.util.List;

public class CadastroPessoaService {
    private CadastroPessoaDao pessoaDao;
    private RoteadorDao roteadorDao;

    public CadastroPessoaService() {
        this.pessoaDao = new CadastroPessoaDao();
        this.roteadorDao = new RoteadorDao();
    }

    public List<CadastroPessoa> listarTodos() {
        return pessoaDao.getAllCadastroPessoa();
    }

    public void salvar(CadastroPessoa pessoa) {
        if (pessoa.getIdcadastroPessoa() == 0) {
            pessoaDao.insertCadastroPessoa(pessoa);
        } else {
            pessoaDao.updateCadastroPessoa(pessoa);
        }
    }

    public void excluir(int id) throws Exception {
        CadastroPessoa pessoa = pessoaDao.getCadastroPessoaByCodigo(id);
        if (pessoa != null && pessoa.getNomeusuario() != null) {
            String ssid = pessoa.getPontoacesso();
            if (ssid != null && !ssid.isEmpty()) {
                Roteador roteador = roteadorDao.getRoteadorBySsid(ssid);
                if (roteador != null) {
                    try {
                        ApiConnection apiCon = ApiConnection.connect(roteador.getIproteador());
                        apiCon.login(roteador.getUsuario(), roteador.getPass());
                        apiCon.execute("/ip/hotspot/user/remove numbers='" + pessoa.getNomeusuario() + "'");
                        apiCon.close();
                    } catch (Exception e) {
                        System.err.println("Erro ao remover usuário do Mikrotik: " + e.getMessage());
                        // Opcional: relançar ou apenas logar. No servlet original era apenas logado via Exception e.
                    }
                }
            }
        }
        pessoaDao.deleteCadastroPessoa(id);
    }

    public CadastroPessoa buscarPorId(int id) {
        return pessoaDao.getCadastroPessoaByCodigo(id);
    }
}
