import { useState, useEffect } from "react";
import { useNavigate, useParams, useLocation } from "react-router-dom"; // ✅ useLocation 추가
import axios from "axios";
import '../styles/PostForm.scss';

function PostFormPage() {
    const { id } = useParams();
    const navigate = useNavigate();
    const location = useLocation(); // ✅ 현재 URL
    const queryParams = new URLSearchParams(location.search);
    const currentPage = queryParams.get("page") || 1; // ✅ 현재 페이지

    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [author, setAuthor] = useState("익명");
    const [password, setPassword] = useState("");

    useEffect(() => {
        if (id) {
            axios.get(`http://localhost:5000/api/posts/${id}`)
                .then(res => {
                    setTitle(res.data.title);
                    setContent(res.data.content);
                    setAuthor(res.data.author || "익명");
                })
                .catch(err => console.error(err));
        }
    }, [id]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!title || !content || (!id && !password)) {
            alert("제목, 내용, 비밀번호는 필수입니다.");
            return;
        }

        try {
            if (id) {
                await axios.put(`http://localhost:5000/api/posts/${id}`, { title, content, password });
                alert("수정 완료");
            } else {
                await axios.post("http://localhost:5000/api/posts", { title, content, author, password });
                alert("작성 완료");
            }
            navigate(`/?page=${currentPage}`); // ✅ 페이지 쿼리 유지
        } catch (err) {
            console.error(err);
            alert(err.response?.data?.error || "실패");
        }
    };

    return (
        <div className="form-container">
            <div className="form-box">
                <h1>{id ? "글 수정" : "글 작성"}</h1>
                <form onSubmit={handleSubmit}>
                    <input type="text" placeholder="제목" value={title} onChange={e => setTitle(e.target.value)} />
                    <input type="text" placeholder="작성자" value={author} onChange={e => setAuthor(e.target.value)} />
                    {!id && <input type="password" placeholder="비밀번호" value={password} onChange={e => setPassword(e.target.value)} />}
                    <textarea placeholder="내용" value={content} onChange={e => setContent(e.target.value)} />
                    <div className="form-buttons">
                        <button 
                            type="button" 
                            className="button-common button-green" 
                            onClick={() => navigate(`/?page=${currentPage}`)} // ✅ 페이지 쿼리 유지
                        >
                            목록
                        </button>
                        <button type="submit" className="button-common">{id ? "수정" : "작성"}</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default PostFormPage;
