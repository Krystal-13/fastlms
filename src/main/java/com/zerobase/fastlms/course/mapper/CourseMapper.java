package com.zerobase.fastlms.course.mapper;

import com.zerobase.fastlms.admin.model.CommonParam;
import com.zerobase.fastlms.course.dto.CourseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<CourseDto> selectList(CommonParam parameter);

    long selectListCount(CommonParam parameter);
}
