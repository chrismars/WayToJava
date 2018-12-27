package app.sys.uplaodPic.dao;

import app.common.dao.GenericEntityDao;
import app.sys.uplaodPic.vo.UserMessageEntity;

import javax.inject.Named;

@Named("userDao")
public class userDao extends GenericEntityDao<UserMessageEntity,Integer> {
    @Override
    protected Class<UserMessageEntity> entityClass() {
        return UserMessageEntity.class;
    }
}
