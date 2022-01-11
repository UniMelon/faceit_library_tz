package faceit.tz.model.mapper;

import faceit.tz.model.entity.Book;
import faceit.tz.model.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);
    Book toEntity(BookDto bookDto);
    List<BookDto> toDtoList(List<Book> books);
}
