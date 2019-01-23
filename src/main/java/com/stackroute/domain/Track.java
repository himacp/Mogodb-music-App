package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor   //use of annotations
@AllArgsConstructor
@Builder
@Document
public class Track {
    @Id
    private int trackId;
    private String trackName;
    private String trackComments;

    }