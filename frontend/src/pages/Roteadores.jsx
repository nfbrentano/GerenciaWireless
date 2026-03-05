import { useState, useEffect } from 'react';
import { Wifi, Plus, Edit, Trash2, Power, Search, RefreshCw, X } from 'lucide-react';
import { fetchApi } from '../api/config';

export default function Roteadores() {
    const [roteadores, setRoteadores] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');

    // Modal state
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formData, setFormData] = useState(null);

    const initialFormState = {
        idpontoacesso: 0,
        ssid: '',
        modelo: '',
        largurabanda: '',
        frequencia: '',
        iproteador: '',
        usuario: '',
        pass: ''
    };

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

    const handleOpenModal = (router = null) => {
        setFormData(router ? { ...router } : { ...initialFormState });
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setFormData(null);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSave = async (e) => {
        e.preventDefault();
        try {
            await fetchApi('/RoteadorController', {
                method: 'POST',
                body: JSON.stringify(formData)
            });
            handleCloseModal();
            loadData();
        } catch (err) {
            alert(err.message || 'Falha ao salvar roteador.');
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
                <button className="btn btn-primary" onClick={() => handleOpenModal()}>
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
                                            <button className="btn-icon" title="Editar" onClick={() => handleOpenModal(router)}>
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

            {/* Modal de Formulário */}
            {isModalOpen && formData && (
                <div style={{
                    position: 'fixed', top: 0, left: 0, right: 0, bottom: 0,
                    backgroundColor: 'rgba(0,0,0,0.7)', display: 'flex',
                    alignItems: 'center', justifyContent: 'center', zIndex: 1000
                }}>
                    <div className="card" style={{ width: '100%', maxWidth: '500px', padding: '24px' }}>
                        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '24px' }}>
                            <h2 style={{ fontSize: '1.25rem' }}>{formData.idpontoacesso ? 'Editar Roteador' : 'Novo Roteador'}</h2>
                            <button className="btn-icon" onClick={handleCloseModal}><X size={20} /></button>
                        </div>

                        <form onSubmit={handleSave} style={{ display: 'flex', flexDirection: 'column', gap: '16px' }}>
                            <div style={{ display: 'flex', gap: '16px' }}>
                                <div style={{ flex: 1 }}>
                                    <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>Modelo</label>
                                    <input required type="text" name="modelo" value={formData.modelo} onChange={handleInputChange}
                                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                                </div>
                                <div style={{ flex: 1 }}>
                                    <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>SSID</label>
                                    <input required type="text" name="ssid" value={formData.ssid} onChange={handleInputChange}
                                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                                </div>
                            </div>

                            <div style={{ display: 'flex', gap: '16px' }}>
                                <div style={{ flex: 1 }}>
                                    <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>Largura de Banda</label>
                                    <input type="text" name="largurabanda" value={formData.largurabanda} onChange={handleInputChange}
                                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                                </div>
                                <div style={{ flex: 1 }}>
                                    <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>Frequência</label>
                                    <input type="text" name="frequencia" value={formData.frequencia} onChange={handleInputChange}
                                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                                </div>
                            </div>

                            <div>
                                <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>IP Local do Roteador</label>
                                <input required type="text" name="iproteador" value={formData.iproteador} onChange={handleInputChange}
                                    style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                            </div>

                            <div style={{ display: 'flex', gap: '16px' }}>
                                <div style={{ flex: 1 }}>
                                    <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>Usuário SNMP / API</label>
                                    <input required type="text" name="usuario" value={formData.usuario} onChange={handleInputChange} autoComplete="new-password"
                                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                                </div>
                                <div style={{ flex: 1 }}>
                                    <label style={{ display: 'block', marginBottom: '8px', color: 'var(--text-muted)', fontSize: '0.875rem' }}>Senha</label>
                                    <input required type="password" name="pass" value={formData.pass} onChange={handleInputChange} autoComplete="new-password"
                                        style={{ width: '100%', padding: '10px', borderRadius: '6px', border: '1px solid var(--border-color)', backgroundColor: 'transparent', color: 'white' }} />
                                </div>
                            </div>

                            <div style={{ display: 'flex', justifyContent: 'flex-end', gap: '12px', marginTop: '16px' }}>
                                <button type="button" className="btn" onClick={handleCloseModal} style={{ backgroundColor: 'transparent', border: '1px solid var(--border-color)' }}>
                                    Cancelar
                                </button>
                                <button type="submit" className="btn btn-primary" style={{ backgroundColor: 'var(--success)', color: 'white' }}>
                                    Salvar Roteador
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}
