package app.sys.uplaodPic.dao;

import app.common.dao.GenericEntityDao;
import app.sys.uplaodPic.action.mobileEntity.AttachmentAndLocation;
import app.sys.uplaodPic.vo.AqAttachmentEntity;
import app.sys.uplaodPic.vo.AqObjdetailEntity;
import app.sys.uplaodPic.vo.AqObjectEntity;
import app.sys.uplaodPic.vo.AqObjectattributeEntity;
import org.hibernate.Query;
import org.springframework.jdbc.object.SqlQuery;

import javax.inject.Named;
import java.util.List;

@Named("uploadAqObjectDao")
public class uploadAqObjectDao extends GenericEntityDao<AqObjectEntity,String>{
    @Override
    protected Class<AqObjectEntity> entityClass() {
        return AqObjectEntity.class;
    }

    public List<AqObjectEntity> getAqObjectEntity(Integer admuId) {
        String hql="select ao from AqObjectEntity ao " +
                "where ao.admuId=:ADMUID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("ADMUID",admuId);
        List<AqObjectEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public AqObjectEntity alterAqObjectEntity(Integer objId) {
        String hql="select ao from AqObjectEntity ao " +
                "where ao.objId=:OBJID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List<AqObjectEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public List getAqObjtailEntity(Integer objId) {
        String hql="select aot.dtlId,aot.name,aot.memo,aot.objId,AsText(aot.location),aot.type,aot.oaId from aq_objdetail aot " +
                "where aot.objId=:OBJID and aot.type=:TYPE";
        List<AqObjdetailEntity> list = this.getCurrentSession().createSQLQuery(hql).setParameter("OBJID",objId).setParameter("TYPE",String.valueOf(1)).list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public List getAttributeAqObjdetailEntity(Integer oaId) {
        String hql="select aot.dtlId,aot.name,aot.memo,aot.objId,AsText(aot.location),aot.type,aot.oaId from aq_objdetail aot " +
                "where aot.oaId=:OAID and aot.type=:TYPE";
        List<AqObjdetailEntity> list = this.getCurrentSession().createSQLQuery(hql).setParameter("OAID",oaId).setParameter("TYPE",String.valueOf(2)).list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public List<AqObjectattributeEntity> getAqObejctAttributeEntity(Integer objId) {
        String hql="select ao from AqObjectattributeEntity ao " +
                "where ao.objId=:OBJID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List<AqObjectattributeEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public AqObjdetailEntity alterAqObjtailEntity(Integer dtlId) {
        String hql="select aot from AqObjdetailEntity aot " +
                "where aot.dtlId=:DTLID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("DTLID",dtlId);
        List<AqObjdetailEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }


    public List getAqAttachmentAndLocation(Integer objId,Integer type) {
        String hql="select at.attId,at.name,at.url,at.type,at.tableId,aot.dtlId,aot.name aotName,aot.memo,aot.objId,AsText(aot.location),aot.type aotType,aot.oaId from aq_attachment at,aq_objdetail aot " +
                "where at.tableId=aot.dtlId and aot.type=3 and aot.objId=:OBJID and at.type=:TYPE ";
        Query q=this.getCurrentSession().createSQLQuery(hql).setParameter("OBJID",objId).setParameter("TYPE",type);
        List list=  q.list();
        if (list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List getAttributeAqAttachmentAndLocation(Integer oaId) {
        String hql="select at.attId,at.name,at.url,at.type,at.tableId,aot.dtlId,aot.name aotName,aot.memo,aot.objId,AsText(aot.location),aot.type aotType,aot.oaId from aq_attachment at,aq_objdetail aot " +
                "where at.tableId=aot.dtlId and aot.type=3 and aot.oaId=:OAID and at.type=2 ";
        Query q=this.getCurrentSession().createSQLQuery(hql).setParameter("OAID",oaId);
        List list=  q.list();
        if (list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public AqAttachmentEntity alterAqAttachment(Integer attId) {
        String hql="select at from AqAttachmentEntity at " +
                "where at.attId=:ATTID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("ATTID",attId);
        List<AqAttachmentEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public List getDeleteUrl(Integer objId){
        String hql="select at.url from AqObjectEntity ao,AqObjdetailEntity aot,AqAttachmentEntity at " +
                "where ao.objId=:OBJID and aot.objId=ao.objId and at.tableId=aot.dtlId";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List list=q.list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List getDtlId(Integer objId){
        String hql="select aot.dtlId from AqObjectEntity ao,AqObjdetailEntity aot " +
                "where ao.objId=:OBJID and aot.objId=ao.objId";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List list=q.list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List getOaId(Integer objId){
        String hql="select aoa.oaId from AqObjectattributeEntity aoa " +
                "where aoa.objId=:OBJID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List list=q.list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }


    /*public List getCesiumObjectMessage(){
        String hql="select ao.name,ao.address,ao.createTime,AsText(aot.location),ao.objId from aq_object ao,aq_objdetail aot " +
                "where aot.objId=ao.objId and aot.type=:TYPE";
        List<AqObjdetailEntity> list = this.getCurrentSession().createSQLQuery(hql).setParameter("TYPE",String.valueOf(1)).list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }*/

    public List getCesiumObjectMessage(){
        String hql="select ao.name,ao.address,ao.createTime,AsText(ao.location),ao.objId,ao.url from objectMessage ao";
        List<AqObjdetailEntity> list = this.getCurrentSession().createSQLQuery(hql).list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public List getObjectUrl(Integer objId) {
        String hql="select aa.url from  AqAttachmentEntity aa,AqObjdetailEntity aot " +
                "where aa.tableId=aot.dtlId and aa.type=1 and aot.objId=:OBJID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OBJID",objId);
        List<AqObjectEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    /*public List getAttributeMessage(Integer objId){
        String hql="select aoa.name,ao.address,ao.createTime,AsText(aot.location),aot.oaId from aq_object ao,aq_objdetail aot,aq_objectattribute aoa " +
                "where aot.objId=:OBJID and aot.objId=ao.objId and aot.type=:TYPE and aoa.objId=:OBJID";
        List<AqObjdetailEntity> list = this.getCurrentSession().createSQLQuery(hql).setParameter("TYPE",String.valueOf(2)).setParameter("OBJID",objId).list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }*/

    public List getAttributeMessage(Integer objId){
        String hql="select a.name,a.address,a.createTime,AsText(a.location),a.oaId,a.url from attributeMessage a where a.objId=:OBJID";
        List<AqObjdetailEntity> list = this.getCurrentSession().createSQLQuery(hql).setParameter("OBJID",objId).list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    public List getAttributeUrl(Integer oaId) {
        String hql="select aa.url from AqObjdetailEntity aot, AqAttachmentEntity aa " +
                "where aot.dtlId=aa.tableId and aot.oaId=:OAID";
        Query q=this.getCurrentSession().createQuery(hql).setParameter("OAID",oaId);
        List<AqObjectEntity> list=q.list();
        if (list!=null && list.size()>0){
            return list;
        }
        return null;
    }
}
