package com.portfolio.springboot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeDto {
    private LocalDateTime memberJoinDatetime;
}
