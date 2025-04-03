package usertext.basic.dao;

import org.apache.ibatis.annotations.Mapper;
import usertext.basic.entity.UserExtraInfo;
@Mapper
public interface UserExtraInfoMapper {
    int deleteByPrimaryKey(Integer extraInfoId);

    int insert(UserExtraInfo record);

    int insertSelective(UserExtraInfo record);

    UserExtraInfo selectByPrimaryKey(Integer extraInfoId);

    int updateByPrimaryKeySelective(UserExtraInfo record);

    int updateByPrimaryKey(UserExtraInfo record);

    int userEIUpd(UserExtraInfo record);
}