package jsjweb.service.impl;

import jsjweb.dao.NewsDao;
import jsjweb.dao.impl.NewsDaoImpl;
import jsjweb.entity.News;
import jsjweb.service.NewsService;
import java.sql.SQLException;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao = new NewsDaoImpl();

    @Override
    public boolean publish(News news) {
        try {
            return newsDao.insert(news) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String delete(int id) {
        try {
            return newsDao.delete(id) > 0 ? "yes" : "no";
        } catch (SQLException e) {
            e.printStackTrace();
            return "no";
        }
    }

    @Override
    public String update(News news) {
        try {
            return newsDao.update(news) > 0 ? "yes" : "no";
        } catch (SQLException e) {
            e.printStackTrace();
            return "no";
        }
    }

    @Override
    public News getNewsById(int id) {
        try {
            return newsDao.select(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<News> getNewsByType(int type) {
        try {
            return newsDao.selectByType(type);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<News> getAllNews(int page, int pageSize) {
        try {
            return newsDao.selectAll(page, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<News> searchNews(String keyword) {
        try {
            return newsDao.search(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}