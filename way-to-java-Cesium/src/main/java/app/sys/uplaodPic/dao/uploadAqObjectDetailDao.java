package app.sys.uplaodPic.dao;

import app.common.dao.GenericEntityDao;
import app.sys.uplaodPic.vo.AqObjdetailEntity;
import org.hibernate.Query;

import javax.inject.Named;
import java.util.List;

@Named("uploadAqObjectDetailDao")
public class uploadAqObjectDetailDao extends GenericEntityDao<AqObjdetailEntity,Integer>{
    @Override
    protected Class<AqObjdetailEntity> entityClass() {
        return AqObjdetailEntity.class;
    }

    public void insertDetails(String insertSql){
        if(insertSql == null){
            return;
        }
        this.getCurrentSession().createSQLQuery(insertSql).executeUpdate();
    }

    public Integer getTheLatestDtlId(){
        String hql="select max(a.dtlId) from AqObjdetailEntity a";
        Query q=this.getCurrentSession().createQuery(hql);
        Integer dtlId= (Integer) q.list().get(0);
        if(dtlId!=null){
            return dtlId;
        }
        return null;
    }
}
