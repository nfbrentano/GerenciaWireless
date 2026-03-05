import { Wifi, Users, MapPin, Activity, Settings2, ShieldCheck, Download } from 'lucide-react';

export default function Dashboard() {
    const stats = [
        { label: 'Total de Roteadores', value: '1,248', icon: <Wifi size={24} />, trend: '+12%', color: 'var(--accent-primary)' },
        { label: 'Clientes Ativos', value: '8,432', icon: <Users size={24} />, trend: '+4%', color: 'var(--success)' },
        { label: 'Endereços Mapeados', value: '439', icon: <MapPin size={24} />, trend: '+2%', color: 'var(--warning)' },
        { label: 'Uptime Médio', value: '99.9%', icon: <Activity size={24} />, trend: 'Estável', color: 'var(--danger)' },
    ];

    const recentRouters = [
        { id: 'RT-8842', mac: '00:1A:2B:3C:4D:5E', status: 'Online', ip: '192.168.1.10', location: 'Centro' },
        { id: 'RT-1193', mac: '00:1A:2B:3C:4D:5F', status: 'Offline', ip: '192.168.1.12', location: 'Zona Sul' },
        { id: 'RT-2834', mac: '00:1A:2B:3C:4D:6A', status: 'Manutenção', ip: '192.168.1.55', location: 'Zona Norte' },
        { id: 'RT-9944', mac: '00:1A:2B:3C:4D:7B', status: 'Online', ip: '192.168.1.88', location: 'Leste' },
    ];

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

            <div className="grid-cards">
                {stats.map((stat, i) => (
                    <div key={i} className="card stat-card">
                        <div className="stat-icon" style={{ color: stat.color, backgroundColor: `${stat.color}22` }}>
                            {stat.icon}
                        </div>
                        <div className="stat-info">
                            <h3>{stat.label}</h3>
                            <p>{stat.value}</p>
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
                        <h2>Status dos Roteadores</h2>
                        <button className="btn-icon">
                            <Settings2 size={20} />
                        </button>
                    </div>

                    <div className="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Codígo</th>
                                    <th>Endereço MAC</th>
                                    <th>Status</th>
                                    <th>IP Local</th>
                                    <th>Localização</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                {recentRouters.map((router, i) => (
                                    <tr key={i}>
                                        <td style={{ fontWeight: '500' }}>{router.id}</td>
                                        <td style={{ color: 'var(--text-muted)' }}>{router.mac}</td>
                                        <td>
                                            <span className={`badge ${router.status === 'Online' ? 'success' :
                                                    router.status === 'Offline' ? 'danger' : 'warning'
                                                }`}>
                                                {router.status}
                                            </span>
                                        </td>
                                        <td>{router.ip}</td>
                                        <td>{router.location}</td>
                                        <td>
                                            <button className="btn-icon" title="Verificar status SNMP">
                                                <ShieldCheck size={18} />
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );
}
