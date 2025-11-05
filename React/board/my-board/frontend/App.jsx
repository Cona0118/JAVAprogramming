import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PostListPage from "./pages/PostListPage";
import PostFormPage from "./pages/PostFormPage";
import PostDetailPage from "./pages/PostDetailPage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<PostListPage />} />
                <Route path="/write" element={<PostFormPage />} />
                <Route path="/edit/:id" element={<PostFormPage />} />
                <Route path="/post/:id" element={<PostDetailPage />} />
            </Routes>
        </Router>
    );
}

export default App;
