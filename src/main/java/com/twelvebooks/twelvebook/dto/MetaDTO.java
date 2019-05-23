package com.twelvebooks.twelvebook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaDTO {
    private boolean end;
    private int pagable_count;
    private int total_count;
}
