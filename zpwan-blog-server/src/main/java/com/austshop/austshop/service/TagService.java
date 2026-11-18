package com.austshop.austshop.service;

import com.austshop.austshop.entity.TagInfo;
import com.austshop.austshop.mapper.TagMapper;
import com.austshop.austshop.service.impl.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "tagService")
public class TagService implements ITagService {

    @Autowired
    public TagMapper tagMapper;

    /**
     * 新增tag
     *
     * @param code
     * @param value
     * @return
     */
    public boolean getTagInsert(String code, String value) {
        TagInfo tagInfo = new TagInfo();
        tagInfo.setCode(code);
        tagInfo.setValue(value);
        tagInfo.setCreate_time(new Date());
        tagInfo.setModify_time(new Date());
        return tagMapper.tagInsertSelective(tagInfo) > 0;
    }

    /**
     * 查询
     *
     * @param page
     * @param limit
     * @return
     */
    public List<TagInfo> getTagList(Integer page, Integer limit) {
        int skip = page > 1 ? (page - 1) * limit : 0;
        return tagMapper.getTagList(skip, limit);
    }

    /**
     * 获取总数
     *
     * @return
     */
    public int getTagListTotal() {
        return tagMapper.gettotal();
    }

    /**
     * 删除tag
     * @param id
     * @return
     */
    public boolean getTagDelete(Integer id) {
        return tagMapper.getTagDelete(id) > 0;
    }
}
