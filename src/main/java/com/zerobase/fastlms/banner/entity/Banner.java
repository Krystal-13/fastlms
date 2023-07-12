package com.zerobase.fastlms.banner.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String filename;
    String urlFilename;
    LocalDateTime regDt;
    String linkUrl;
    String displayType;
    int sortValue;
    boolean displayYn;


}
