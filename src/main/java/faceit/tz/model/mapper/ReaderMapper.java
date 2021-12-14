package faceit.tz.model.mapper;

import faceit.tz.model.Reader;
import faceit.tz.model.dto.ReaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReaderMapper {
    ReaderMapper INSTANCE = Mappers.getMapper(ReaderMapper.class);

    @Mapping(expression = "java( String.valueOf(reader.getBook().getName()) )", target = "book")
    @Mapping(expression = "java( String.valueOf(reader.getUser().getUsername()) )", target = "username")
    ReaderDto toDto(Reader reader);


}
