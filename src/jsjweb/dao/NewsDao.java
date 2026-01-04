package jsjweb.dao;

import jsjweb.entity.News;
import java.sql.SQLException;
import java.util.List;

public interface NewsDao {
    // 添加新闻
    int insert(News news) throws SQLException;
    // 删除新闻
    int delete(int id) throws SQLException;
    // 更新新闻
    int update(News news) throws SQLException;
    // 根据ID查询
    News select(int id) throws SQLException;
    // 根据类型查询
    List<News> selectByType(int type) throws SQLException;
    // 分页查询所有新闻
    List<News> selectAll(int page, int pageSize) throws SQLException;
    // 搜索新闻（标题关键字）
    List<News> search(String keyword) throws SQLException;
}