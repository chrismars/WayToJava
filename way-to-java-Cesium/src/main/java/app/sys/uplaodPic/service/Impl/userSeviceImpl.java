package app.sys.uplaodPic.service.Impl;

import app.sys.uplaodPic.action.mobileEntity.AttachmentAndLocation;
import app.sys.uplaodPic.dao.*;
import app.sys.uplaodPic.service.userService;
import app.sys.uplaodPic.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class userSeviceImpl implements userService {
    @Autowired
    userDao userDao;
    @Autowired
    uploadAqObjectDao uploadAqObjectDao;
    @Autowired
    uploadAqObjectDetailDao uploadAqObjectDetailDao;
    @Autowired
    uploadAttachmentDao uploadAttachmentDao;
    @Autowired
    uploadAqObjectattributeDao uploadAqObjectattributeDao;
    @Autowired
    Web_mobileDao web_mobileDao;

    @Override
    public UserMessageEntity saveUserMassage(UserMessageEntity userMessageEntity) {
        return userDao.saveOrUpdate(userMessageEntity);
    }

    @Override
    public AqObjectEntity saveAqObject(AqObjectEntity aqObjectEntity) {
        return uploadAqObjectDao.saveOrUpdate(aqObjectEntity);
    }

    @Override
    public AqObjdetailEntity saveAqObjectDetail(AqObjdetailEntity aqObjdetailEntity) {
        return uploadAqObjectDetailDao.saveOrUpdate(aqObjdetailEntity);
    }

    @Override
    public AqAttachmentEntity saveAqAttachment(AqAttachmentEntity aqAttachmentEntity) {
        return uploadAttachmentDao.saveOrUpdate(aqAttachmentEntity);
    }

    @Override
    public AqObjectattributeEntity saveAqObjectattribute(AqObjectattributeEntity aqObjectattributeEntity) {
        return uploadAqObjectattributeDao.saveOrUpdate(aqObjectattributeEntity);
    }

    @Override
    public List<AqObjectEntity> getAqObjectEntity(Integer admuID) {
        return uploadAqObjectDao.getAqObjectEntity(admuID);
    }

    @Override
    public List<AqObjdetailEntity> getAqObjdetailEntity(Integer objId) {
        return uploadAqObjectDao.getAqObjtailEntity(objId);
    }

    @Override
    public List getAttributeAqObjdetailEntity(Integer oaId) {
        return uploadAqObjectDao.getAttributeAqObjdetailEntity(oaId);
    }

    @Override
    public List getAqAttachmentAndLocation(Integer objId,Integer type) {
        return uploadAqObjectDao.getAqAttachmentAndLocation(objId,type);
    }

    @Override
    public List getAttributeAqAttachmentAndLocation(Integer oaId) {
        return uploadAqObjectDao.getAttributeAqAttachmentAndLocation(oaId);
    }

    @Override
    public List<AqObjectattributeEntity> getAqObejctAttributeEntity(Integer objId) {
        return uploadAqObjectDao.getAqObejctAttributeEntity(objId);
    }

    @Override
    public AqObjectEntity alterAqObjectEntity(Integer objId) {
        return uploadAqObjectDao.alterAqObjectEntity(objId);
    }

    @Override
    public AqObjdetailEntity alterAqObjdetailEntity(Integer dtlId) {
        return uploadAqObjectDao.alterAqObjtailEntity(dtlId);
    }

    @Override
    public AqAttachmentEntity alterAqAttachmentEntity(Integer attId) {
        return uploadAqObjectDao.alterAqAttachment(attId);
    }

    @Override
    public void deletAqObjectEntity(Integer objId) {
        /*try {
            return uploadAqObjectDao.deleteByIntProperty("goptId",objId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;*/
        uploadAqObjectDao.deleteByIntProperty("objId",objId);
    }

    @Override
    public void deleteAqObjectDetailEntity(Integer dtlId) {
        uploadAqObjectDetailDao.deleteByIntProperty("dtlId",dtlId);
    }

    @Override
    public void deleteAqObjectattributeEntity(Integer oaId) {
        uploadAqObjectattributeDao.deleteByIntProperty("oaId",oaId);
    }

    @Override
    public List getDeleteUrl(Integer objId) {
        return uploadAqObjectDao.getDeleteUrl(objId);
    }

    @Override
    public List getDtlId(Integer objId) {
        return uploadAqObjectDao.getDtlId(objId);
    }

    @Override
    public List getOaId(Integer objId) {
        return uploadAqObjectDao.getOaId(objId);
    }

    @Override
    public void insertAqObjdetail(String insertSql) {
        uploadAqObjectDetailDao.insertDetails(insertSql);
    }

    @Override
    public Integer getTheLatestDtlId() {
        return uploadAqObjectDetailDao.getTheLatestDtlId();
    }

    @Override
    public List getCesiumObjectMessage() {
        return uploadAqObjectDao.getCesiumObjectMessage();
    }

    @Override
    public List getObjectUrl(Integer objId) {
        return uploadAqObjectDao.getObjectUrl(objId);
    }


    @Override
    public List getCesiumAttributeMessage(Integer objiId) {
        return uploadAqObjectDao.getAttributeMessage(objiId);
    }

    @Override
    public List getAttributeUrl(Integer oaId) {
        return uploadAqObjectDao.getAttributeUrl(oaId);
    }

    @Override
    public WebObjIdMobileIdEntity addWebAndMoblieObjId(WebObjIdMobileIdEntity webObjIdMobileIdEntity) {
        return web_mobileDao.saveOrUpdate(webObjIdMobileIdEntity);
    }

    @Override
    public String getMobileObjId(Integer objId) {
        return web_mobileDao.getMobileObjId(objId);
    }


}
