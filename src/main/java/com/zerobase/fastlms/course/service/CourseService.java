package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {

    boolean add(CourseInput parameter);
    boolean set(CourseInput parameter);
    List<CourseDto> list(CourseParam parameter);
    CourseDto getById(long id);
    boolean del(String idList);
    List<CourseDto> frontList(CourseParam parameter);

    CourseDto frontDetail(long id);

    ServiceResult req(TakeCourseInput parameter);

    List<CourseDto> listAll();
}
