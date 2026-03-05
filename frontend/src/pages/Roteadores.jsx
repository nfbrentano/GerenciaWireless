import { useState, useEffect } from 'react';
import { Wifi, Plus, Edit, Trash2, Power, Search, RefreshCw } from 'lucide-react';
import { fetchApi } from '../api/config';

export default function Roteadores() {
    const [roteadores, setRoteadores] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    const loadData = async () => {
        try {
            setLoading(true);
            setError(null);
            const data = await fetchApi('/RoteadorController');
            setRoteadores(data);
        } catch (err) {
            setError(err.message || 'Falha ao carregar os roteadores.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadData();
    }, []);

    const handleDelete = async (id) => {
        if (!window.confirm('Tem certeza que deseja remover este roteador?')) return;

        try {
            await fetchApi(`/RoteadorController?id=${id}`, { method: 'DELETE' });
            loadData();
        } catch (err) {
            alert(err.message || 'Falha ao excluir o roteador.');
        }
    };

    const handleRestart = async (id) => {
        if (!window.confirm('Tem certeza que deseja reiniciar este roteador via SNMP/API?')) return;

        try {
            await fetchApi(`/RoteadorController?acao=reiniciar&id=${id}`, { method: 'POST' });
            alert('Sinal de reinício enviado com sucesso!');
        } catch (err) {
            alert(err.message || 'Falha ao reiniciar o roteador.');
        }
    };

    const filtered = roteadores.filter(r =>
        r.ssid?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        r.modelo?.toLowerCase().includes(searchTerm.toLowerCase()) ||
        r.iproteador?.includes(searchTerm)
    );

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '32px' }}>
                <div>
                    <h1>Gerenciamento de Roteadores</h1>
                    <p style={{ color: 'var(--text-muted)' }}>Lista e cadastro de pontos de acesso (Mikrotik/Outros)</p>
                </div>
                <button className="btn btn-primary">
                    <Plus size={16} /> Adicionar Roteador
                </button>
            </div>

            <div className="card" style={{ marginBottom: '24px' }}>
                <div style={{ display: 'flex', gap: '16px', marginBottom: '24px', alignItems: 'center' }}>
                    <div style={{ position: 'relative', flex: 1, maxWidth: '400px' }}>
                        <Search size={20} style={{ position: 'absolute', left: '12px', top: '50%', transform: 'translateY(-50%)', color: 'var(--text-muted)' }} />
                        <input
                            type="text"
                            placeholder="Buscar por SSID, Modelo ou IP..."
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
                    {loading && roteadores.length === 0 ? (
                        <div style={{ padding: '40px', textAlign: 'center', color: 'var(--text-muted)' }}>Carregando dados do servidor...</div>
                    ) : (
                        <table>
                            <thead>
                                <tr>
                                    <th>Codígo</th>
                                    <th>Modelo</th>
                                    <th>SSID</th>
                                    <th>IP Local</th>
                                    <th>Frequência</th>
                                    <th style={{ textAlign: 'right' }}>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {filtered.length > 0 ? filtered.map((router) => (
                                    <tr key={router.idpontoacesso}>
                                        <td style={{ fontWeight: '500' }}>{router.idpontoacesso}</td>
                                        <td>{router.modelo}</td>
                                        <td style={{ color: 'var(--accent-primary)' }}>{router.ssid}</td>
                                        <td>{router.iproteador}</td>
                                        <td>{router.frequencia}</td>
                                        <td style={{ textAlign: 'right', display: 'flex', gap: '8px', justifyContent: 'flex-end' }}>
                                            <button className="btn-icon" title="Editar">
                                                <Edit size={18} />
                                            </button>
                                            <button className="btn-icon" title="Reiniciar" onClick={() => handleRestart(router.idpontoacesso)}>
                                                <Power size={18} color="var(--warning)" />
                                            </button>
                                            <button className="btn-icon" title="Excluir" onClick={() => handleDelete(router.idpontoacesso)}>
                                                <Trash2 size={18} color="var(--danger)" />
                                            </button>
                                        </td>
                                    </tr>
                                )) : (
                                    <tr>
                                        <td colSpan="6" style={{ textAlign: 'center', padding: '32px', color: 'var(--text-muted)' }}>
                                            Nenhum roteador encontrado.
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
