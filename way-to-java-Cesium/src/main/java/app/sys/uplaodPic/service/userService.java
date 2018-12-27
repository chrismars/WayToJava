package app.sys.uplaodPic.service;

import app.sys.uplaodPic.action.mobileEntity.AttachmentAndLocation;
import app.sys.uplaodPic.vo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface userService {
    UserMessageEntity saveUserMassage(UserMessageEntity userMessageEntity);
    AqObjectEntity saveAqObject(AqObjectEntity aqObjectEntity);
    AqObjdetailEntity saveAqObjectDetail(AqObjdetailEntity AqObjdetailEntity);
    AqAttachmentEntity saveAqAttachment(AqAttachmentEntity aqAttachmentEntity);
    AqObjectattributeEntity saveAqObjectattribute(AqObjectattributeEntity aqObjectattributeEntity);
    List<AqObjectEntity> getAqObjectEntity(Integer admuId);
    List<AqObjdetailEntity> getAqObjdetailEntity(Integer objId);
    List getAttributeAqObjdetailEntity(Integer oaId);
    List getAqAttachmentAndLocation(Integer objId,Integer type);
    List getAttributeAqAttachmentAndLocation(Integer oaId);
    List<AqObjectattributeEntity> getAqObejctAttributeEntity(Integer objId);
    AqObjectEntity alterAqObjectEntity(Integer objId);
    AqObjdetailEntity alterAqObjdetailEntity(Integer dtlId);
    AqAttachmentEntity alterAqAttachmentEntity(Integer attId);
    void deletAqObjectEntity(Integer objId);
    void deleteAqObjectDetailEntity(Integer dtlId);
    void deleteAqObjectattributeEntity(Integer oaId);
    List getDeleteUrl(Integer objId);
    List getDtlId(Integer objId);
    List getOaId(Integer objId);
    void insertAqObjdetail(String insertSql);
    Integer getTheLatestDtlId();

    List getCesiumObjectMessage();
    List getObjectUrl(Integer objId);
    List getCesiumAttributeMessage(Integer objId);
    List getAttributeUrl(Integer oaId);

    WebObjIdMobileIdEntity addWebAndMoblieObjId(WebObjIdMobileIdEntity webObjIdMobileIdEntity);
    String getMobileObjId(Integer objId);
}
