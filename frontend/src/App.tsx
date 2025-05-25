import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import { Toaster } from 'react-hot-toast';
import ReportPage from "./pages/ReportPage";

function App() {
    return (
        <>
            <Toaster position="top-right" />

            <Router>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/report" element={<ReportPage />} />
                </Routes>
            </Router>
        </>
    );
}

export default App;
