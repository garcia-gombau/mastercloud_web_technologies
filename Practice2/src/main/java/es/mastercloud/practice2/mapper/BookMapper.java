package es.mastercloud.practice2.mapper;

import es.mastercloud.practice2.models.dao.Book;
import es.mastercloud.practice2.models.dto.BasicBookDTO;
import es.mastercloud.practice2.models.dto.BookDTO;
import es.mastercloud.practice2.models.dto.IdBookDTO;
import es.mastercloud.practice2.models.dto.InBookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface BookMapper {
    BookDTO toBookDTO(Book book);
    IdBookDTO toIdBookDTO(Book book);
    Book toBookFromInBookDTO(InBookDTO book);
    BasicBookDTO toBasicDTO(Book book);
}
