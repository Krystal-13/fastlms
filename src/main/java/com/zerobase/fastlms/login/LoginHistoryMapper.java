package com.zerobase.fastlms.login;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {
    List<LoginHistoryDto> selectList(MemberParam parameter);

}
