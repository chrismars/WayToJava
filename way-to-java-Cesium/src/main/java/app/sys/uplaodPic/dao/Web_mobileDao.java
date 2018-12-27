package app.sys.uplaodPic.dao;

import app.common.dao.GenericEntityDao;
import app.sys.uplaodPic.vo.WebObjIdMobileIdEntity;
import org.hibernate.Query;

import javax.inject.Named;
import java.util.List;

@Named("Web_mobileDao")
public class Web_mobileDao extends GenericEntityDao<WebObjIdMobileIdEntity,Integer> {
    @Override
    protected Class<WebObjIdMobileIdEntity> entityClass() {
        return WebObjIdMobileIdEntity.class;
    }

    public String getMobileObjId(Integer objId){
        String hql="select w.mobileObjId from WebObjIdMobileIdEntity w where w.webObjId=:OBJID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List list= q.list();
        if (list!=null&&list.size()>0){
            String mobileObjId= (String) list.get(0);
            return mobileObjId;
        }
        return null;
    }
}
