package jsjweb.dao.impl;

import jsjweb.dao.NewsDao;
import jsjweb.entity.News;
import jsjweb.utils.MyUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public int insert(News news) throws SQLException {
        Connection conn = MyUtils.getConnection();
        String sql = "insert into news(n_title, n_content, n_author, n_time, n_pic, n_type) values(?,?,?,?,?,?)";
        Object[] params = {
                news.getN_title(), news.getN_content(), news.getN_author(),
                news.getN_time(), news.getN_pic(), news.getN_type()
        };
        int row = queryRunner.update(conn, sql, params);
        MyUtils.closeAll(null, null, conn);
        return row;
    }

    @Override
    public int delete(int id) throws SQLException {
        Connection conn = MyUtils.getConnection();
        String sql = "delete from news where n_id=?";
        int row = queryRunner.update(conn, sql, id);
        MyUtils.closeAll(null, null, conn);
        return row;
    }

    @Override
    public int update(News news) throws SQLException {
        Connection conn = MyUtils.getConnection();
        String sql = "update news set n_title=?, n_content=?, n_author=?, n_time=?, n_pic=?, n_type=? where n_id=?";
        Object[] params = {
                news.getN_title(), news.getN_content(), news.getN_author(),
                news.getN_time(), news.getN_pic(), news.getN_type(), news.getN_id()
        };
        int row = queryRunner.update(conn, sql, params);
        MyUtils.closeAll(null, null, conn);
        return row;
    }

    @Override
    public News select(int id) throws SQLException {
        Connection conn = MyUtils.getConnection();
        String sql = "select * from news where n_id=?";
        News news = queryRunner.query(conn, sql, new BeanHandler<>(News.class), id);
        MyUtils.closeAll(null, null, conn);
        return news;
    }

    @Override
    public List<News> selectByType(int type) throws SQLException {
        Connection conn = MyUtils.getConnection();
        String sql = "select * from news where n_type=? order by n_time desc";
        List<News> list = queryRunner.query(conn, sql, new BeanListHandler<>(News.class), type);
        MyUtils.closeAll(null, null, conn);
        return list;
    }

    @Override
    public List<News> selectAll(int page, int pageSize) throws SQLException {
        Connection conn = MyUtils.getConnection();
        int start = (page - 1) * pageSize;
        String sql = "select * from news order by n_time desc limit ?,?";
        List<News> list = queryRunner.query(conn, sql, new BeanListHandler<>(News.class), start, pageSize);
        MyUtils.closeAll(null, null, conn);
        return list;
    }

    @Override
    public List<News> search(String keyword) throws SQLException {
        Connection conn = MyUtils.getConnection();
        String sql = "select * from news where n_title like ? order by n_time desc";
        List<News> list = queryRunner.query(conn, sql, new BeanListHandler<>(News.class), "%" + keyword + "%");
        MyUtils.closeAll(null, null, conn);
        return list;
    }
}