import { useState, useEffect } from 'react';
import { Wifi, Users, MapPin, Activity, Settings2, ShieldCheck, Download } from 'lucide-react';
import { fetchApi } from '../api/config';

export default function Dashboard() {
    const [roteadores, setRoteadores] = useState([]);
    const [clientes, setClientes] = useState([]);
    const [enderecos, setEnderecos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadDashboardData = async () => {
            try {
                setLoading(true);
                const [rotData, cliData, endData] = await Promise.all([
                    fetchApi('/RoteadorController'),
                    fetchApi('/CadastroPessoaController'),
                    fetchApi('/EnderecoController')
                ]);
                setRoteadores(rotData);
                setClientes(cliData);
                setEnderecos(endData);
            } catch (err) {
                setError(err.message || 'Falha ao carregar dados do dashboard.');
            } finally {
                setLoading(false);
            }
        };
        loadDashboardData();
    }, []);

    const stats = [
        { label: 'Total de Roteadores', value: roteadores.length, icon: <Wifi size={24} />, trend: 'Atualizado', color: 'var(--accent-primary)' },
        { label: 'Clientes Ativos', value: clientes.length, icon: <Users size={24} />, trend: 'Atualizado', color: 'var(--success)' },
        { label: 'Endereços Mapeados', value: enderecos.length, icon: <MapPin size={24} />, trend: 'Atualizado', color: 'var(--warning)' },
        { label: 'Uptime Médio', value: '99.9%', icon: <Activity size={24} />, trend: 'Estável', color: 'var(--danger)' },
    ];

    const recentRouters = roteadores.slice(0, 5); // Mostrar os últimos 5 como exemplo

    return (
        <>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '32px' }}>
                <div>
                    <h1>Painel de Controle</h1>
                    <p style={{ color: 'var(--text-muted)' }}>Bem-vindo ao sistema de Gerência Wireless.</p>
                </div>
                <button className="btn btn-primary">
                    <Download size={16} /> Emitir Relatório
                </button>
            </div>

            {error && (
                <div style={{ padding: '16px', backgroundColor: 'rgba(239, 68, 68, 0.1)', color: 'var(--danger)', borderRadius: '8px', marginBottom: '16px' }}>
                    <strong>Erro:</strong> {error}. Certifique-se de que o backend Java (NetBeans) está rodando.
                </div>
            )}

            <div className="grid-cards">
                {stats.map((stat, i) => (
                    <div key={i} className="card stat-card">
                        <div className="stat-icon" style={{ color: stat.color, backgroundColor: `${stat.color}22` }}>
                            {stat.icon}
                        </div>
                        <div className="stat-info">
                            <h3>{stat.label}</h3>
                            <p>{loading ? '...' : stat.value}</p>
                            <span style={{ fontSize: '0.75rem', color: stat.trend.includes('+') ? 'var(--success)' : 'var(--text-muted)' }}>
                                {stat.trend} este mês
                            </span>
                        </div>
                    </div>
                ))}
            </div>

            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '24px' }}>
                <div className="card" style={{ gridColumn: '1 / -1' }}>
                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '24px' }}>
                        <h2>Status dos Roteadores Registrados</h2>
                        <button className="btn-icon">
                            <Settings2 size={20} />
                        </button>
                    </div>

                    <div className="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Codígo</th>
                                    <th>SSID</th>
                                    <th>Status</th>
                                    <th>IP Local</th>
                                    <th>Frequência</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {loading && recentRouters.length === 0 ? (
                                    <tr>
                                        <td colSpan="6" style={{ textAlign: 'center', padding: '32px', color: 'var(--text-muted)' }}>
                                            Carregando dados...
                                        </td>
                                    </tr>
                                ) : recentRouters.length > 0 ? recentRouters.map((router, i) => (
                                    <tr key={router.idpontoacesso || i}>
                                        <td style={{ fontWeight: '500' }}>{router.idpontoacesso}</td>
                                        <td style={{ color: 'var(--text-muted)' }}>{router.ssid}</td>
                                        <td>
                                            <span className="badge success">
                                                On/Off
                                            </span>
                                        </td>
                                        <td>{router.iproteador}</td>
                                        <td>{router.frequencia}</td>
                                        <td>
                                            <button className="btn-icon" title="Verificar status SNMP">
                                                <ShieldCheck size={18} />
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
                    </div>
                </div>
            </div>
        </>
    );
}
