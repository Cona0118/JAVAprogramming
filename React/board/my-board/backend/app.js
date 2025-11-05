// backend/app.js
const express = require("express");
const cors = require("cors");
const mysql = require("mysql2/promise");
const bcrypt = require("bcrypt");

const app = express();
const port = 5000;

app.use(cors());
app.use(express.json());

const connection = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "univ301",
    database: "board_db",
});

// 요청 로깅 (개발용)
app.use((req, res, next) => {
    console.log(`[${new Date().toISOString()}] ${req.method} ${req.originalUrl}`);
    next();
});

// helper: LIKE 특수문자 이스케이프
function escapeLike(str) {
    if (typeof str !== "string") return str;
    return str.replace(/\\/g, "\\\\").replace(/%/g, "\\%").replace(/_/g, "\\_");
}

/**
 * GET /api/posts
 * Query:
 *   - page (default 1)
 *   - limit (default 5)
 *   - search (optional)
 *   - searchType: all | title | content | author  (default all)
 *   - 댓글 수 포함
 */
app.get('/api/posts', async (req, res) => {
    try {
        let page = parseInt(req.query.page, 10);
        let limit = parseInt(req.query.limit, 10);
        const rawSearch = (req.query.search || "").toString();
        const searchType = (req.query.searchType || "all").toString();

        if (isNaN(page) || page < 1) page = 1;
        if (isNaN(limit) || limit < 1) limit = 5;
        const offset = (page - 1) * limit;

        // 댓글 수 포함 SQL
        let selectSql = `
            SELECT p.*, COUNT(c.id) AS comment_count
            FROM posts p
            LEFT JOIN comments c ON p.id = c.post_id
        `;
        const selectParams = [];

        if (rawSearch && rawSearch.trim() !== "") {
            const escaped = escapeLike(rawSearch.trim());
            const like = `%${escaped}%`;

            let whereClause = '';
            switch (searchType) {
                case 'title':
                    whereClause = " WHERE p.title LIKE ? ESCAPE '\\\\'";
                    selectParams.push(like);
                    break;
                case 'content':
                    whereClause = " WHERE p.content LIKE ? ESCAPE '\\\\'";
                    selectParams.push(like);
                    break;
                case 'author':
                    whereClause = " WHERE p.author LIKE ? ESCAPE '\\\\'";
                    selectParams.push(like);
                    break;
                case 'all':
                default:
                    whereClause = " WHERE p.title LIKE ? ESCAPE '\\\\' OR p.content LIKE ? ESCAPE '\\\\'";
                    selectParams.push(like, like);
                    break;
            }
            selectSql += whereClause;
        }

        selectSql += ` GROUP BY p.id ORDER BY p.id DESC LIMIT ${limit} OFFSET ${offset}`;

        const [rows] = await connection.execute(selectSql, selectParams);

        // 총 게시글 수
        const [countRows] = await connection.execute('SELECT COUNT(*) as total FROM posts');
        const total = countRows[0]?.total || 0;
        const totalPages = Math.max(1, Math.ceil(total / limit));

        res.json({ posts: rows || [], page, limit, total, totalPages });
    } catch (err) {
        console.error('Error in GET /api/posts:', err);
        res.status(500).json({ error: err.message });
    }
});

// 글 상세
app.get('/api/posts/:id', async (req, res) => {
    const { id } = req.params;
    try {
        const [rows] = await connection.execute('SELECT * FROM posts WHERE id = ?', [id]);
        if (!rows.length) return res.status(404).json({ error: "글이 없습니다." });
        res.json(rows[0]);
    } catch (err) {
        console.error('Error in GET /api/posts/:id', err);
        res.status(500).json({ error: err.message });
    }
});

// 글 작성
app.post('/api/posts', async (req, res) => {
    const { title, content, author, password } = req.body;
    if (!title || !content || !password) return res.status(400).json({ error: "제목, 내용, 비밀번호는 필수입니다." });

    const saveAuthor = author?.trim() || "익명";
    try {
        const hashedPassword = await bcrypt.hash(password, 10);
        const [result] = await connection.execute(
            'INSERT INTO posts (title, content, author, password) VALUES (?, ?, ?, ?)',
            [title, content, saveAuthor, hashedPassword]
        );
        res.json({ id: result.insertId });
    } catch (err) {
        console.error('Error in POST /api/posts', err);
        res.status(500).json({ error: err.message });
    }
});

// 비밀번호 확인 (수정 전용)
app.post('/api/posts/:id/check-password', async (req, res) => {
    const { id } = req.params;
    const { password } = req.body;
    if (!password) return res.status(400).json({ error: "비밀번호는 필수입니다." });

    try {
        const [rows] = await connection.execute('SELECT password FROM posts WHERE id = ?', [id]);
        if (!rows.length) return res.status(404).json({ error: "글이 없습니다." });

        const match = await bcrypt.compare(password, rows[0].password);
        if (!match) return res.status(403).json({ error: "비밀번호가 틀렸습니다." });

        res.json({ message: "비밀번호 확인 완료" });
    } catch (err) {
        console.error('Error in POST /api/posts/:id/check-password', err);
        res.status(500).json({ error: err.message });
    }
});

// 글 수정
app.put('/api/posts/:id', async (req, res) => {
    const { id } = req.params;
    const { title, content, password } = req.body;
    if (!title || !content) return res.status(400).json({ error: "제목과 내용은 필수입니다." });

    try {
        if (password) {
            const [rows] = await connection.execute('SELECT password FROM posts WHERE id = ?', [id]);
            if (!rows.length) return res.status(404).json({ error: "글이 없습니다." });

            const match = await bcrypt.compare(password, rows[0].password);
            if (!match) return res.status(403).json({ error: "비밀번호가 틀렸습니다." });
        }

        await connection.execute(
            'UPDATE posts SET title = ?, content = ?, updated_at = NOW() WHERE id = ?',
            [title, content, id]
        );
        res.json({ message: "수정 완료" });
    } catch (err) {
        console.error('Error in PUT /api/posts/:id', err);
        res.status(500).json({ error: err.message });
    }
});

// 글 삭제 (댓글도 같이 삭제)
app.delete('/api/posts/:id', async (req, res) => {
    const { id } = req.params;
    const { password } = req.body || {};

    if (!password) return res.status(400).json({ error: "비밀번호는 필수입니다." });

    try {
        const [rows] = await connection.execute('SELECT password FROM posts WHERE id = ?', [id]);
        if (!rows.length) return res.status(404).json({ error: "글이 없습니다." });

        const match = await bcrypt.compare(password, rows[0].password);
        if (!match) return res.status(403).json({ error: "비밀번호가 틀렸습니다." });

        // 댓글 삭제
        await connection.execute('DELETE FROM comments WHERE post_id = ?', [id]);
        // 게시글 삭제
        await connection.execute('DELETE FROM posts WHERE id = ?', [id]);

        res.json({ message: "삭제 완료" });
    } catch (err) {
        console.error('Error in DELETE /api/posts/:id', err);
        res.status(500).json({ error: err.message });
    }
});

// === COMMENTS API ===

// 댓글 목록 조회 (해당 게시글)
app.get('/api/posts/:postId/comments', async (req, res) => {
    const { postId } = req.params;
    try {
        const [rows] = await connection.execute(
            'SELECT id, post_id, content, author, created_at, updated_at FROM comments WHERE post_id = ? ORDER BY id ASC',
            [postId]
        );
        res.json({ comments: rows || [] });
    } catch (err) {
        console.error('Error in GET /api/posts/:postId/comments', err);
        res.status(500).json({ error: err.message });
    }
});

// 댓글 작성
app.post('/api/posts/:postId/comments', async (req, res) => {
    const { postId } = req.params;
    const { content, author, password } = req.body;

    if (!content || !password) return res.status(400).json({ error: '댓글 내용과 비밀번호는 필수입니다.' });

    const saveAuthor = author?.trim() === "" ? "익명" : (author || "익명");

    try {
        const hashed = await bcrypt.hash(password, 10);
        const [result] = await connection.execute(
            'INSERT INTO comments (post_id, content, author, password) VALUES (?, ?, ?, ?)',
            [postId, content, saveAuthor, hashed]
        );
        res.json({ id: result.insertId });
    } catch (err) {
        console.error('Error in POST /api/posts/:postId/comments', err);
        res.status(500).json({ error: err.message });
    }
});

// 댓글 비밀번호 확인
app.post('/api/comments/:id/check-password', async (req, res) => {
    const { id } = req.params;
    const { password } = req.body;
    if (!password) return res.status(400).json({ error: '비밀번호는 필수입니다.' });

    try {
        const [rows] = await connection.execute('SELECT password FROM comments WHERE id = ?', [id]);
        if (!rows.length) return res.status(404).json({ error: '댓글이 없습니다.' });

        const match = await bcrypt.compare(password, rows[0].password);
        if (!match) return res.status(403).json({ error: '비밀번호가 틀렸습니다.' });

        res.json({ message: '비밀번호 확인 완료' });
    } catch (err) {
        console.error('Error in POST /api/comments/:id/check-password', err);
        res.status(500).json({ error: err.message });
    }
});

// 댓글 수정
app.put('/api/comments/:id', async (req, res) => {
    const { id } = req.params;
    const { content, password } = req.body;
    if (!content) return res.status(400).json({ error: '내용은 필수입니다.' });

    try {
        const [rows] = await connection.execute('SELECT password FROM comments WHERE id = ?', [id]);
        if (!rows.length) return res.status(404).json({ error: '댓글이 없습니다.' });

        const match = await bcrypt.compare(password, rows[0].password);
        if (!match) return res.status(403).json({ error: '비밀번호가 틀렸습니다.' });

        await connection.execute('UPDATE comments SET content = ?, updated_at = NOW() WHERE id = ?', [content, id]);
        res.json({ message: '수정 완료' });
    } catch (err) {
        console.error('Error in PUT /api/comments/:id', err);
        res.status(500).json({ error: err.message });
    }
});

// 댓글 삭제
app.delete('/api/comments/:id', async (req, res) => {
    const { id } = req.params;
    const { password } = req.body || {};
    if (!password) return res.status(400).json({ error: '비밀번호는 필수입니다.' });

    try {
        const [rows] = await connection.execute('SELECT password FROM comments WHERE id = ?', [id]);
        if (!rows.length) return res.status(404).json({ error: '댓글이 없습니다.' });

        const match = await bcrypt.compare(password, rows[0].password);
        if (!match) return res.status(403).json({ error: '비밀번호가 틀렸습니다.' });

        await connection.execute('DELETE FROM comments WHERE id = ?', [id]);
        res.json({ message: '삭제 완료' });
    } catch (err) {
        console.error('Error in DELETE /api/comments/:id', err);
        res.status(500).json({ error: err.message });
    }
});

app.listen(port, () => {
    console.log(`Server running on http://localhost:${port}`);
});

// 게시글 추천
app.post('/api/posts/:id/like', async (req, res) => {
    const { id } = req.params;
    try {
        // updated_at 건드리지 않음
        await connection.execute('UPDATE posts SET likes = likes + 1 WHERE id = ?', [id]);
        const [rows] = await connection.execute('SELECT likes, dislikes FROM posts WHERE id = ?', [id]);
        res.json(rows[0]);
    } catch (err) {
        console.error('Error in POST /api/posts/:id/like', err);
        res.status(500).json({ error: err.message });
    }
});

// 게시글 비추천
app.post('/api/posts/:id/dislike', async (req, res) => {
    const { id } = req.params;
    try {
        // updated_at 건드리지 않음
        await connection.execute('UPDATE posts SET dislikes = dislikes + 1 WHERE id = ?', [id]);
        const [rows] = await connection.execute('SELECT likes, dislikes FROM posts WHERE id = ?', [id]);
        res.json(rows[0]);
    } catch (err) {
        console.error('Error in POST /api/posts/:id/dislike', err);
        res.status(500).json({ error: err.message });
    }
});
