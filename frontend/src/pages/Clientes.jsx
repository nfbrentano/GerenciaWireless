import { useState, useEffect } from 'react';
import { Users, Plus, Edit, Trash2, Search, RefreshCw, Eye } from 'lucide-react';
import { fetchApi } from '../api/config';

export default function Clientes() {
    const [clientes, setClientes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    const loadData = async () => {
        try {
            setLoading(true);
            setError(null);
            const data = await fetchApi('/CadastroPessoaController');
            setClientes(data);
        } catch (err) {
            setError(err.message || 'Falha ao carregar os clientes.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadData();
    }, []);

    const handleDelete = async (id) => {
        if (!window.confirm('Tem certeza que deseja remover este cliente?')) return;

        try {
            await fetchApi(`/CadastroPessoaController?id=${id}`, { method: 'DELETE' });
            loadData();
        } catch (err) {
            alert(err.message || 'Falha ao excluir o cliente.');
        }
    };

    const filtered = clientes.filter(c =>
        c.nome?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        c.sobrenome?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        c.documento?.includes(searchTerm)
    );

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '32px' }}>
                <div>
                    <h1>Gestão de Clientes</h1>
                    <p style={{ color: 'var(--text-muted)' }}>Mantenha o cadastro e acesso de seus assinantes</p>
                </div>
                <button className="btn btn-primary">
                    <Plus size={16} /> Novo Cadastro
                </button>
            </div>

            <div className="card" style={{ marginBottom: '24px' }}>
                <div style={{ display: 'flex', gap: '16px', marginBottom: '24px', alignItems: 'center' }}>
                    <div style={{ position: 'relative', flex: 1, maxWidth: '400px' }}>
                        <Search size={20} style={{ position: 'absolute', left: '12px', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
                        <input
                            type="text"
                            placeholder="Buscar por nome ou documento..."
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                            style={{
                                width: '100%', padding: '10px 10px 10px 40px',
                                borderRadius: '8px', border: '1px solid var(--border-color)',
                                backgroundColor: 'rgba(0,0,0,0.2)', color: 'white'
                            }}
                        />
                    </div>
                    <button className="btn-icon" onClick={loadData} title="Recarregar">
                        <RefreshCw size={20} className={loading ? 'spinning' : ''} />
                    </button>
                </div>

                {error && (
                    <div style={{ padding: '16px', backgroundColor: 'rgba(239, 68, 68, 0.1)', color: 'var(--danger)', borderRadius: '8px', marginBottom: '16px' }}>
                        <strong>Erro:</strong> {error}. Certifique-se de que o backend Java (NetBeans) está rodando.
                    </div>
                )}

                <div className="table-container">
                    {loading && clientes.length === 0 ? (
                        <div style={{ padding: '40px', textAlign: 'center', color: 'var(--text-muted)' }}>Carregando dados do servidor...</div>
                    ) : (
                        <table>
                            <thead>
                                <tr>
                                    <th>Codígo</th>
                                    <th>Nome Completo</th>
                                    <th>Documento</th>
                                    <th>PPPoE / Usuário</th>
                                    <th>Localização</th>
                                    <th style={{ textAlign: 'right' }}>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {filtered.length > 0 ? filtered.map((cliente) => (
                                    <tr key={cliente.idcadastroPessoa}>
                                        <td style={{ fontWeight: '500' }}>{cliente.idcadastroPessoa}</td>
                                        <td style={{ color: 'var(--accent-primary)' }}>{cliente.nome} {cliente.sobrenome}</td>
                                        <td>{cliente.documento}</td>
                                        <td><span className="badge warning">{cliente.nomeusuario}</span></td>
                                        <td style={{ color: 'var(--text-muted)' }}>{cliente.cidade} / {cliente.estado}</td>
                                        <td style={{ textAlign: 'right', display: 'flex', gap: '8px', justifyContent: 'flex-end' }}>
                                            <button className="btn-icon" title="Ver Detalhes">
                                                <Eye size={18} />
                                            </button>
                                            <button className="btn-icon" title="Editar">
                                                <Edit size={18} />
                                            </button>
                                            <button className="btn-icon" title="Excluir" onClick={() => handleDelete(cliente.idcadastroPessoa)}>
                                                <Trash2 size={18} color="var(--danger)" />
                                            </button>
                                        </td>
                                    </tr>
                                )) : (
                                    <tr>
                                        <td colSpan="6" style={{ textAlign: 'center', padding: '32px', color: 'var(--text-muted)' }}>
                                            Nenhum cliente encontrado.
                                        </td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    )}
                </div>
            </div>
        </div>
    );
}
