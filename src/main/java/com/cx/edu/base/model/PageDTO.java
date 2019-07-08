package com.cx.edu.base.model;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 6303263416499498630L;

    /**
     * 总页数
     */
    private Integer total;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 总记录数
     */
    private Long records;

    /**
     * 数据
     */
    private Object data;

    public PageDTO(PageInfo pageInfo, Object data) {
        this.total = pageInfo.getPages();
        this.page = pageInfo.getPageNum();
        this.records = pageInfo.getTotal();
        this.data = data;
    }

}
