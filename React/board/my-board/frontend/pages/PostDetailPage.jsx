// frontend/src/PostDetailPage.jsx
import { useEffect, useState } from "react";
import { useNavigate, useParams, useLocation } from "react-router-dom";
import axios from "axios";
import "../styles/PostDetail.scss";

const API_URL = "http://localhost:5000/api";

function PostDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const currentPage = queryParams.get("page") || 1;

  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);
  const [commentForm, setCommentForm] = useState({
    author: "익명",
    password: "",
    content: "",
  });

  const [editingCommentId, setEditingCommentId] = useState(null);
  const [editingContent, setEditingContent] = useState("");
  const [editingPassword, setEditingPassword] = useState("");

  const [likes, setLikes] = useState(0);
  const [dislikes, setDislikes] = useState(0);
  const [views, setViews] = useState(0); // 조회수 상태

  useEffect(() => {
    if (!id) return;
    fetchPost();
    fetchComments();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  // 글 정보 가져오기 (조회수는 별도 API로 증가시키는 방식 — 세션 제한 제거: 매 번 증가)
  const fetchPost = async () => {
    try {
      const res = await axios.get(`${API_URL}/posts/${id}`);
      setPost(res.data);
      setLikes(res.data.likes || 0);
      setDislikes(res.data.dislikes || 0);
      setViews(res.data.views || 0);

      // 조회수는 세션 제한 없이, 글을 열 때마다 증가
      incrementView();
    } catch (err) {
      console.error(err);
    }
  };

  // 조회수 증가 — 세션 제한 제거 (매번 호출될 때마다 증가)
  const incrementView = async () => {
    try {
      const res = await axios.post(`${API_URL}/posts/${id}/view`);
      if (res?.data?.views !== undefined) {
        setViews(res.data.views);
        // post 객체에도 반영
        setPost((p) => (p ? { ...p, views: res.data.views } : p));
      } else {
        // 안전망: 로컬에서 +1
        setViews((v) => (v ?? 0) + 1);
        setPost((p) => (p ? { ...p, views: (p.views ?? 0) + 1 } : p));
      }
    } catch (err) {
      console.error("조회수 증가 실패", err);
    }
  };

  const fetchComments = async () => {
    try {
      const res = await axios.get(`${API_URL}/posts/${id}/comments`);
      if (Array.isArray(res.data)) setComments(res.data);
      else setComments(res.data.comments || []);
    } catch (err) {
      console.error("댓글 불러오기 실패", err);
    }
  };

  if (!post) return <div>로딩 중...</div>;

  // ---------------- 포스트 관련 ----------------
  const handleEditPost = async () => {
    const password = prompt("비밀번호를 입력하세요.");
    if (!password) return;
    try {
      await axios.post(`${API_URL}/posts/${id}/check-password`, { password });
      navigate(`/edit/${id}?page=${currentPage}`);
    } catch (err) {
      alert(err.response?.data?.error || "비밀번호가 틀렸습니다.");
    }
  };

  const handleDeletePost = async () => {
    const password = prompt("비밀번호를 입력하세요.");
    if (!password) return;
    try {
      await axios.delete(`${API_URL}/posts/${id}`, { data: { password } });
      alert("삭제 완료");
      navigate(`/?page=${currentPage}`);
    } catch (err) {
      alert(err.response?.data?.error || "삭제 실패 또는 비밀번호가 틀렸습니다.");
    }
  };

  // ---------------- 추천/비추천 ----------------
  const handleLike = async () => {
    try {
      const res = await axios.post(`${API_URL}/posts/${id}/like`);
      setLikes(res.data.likes);
      setDislikes(res.data.dislikes);
    } catch (err) {
      console.error(err);
      alert("추천 실패");
    }
  };

  const handleDislike = async () => {
    try {
      const res = await axios.post(`${API_URL}/posts/${id}/dislike`);
      setLikes(res.data.likes);
      setDislikes(res.data.dislikes);
    } catch (err) {
      console.error(err);
      alert("비추천 실패");
    }
  };

  // ---------------- 댓글 관련 ----------------
  const handleCommentInput = (e) => {
    const { name, value } = e.target;
    setCommentForm((prev) => ({ ...prev, [name]: value }));
  };

  const submitComment = async (e) => {
    e.preventDefault();
    const { author, password, content } = commentForm;
    if (!content || !password) {
      alert("댓글 내용과 비밀번호는 필수입니다.");
      return;
    }
    try {
      await axios.post(`${API_URL}/posts/${id}/comments`, { author, password, content });
      setCommentForm({ author: "익명", password: "", content: "" });
      fetchComments();
    } catch (err) {
      console.error(err);
      alert(err.response?.data?.error || "댓글 작성 실패");
    }
  };

  const startEditComment = async (commentId) => {
    if (editingCommentId === commentId) return;
    const pw = prompt("댓글 비밀번호를 입력하세요.");
    if (!pw) return;
    try {
      await axios.post(`${API_URL}/comments/${commentId}/check-password`, { password: pw });
      const comment = comments.find((c) => c.id === commentId);
      setEditingCommentId(commentId);
      setEditingContent(comment.content);
      setEditingPassword(pw);
    } catch (err) {
      alert(err.response?.data?.error || "비밀번호가 틀렸습니다.");
    }
  };

  const submitEditComment = async (commentId) => {
    if (!editingContent) return alert("내용을 입력하세요.");
    try {
      await axios.put(`${API_URL}/comments/${commentId}`, {
        content: editingContent,
        password: editingPassword,
      });
      setEditingCommentId(null);
      setEditingContent("");
      setEditingPassword("");
      fetchComments();
    } catch (err) {
      alert(err.response?.data?.error || "수정 실패");
    }
  };

  const deleteComment = async (commentId) => {
    const password = prompt("댓글 비밀번호를 입력하세요.");
    if (!password) return;
    try {
      await axios.delete(`${API_URL}/comments/${commentId}`, { data: { password } });
      fetchComments();
    } catch (err) {
      alert(err.response?.data?.error || "삭제 실패");
    }
  };

  return (
    <div className="detail-container">
      <div className="detail-box">
        <h1>{post.title}</h1>
        <small>
          작성자: {post.author} | 작성일: {new Date(post.created_at).toLocaleString()}
          {post.updated_at && post.updated_at !== post.created_at ? " (수정됨)" : ""}
          <span style={{ marginLeft: 12, color: "#6b7280", fontSize: "0.95rem" }}>
            조회수: {views}
          </span>
        </small>
        <p>{post.content}</p>

        <div className="post-reactions centered">
          <button className="like-btn" onClick={handleLike}>👍 {likes}</button>
          <button className="dislike-btn" onClick={handleDislike}>👎 {dislikes}</button>
        </div>

        <div className="buttons">
          <button className="button-common button-green list-btn" onClick={() => navigate(`/?page=${currentPage}`)}>
            목록
          </button>
          <div className="right-buttons">
            <button className="button-common button-red delete-btn" onClick={handleDeletePost}>
              삭제
            </button>
            <button className="button-common edit-btn" onClick={handleEditPost}>
              수정
            </button>
          </div>
        </div>

        <div className="comments-section">
          <h2>
            댓글 <span className="comment-count">({comments.length})</span>
          </h2>

          <div className="comment-list">
            {comments.length === 0 ? (
              <p>댓글이 없습니다.</p>
            ) : (
              comments.map((c) => (
                <div key={c.id} className="comment-item">
                  <div className="comment-meta">
                    <strong>{c.author}</strong>
                    <span>
                      {new Date(c.created_at).toLocaleString()}
                      {c.updated_at && c.updated_at !== c.created_at ? " (수정됨)" : ""}
                    </span>
                  </div>

                  {editingCommentId === c.id ? (
                    <div className="comment-edit">
                      <textarea
                        value={editingContent}
                        onChange={(e) => setEditingContent(e.target.value)}
                      />
                      <div>
                        <span
                          className="comment-action-text save"
                          onClick={() => submitEditComment(c.id)}
                        >
                          저장
                        </span>
                        <span
                          className="comment-action-text cancel"
                          onClick={() => {
                            setEditingCommentId(null);
                            setEditingContent("");
                            setEditingPassword("");
                          }}
                        >
                          취소
                        </span>
                      </div>
                    </div>
                  ) : (
                    <>
                      <div className="comment-body">
                        <p>{c.content}</p>
                      </div>
                      <div className="comment-actions">
                        <span className="comment-action-text edit" onClick={() => startEditComment(c.id)}>
                          수정
                        </span>
                        <span className="comment-action-text delete" onClick={() => deleteComment(c.id)}>
                          삭제
                        </span>
                      </div>
                    </>
                  )}
                </div>
              ))
            )}
          </div>

          <form className="comment-form" onSubmit={submitComment}>
            <div className="author-password">
              <input
                name="author"
                value={commentForm.author}
                onChange={handleCommentInput}
                placeholder="작성자 (기본: 익명)"
              />
              <input
                name="password"
                type="password"
                value={commentForm.password}
                onChange={handleCommentInput}
                placeholder="비밀번호"
              />
            </div>
            <textarea
              name="content"
              value={commentForm.content}
              onChange={handleCommentInput}
              placeholder="댓글 내용을 입력하세요"
            />
            <div className="comment-form-btns">
              <button type="submit" className="button-common">
                댓글 작성
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default PostDetailPage;
