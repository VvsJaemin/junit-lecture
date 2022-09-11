package site.meta.junit.web.dto;

import lombok.Setter;
import site.meta.junit.domain.Book;

//@Setter
public class BookSaveReqDto {
    private String title;
    private String author;


    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .build();

    }
}
