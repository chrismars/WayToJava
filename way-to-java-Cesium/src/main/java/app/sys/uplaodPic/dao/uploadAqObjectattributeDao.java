package app.sys.uplaodPic.dao;

import app.common.dao.GenericEntityDao;
import app.sys.uplaodPic.vo.AqObjectattributeEntity;

import javax.inject.Named;

@Named("uploadAqObjectattributeDao")
public class uploadAqObjectattributeDao extends GenericEntityDao<AqObjectattributeEntity,String> {

    @Override
    protected Class<AqObjectattributeEntity> entityClass() {
        return AqObjectattributeEntity.class;
    }
}
