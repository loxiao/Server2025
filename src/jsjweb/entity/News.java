package jsjweb.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class News {
    @JSONField(ordinal = 1)
    private int n_id;         // 新闻ID
    @JSONField(ordinal = 2)
    private String n_title;   // 标题
    @JSONField(ordinal = 3)
    private String n_content; // 内容
    @JSONField(ordinal = 4)
    private String n_author;  // 作者
    @JSONField(ordinal = 5, format = "yyyy-MM-dd HH:mm:ss")
    private Date n_time;      // 发布时间
    @JSONField(ordinal = 6)
    private String n_pic;     // 图片路径
    @JSONField(ordinal = 7)
    private int n_type;       // 新闻类型（1-国内 2-国际 3-科技等）

    // 构造方法
    public News() {}

    public News(int n_id, String n_title, String n_content, String n_author, Date n_time, String n_pic, int n_type) {
        this.n_id = n_id;
        this.n_title = n_title;
        this.n_content = n_content;
        this.n_author = n_author;
        this.n_time = n_time;
        this.n_pic = n_pic;
        this.n_type = n_type;
    }

    // getter和setter
    public int getN_id() { return n_id; }
    public void setN_id(int n_id) { this.n_id = n_id; }
    public String getN_title() { return n_title; }
    public void setN_title(String n_title) { this.n_title = n_title; }
    public String getN_content() { return n_content; }
    public void setN_content(String n_content) { this.n_content = n_content; }
    public String getN_author() { return n_author; }
    public void setN_author(String n_author) { this.n_author = n_author; }
    public Date getN_time() { return n_time; }
    public void setN_time(Date n_time) { this.n_time = n_time; }
    public String getN_pic() { return n_pic; }
    public void setN_pic(String n_pic) { this.n_pic = n_pic; }
    public int getN_type() { return n_type; }
    public void setN_type(int n_type) { this.n_type = n_type; }

    @Override
    public String toString() {
        return "News{" +
                "n_id=" + n_id +
                ", n_title='" + n_title + '\'' +
                ", n_content='" + n_content + '\'' +
                ", n_author='" + n_author + '\'' +
                ", n_time=" + n_time +
                ", n_pic='" + n_pic + '\'' +
                ", n_type=" + n_type +
                '}';
    }
}