package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Type("map")
public class Map extends AbstractEntity {
    private String battleType;
    private String displayName;
    private String mapType;

    @Relationship("author")
    private Player author;

    @Relationship("statistics")
    private MapStatistics statistics;

    @Relationship("latestVersion")
    @JsonIgnore
    private MapVersion latestVersion;

    @Relationship("versions")
    @JsonIgnore
    private List<MapVersion> versions;
}
