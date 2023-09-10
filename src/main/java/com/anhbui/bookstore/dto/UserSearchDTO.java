package com.anhbui.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDTO {
    private Long categoryId;
    private String sortBy;
    private String keyword;

    public boolean isEmpty() {
        return categoryId == null && (keyword == null || keyword.isEmpty()) && (sortBy == null || sortBy.isEmpty());
    }
}
