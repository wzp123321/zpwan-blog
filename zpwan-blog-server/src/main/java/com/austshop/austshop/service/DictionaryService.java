package com.austshop.austshop.service;

import com.austshop.austshop.entity.DictionaryInfo;
import com.austshop.austshop.mapper.DicMapper;
import com.austshop.austshop.service.impl.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * zpwan
 * 2019/3/19
 */
@Service(value = "dictionarySerice")
public class DictionaryService implements IDictionaryService {

    @Autowired
    private DicMapper dicMapper;

    /**
     * 新增字典
     *
     * @param code
     * @param value
     * @param description
     * @param type
     * @param pId
     * @return
     */
    public boolean getDictionaryInsert(String code, String value, Integer type, String description, Integer pId) {
        int newpId = 0;
        String newPCode = "";
        String newPValue = "";
        /**
         * 如果时二级目录
         */
        if (type == 2) {
            DictionaryInfo dictionary = dicMapper.getDictionaryInfoById(pId);
            newpId = type == 1 ? 0 : pId;
            newPCode = type == 1 ? "" : dictionary.getCode();
            newPValue = type == 1 ? "" : dictionary.getValue();
        }
        DictionaryInfo dictionaryInfo = new DictionaryInfo();
        dictionaryInfo.setCode(code);
        dictionaryInfo.setValue(value);
        dictionaryInfo.setType(type);
        dictionaryInfo.setDescription(description);
        dictionaryInfo.setPId(newpId);
        dictionaryInfo.setPCode(newPCode);
        dictionaryInfo.setPValue(newPValue);
        dictionaryInfo.setCreate_time(new Date());
        dictionaryInfo.setModify_time(new Date());
        return dicMapper.insertSelective(dictionaryInfo) > 0;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param type
     * @return
     */
    public List<DictionaryInfo> getDictionaryList(Integer page, Integer limit, Integer type, Integer pId) {
        Integer skip = page > 0 ? (page - 1) * limit : 0;
        return dicMapper.getDictionaryList(skip, limit, type, pId);
    }

    /**
     * 获取total
     *
     * @param type
     * @return
     */
    public int getDictionaryTotal(Integer type) {
        return dicMapper.getDicTotal(type);
    }

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    public DictionaryInfo getDictionaryInfoById(Integer id) {
        return dicMapper.getDictionaryInfoById(id);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean getDicInfoDel(Integer id) {
        return dicMapper.getDictionaryInfoDelete(id) > 0;
    }

    /**
     * 编辑字典
     *
     * @param value
     * @param type
     * @param description
     * @param pId
     * @return
     */
    public boolean getDictionaryInfoUpdate(Integer id,String code, String value, Integer type, String description, Integer pId) {
        int newpId = 0;
        String newPCode = "";
        String newPValue = "";
        DictionaryInfo dictionaryInfo = new DictionaryInfo();
        /**
         * 如果传了type==1 说明是一级目录  清空父级相关数据
         * 如果传了type==2 说明是一级目录 那么根据pid来设置父级相关数据
         */
        if (type == 1) {
            dictionaryInfo.setPId(0);
            dictionaryInfo.setPCode("");
            dictionaryInfo.setPValue("");
        } else {
            DictionaryInfo dictionary = dicMapper.getDictionaryInfoById(pId);
            dictionaryInfo.setPId(pId);
            dictionaryInfo.setPCode(dictionary.getCode());
            dictionaryInfo.setPValue(dictionary.getValue());
        }
        dictionaryInfo.setId(id);
        dictionaryInfo.setCode(code);
        dictionaryInfo.setValue(value);
        dictionaryInfo.setType(type);
        dictionaryInfo.setDescription(description);
        dictionaryInfo.setModify_time(new Date());
        return dicMapper.updatetSelective(dictionaryInfo) > 0;
    }
}
