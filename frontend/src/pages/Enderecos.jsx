import { useState, useEffect } from 'react';
import { MapPin, Plus, Edit, Trash2, Search, RefreshCw } from 'lucide-react';
import { fetchApi } from '../api/config';

export default function Enderecos() {
    const [enderecos, setEnderecos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    const loadData = async () => {
        try {
            setLoading(true);
            setError(null);
            const data = await fetchApi('/EnderecoController');
            setEnderecos(data);
        } catch (err) {
            setError(err.message || 'Falha ao carregar os endereços.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadData();
    }, []);

    const handleDelete = async (id) => {
        if (!window.confirm('Tem certeza que deseja remover este endereço? Todos os clientes vinculados poderão ter problemas.')) return;

        try {
            await fetchApi(`/EnderecoController?id=${id}`, { method: 'DELETE' });
            loadData();
        } catch (err) {
            alert(err.message || 'Falha ao excluir o endereço.');
        }
    };

    const filtered = enderecos.filter(e =>
        e.nome?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        e.bairro?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        e.cidade?.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '32px' }}>
                <div>
                    <h1>Base de Endereços</h1>
                    <p style={{ color: 'var(--text-muted)' }}>Gerenciamento de Ruas, Bairros, Cidades e Estados</p>
                </div>
                <button className="btn btn-primary">
                    <Plus size={16} /> Novo Endereço
                </button>
            </div>

            <div className="card" style={{ marginBottom: '24px' }}>
                <div style={{ display: 'flex', gap: '16px', marginBottom: '24px', alignItems: 'center' }}>
                    <div style={{ position: 'relative', flex: 1, maxWidth: '400px' }}>
                        <Search size={20} style={{ position: 'absolute', left: '12px', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
                        <input
                            type="text"
                            placeholder="Buscar por Rua, Bairro ou Cidade..."
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
                    {loading && enderecos.length === 0 ? (
                        <div style={{ padding: '40px', textAlign: 'center', color: 'var(--text-muted)' }}>Carregando dados globais...</div>
                    ) : (
                        <table>
                            <thead>
                                <tr>
                                    <th>Codígo</th>
                                    <th>Logradouro (Rua)</th>
                                    <th>Bairro</th>
                                    <th>Cidade</th>
                                    <th>Estado</th>
                                    <th style={{ textAlign: 'right' }}>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {filtered.length > 0 ? filtered.map((endereco) => (
                                    <tr key={endereco.idendereco}>
                                        <td style={{ fontWeight: '500' }}>{endereco.idendereco}</td>
                                        <td style={{ color: 'var(--text-primary)' }}>{endereco.nome}</td>
                                        <td style={{ color: 'var(--accent-primary)' }}>{endereco.bairro}</td>
                                        <td>{endereco.cidade}</td>
                                        <td><span className="badge success">{endereco.estado}</span></td>
                                        <td style={{ textAlign: 'right', display: 'flex', gap: '8px', justifyContent: 'flex-end' }}>
                                            <button className="btn-icon" title="Editar">
                                                <Edit size={18} />
                                            </button>
                                            <button className="btn-icon" title="Excluir" onClick={() => handleDelete(endereco.idendereco)}>
                                                <Trash2 size={18} color="var(--danger)" />
                                            </button>
                                        </td>
                                    </tr>
                                )) : (
                                    <tr>
                                        <td colSpan="6" style={{ textAlign: 'center', padding: '32px', color: 'var(--text-muted)' }}>
                                            Nenhum endereço encontrado.
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
