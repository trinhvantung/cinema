import 'bootstrap-icons/font/bootstrap-icons.css';
import { Route, Routes } from 'react-router-dom';
import Footer from './components/Footer';
import Header from './components/Header';
import Account from './pages/Account';
import ActiveAccountResult from './pages/ActiveAccountResult';
import Home from './pages/Home';
import Login from './pages/Login';
import MovieDetail from './pages/MovieDetail';
import Movies from './pages/Movies';
import ShowDetail from './pages/ShowDetail';
import './scss/index.scss';
import Verify from './pages/Verify';
import PaymenResult from './pages/PaymenResult';

function App() {
  return (
    <>
      <Header />
      <div id="main">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/show/:id" element={<ShowDetail />} />
          <Route path="/movie" element={<Movies />} />
          <Route path="/movie/:id" element={<MovieDetail />} />
          <Route path="/login" element={<Login />} />
          <Route path="/account/*" element={<Account />} />
          <Route path="/active-account" element={<ActiveAccountResult />} />
          <Route path="/verify/:activationCode" element={<Verify />} />
          <Route path="/payment/result" element={<PaymenResult />} />
        </Routes>
      </div>

      <Footer />
    </>
  );
}

export default App;
