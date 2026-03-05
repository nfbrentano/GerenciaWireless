import { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, useLocation } from 'react-router-dom';
import { LayoutDashboard, Users, Wifi, MapPin, Menu, X, Bell, UserCircle } from 'lucide-react';
import Dashboard from './pages/Dashboard';
import './index.css';

function Sidebar({ open, toggleSidebar }) {
  const location = useLocation();

  const navItems = [
    { name: 'Dashboard', icon: <LayoutDashboard size={20} />, path: '/' },
    { name: 'Roteadores', icon: <Wifi size={20} />, path: '/roteadores' },
    { name: 'Clientes', icon: <Users size={20} />, path: '/clientes' },
    { name: 'Endereços', icon: <MapPin size={20} />, path: '/enderecos' },
  ];

  return (
    <>
      <div className={`sidebar ${open ? 'open' : ''}`}>
        <div className="sidebar-header">
          <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
            <Wifi color="var(--accent-primary)" />
            <span>GerenciaWireless</span>
          </div>
          <button className="menu-toggle" onClick={toggleSidebar} style={{ marginLeft: 'auto' }}>
            <X size={24} />
          </button>
        </div>
        
        <nav className="sidebar-nav">
          {navItems.map((item) => (
            <Link 
              key={item.path} 
              to={item.path} 
              className={`nav-item ${location.pathname === item.path ? 'active' : ''}`}
              onClick={() => {
                if (window.innerWidth <= 768) toggleSidebar();
              }}
            >
              {item.icon}
              {item.name}
            </Link>
          ))}
        </nav>
      </div>
      
      {/* Mobile overlay */}
      {open && (
        <div 
          style={{ position: 'fixed', inset: 0, backgroundColor: 'rgba(0,0,0,0.5)', zIndex: 40 }}
          onClick={toggleSidebar}
        />
      )}
    </>
  );
}

function Header({ toggleSidebar }) {
  return (
    <header className="top-header">
      <div style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
        <button className="menu-toggle" onClick={toggleSidebar}>
          <Menu size={24} />
        </button>
      </div>
      
      <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
        <button className="btn-icon">
          <Bell size={20} />
        </button>
        <button className="btn-icon">
          <UserCircle size={24} />
        </button>
      </div>
    </header>
  );
}

function Layout() {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div className="app-container">
      <Sidebar open={sidebarOpen} toggleSidebar={() => setSidebarOpen(!sidebarOpen)} />
      
      <main className="main-content">
        <Header toggleSidebar={() => setSidebarOpen(!sidebarOpen)} />
        <div className="page-container">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="*" element={<Dashboard />} />
          </Routes>
        </div>
      </main>
    </div>
  );
}

function App() {
  return (
    <Router>
      <Layout />
    </Router>
  );
}

export default App;
