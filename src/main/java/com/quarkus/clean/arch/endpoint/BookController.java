package com.quarkus.clean.arch.endpoint;

import com.quarkus.clean.arch.adapter.BookAdapter;
import com.quarkus.clean.arch.endpoint.entity.BookResponse;
import com.quarkus.clean.arch.endpoint.entity.CreateBookRequest;
import com.quarkus.clean.arch.endpoint.entity.UpdateBookRequest;
import com.quarkus.clean.arch.usecase.*;
import com.quarkus.clean.arch.usecase.validations.ValidateObjectFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Slf4j
@Path("/books")
@Tag(name = "Books")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    private final ValidateObjectFields validateObjectFields;
    private final CreateBook createBook;
    private final FindAllBooks findAllBooks;
    private final UpdateBook updateBook;
    private final RemoveBook removeBook;
    private final FindBookById findBookById;
    private final FindBookByTitle findBookByTitle;
    private final CountBooks countBooks;

    @POST
    @Operation(summary = "Realiza a criação de um novo livro")
    public Response create(final CreateBookRequest createBookRequest) {
        log.info("PAYLOAD - Create new book: {}", createBookRequest.toString());
        validateObjectFields.execute(createBookRequest);
        createBook.execute(BookAdapter.fromVMToDomain(createBookRequest));
        return Response.status(Status.CREATED).build();
    }

    @GET
    @Operation(summary = "Retorna a lista de livros")
    public Response findAll() {
        List<BookResponse> bookResponseList = BookAdapter.fromDomainListToResponseList(findAllBooks.execute());
        return Response.status(Status.OK).entity(bookResponseList).build();
    }

    @PUT
    @Operation(summary = "Realiza a atualização de um livro")
    public Response update(final UpdateBookRequest updateBookRequest) {
        log.info("PAYLOAD - Update book: {}", updateBookRequest.toString());
        validateObjectFields.execute(updateBookRequest);
        updateBook.execute(BookAdapter.fromVMToDomainForUpdate(updateBookRequest));
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Realiza a remoção de um livro pelo id")
    public Response remove(@PathParam("id") final Long id) {
        log.info("PAYLOAD - Remove book with id {}", id);
        removeBook.execute(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Realiza a busca de um livro pelo id")
    public Response findById(@PathParam("id") final Long id) {
        log.info("PAYLOAD - Find book by id {}", id);
        BookResponse bookResponse = BookAdapter.fromDomainToResponse(findBookById.execute(id));
        return Response.status(Status.OK).entity(bookResponse).build();
    }

    @GET
    @Path("/title/{title}")
    @Operation(summary = "Realiza a busca de um livro pelo título")
    public Response findByTitle(@PathParam("title") final String title) {
        log.info("PAYLOAD - Find book by title {}", title);
        BookResponse bookResponse = BookAdapter.fromDomainToResponse(findBookByTitle.execute(title));
        return Response.status(Status.OK).entity(bookResponse).build();
    }

    @GET
    @Path("/count")
    @Operation(summary = "Retorna a quantidade de livros cadastrados")
    public Response count() {
        return Response.status(Status.OK).entity(countBooks.execute()).build();
    }
}
