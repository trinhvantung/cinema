import "bootstrap-icons/font/bootstrap-icons.css";
import { Route, Routes } from "react-router-dom";
import Footer from "./components/Footer";
import Header from "./components/Header";
import PrivatePage from "./components/PrivatePage";
import Account from "./pages/Account";
import ActiveAccountResult from "./pages/ActiveAccountResult";
import Home from "./pages/Home";
import MovieDetail from "./pages/MovieDetail";
import Movies from "./pages/Movies";
import PaymenResult from "./pages/PaymenResult";
import ShowDetail from "./pages/ShowDetail";
import "./scss/index.scss";
import Ticket from "./pages/Ticket";

function App() {
  return (
    <>
      <Routes>
        <Route element={<Ticket />} path="/ticket/:bookingId" />
        <Route
          path="*"
          element={
            <>
              <Header />
              <div id="main">
                <Routes>
                  <Route path="/" element={<Home />} />
                  <Route path="/show/:id" element={<ShowDetail />} />
                  <Route path="/movie" element={<Movies />} />
                  <Route path="/movie/:id" element={<MovieDetail />} />
                  <Route
                    path="/account/*"
                    element={<PrivatePage page={Account} />}
                  />
                  <Route
                    path="/active-account"
                    element={<ActiveAccountResult />}
                  />
                  <Route
                    path="/payment/result"
                    element={<PrivatePage page={PaymenResult} />}
                  />
                </Routes>
              </div>

              <Footer />
            </>
          }
        />
      </Routes>
    </>
  );
}

export default App;
