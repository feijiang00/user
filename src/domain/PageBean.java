package domain;

import java.util.List;

/**
 * 实现逻辑：
 *      用户的输入：1.currentPage 2.rows
 *      数据库分页查询需要两个坐标，一个是当前开始的行数，一个是每页展示的行数
 *      这里注意，（start = currentPage - 1） * rows
 *
 */
public class PageBean<T> {
    private int totalCount;//总记录数
    private  int totalPage;//总页码数 = 总记录数 % 每页的显示条数 == 0 ？ 总记录数 / 每页显示条数 : 总记录数 / 每页显示条数 + 1
    private List<T> list;//每页数据的list集合
    private int currentPage;//当前页码
    private  int rows;//每条显示的页数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}

/**
 * 关于limit分页查询：
 *      select * from student limit 0,3; 查询第一页
 *      select * from student limit 3,3; 查询第二页
 *      select * from student limit 6,3; 查询第二页
 *
 *      两个参数，一个是当前的页码currentPage 一个是每页显示的条数rows
 */
