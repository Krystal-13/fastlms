package com.zerobase.fastlms.course.model;

import lombok.Data;

@Data
public class CourseInput {

    String subject;
    long id;
    long categoryId;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    String idList;

    String filename;
    String urlFilename;
}
