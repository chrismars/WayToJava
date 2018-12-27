package app.sys.uplaodPic.dao;

import app.common.dao.GenericEntityDao;
import app.sys.uplaodPic.vo.AqAttachmentEntity;
import org.hibernate.Query;

import javax.inject.Named;
import java.util.List;

@Named("uploadAttachmentDao")
public class uploadAttachmentDao extends GenericEntityDao<AqAttachmentEntity,Integer>{
    @Override
    protected Class<AqAttachmentEntity> entityClass() {
        return AqAttachmentEntity.class;
    }


}
