// frontend/src/PostListPage.jsx
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import '../styles/PostList.scss';
import { FaCommentDots, FaThumbsUp, FaThumbsDown } from "react-icons/fa";

function PostListPage() {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const queryPage = parseInt(queryParams.get("page")) || 1;

  const [posts, setPosts] = useState([]);
  const [page, setPage] = useState(queryPage);
  const [totalPages, setTotalPages] = useState(1);
  const [searchQuery, setSearchQuery] = useState("");
  const [searchType, setSearchType] = useState("all");
  const [tab, setTab] = useState("all"); // 전체글 / 인기글 탭 상태

  // 서버에서 게시글 가져오기
  const fetchPosts = async (p = 1, query = "", type = "all", currentTab = "all") => {
    try {
      const res = await axios.get(`http://localhost:5000/api/posts`, {
        params: { page: p, limit: 5, search: query, searchType: type, tab: currentTab }
      });

      setPosts(res.data.posts);
      setPage(res.data.page);
      setTotalPages(res.data.totalPages);
      navigate(`/?page=${res.data.page}`, { replace: true });
    } catch (err) {
      console.error(err);
      alert("게시글 불러오기 실패");
    }
  };

  useEffect(() => {
    fetchPosts(queryPage, searchQuery, searchType, tab);
  }, [location.search, tab]);

  const handlePageChange = (p) => {
    if (p < 1) p = 1;
    if (p > totalPages) p = totalPages;
    fetchPosts(p, searchQuery, searchType, tab);
  };

  const handleSearch = (e) => {
    e.preventDefault();
    fetchPosts(1, searchQuery, searchType, tab);
  };

  const getPageNumbers = () => {
    let start = Math.max(page - 2, 1);
    let end = Math.min(start + 4, totalPages);
    start = Math.max(end - 4, 1);
    const pages = [];
    for (let i = start; i <= end; i++) pages.push(i);
    return pages;
  };

  return (
    <div className="board-container">
      <h1 
        style={{ cursor: "pointer" }} 
        onClick={() => handlePageChange(1)}
      >
        게시판
      </h1>
      <div className="post-list-box">
        {/* 탭 + 검색창 한 줄로 */}
        <div className="top-bar">
          <div className="tab-container">
            <button className={`tab-button ${tab === "all" ? "active" : ""}`} onClick={() => setTab("all")}>전체글</button>
            <button className={`tab-button ${tab === "popular" ? "active" : ""}`} onClick={() => setTab("popular")}>인기글</button>
          </div>

          <form className="search-form" onSubmit={handleSearch}>
            <div className="search-group" role="search">
              <select value={searchType} onChange={e => setSearchType(e.target.value)}>
                <option value="all">제목 + 내용</option>
                <option value="title">제목만</option>
                <option value="content">내용만</option>
                <option value="author">작성자</option>
              </select>
              <input type="text" placeholder="검색어를 입력해주세요" value={searchQuery} onChange={e => setSearchQuery(e.target.value)} />
              <button type="submit" className="button-common">검색</button>
            </div>
          </form>
        </div>

        {posts.length === 0 ? (
          <p style={{ textAlign: 'center', margin: '2rem 0' }}>검색 결과가 없습니다.</p>
        ) : (
          posts.map(post => {
            const likeScore = (post.likes || 0) - (post.dislikes || 0);
            const isNegative = likeScore < -10;
            return (
              <div key={post.id} className="post-item" onClick={() => navigate(`/post/${post.id}?page=${page}`)} >
                <div className="post-main">
                  <div className="post-text">
                    <h3>{post.title}</h3>
                    <small>
                      작성자: {post.author} | 작성일: {new Date(post.created_at).toLocaleString()} | 조회수: {post.views || 0}
                      {post.updated_at && post.updated_at !== post.created_at ? " (수정됨)" : ""}
                    </small>
                  </div>
                  <div className="post-stats">
                    <span className={`likes ${isNegative ? 'negative' : likeScore >= 10 ? 'high' : ''}`}>
                      {isNegative ? <FaThumbsDown className="like-icon" /> : <FaThumbsUp className="like-icon" />}
                      {likeScore}
                    </span>
                    <span className="comments">
                      <FaCommentDots className="comment-icon" /> {post.comment_count || 0}
                    </span>
                  </div>
                </div>
              </div>
            );
          })
        )}

        <div className="pagination">
          <button className="button-pagination" onClick={() => handlePageChange(1)} disabled={page === 1}>처음</button>
          <button className="button-pagination" onClick={() => handlePageChange(page - 1)} disabled={page === 1}>이전</button>
          {getPageNumbers().map(p => (
            <button key={p} className={`button-pagination ${p === page ? 'active' : ''}`} onClick={() => handlePageChange(p)}>
              {p}
            </button>
          ))}
          <button className="button-pagination" onClick={() => handlePageChange(page + 1)} disabled={page === totalPages}>다음</button>
          <button className="button-pagination" onClick={() => handlePageChange(totalPages)} disabled={page === totalPages}>마지막</button>
        </div>

        <button className="write-btn" onClick={() => navigate("/write")}>글 작성</button>
      </div>
    </div>
  );
}

export default PostListPage;
