package com.douglas.readvista.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.readvista.dtos.BookDTO;
import com.douglas.readvista.entities.Book;
import com.douglas.readvista.repositories.BookRepository;
import com.douglas.readvista.services.exceptions.DataIntegrityViolationException;
import com.douglas.readvista.services.exceptions.ObjectNotFoundException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findBookById(Integer id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Book not found with ID: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public BookDTO create(BookDTO bookDTO) {
        if (bookDTO.id() != null) {
            throw new DataIntegrityViolationException("ID should be null when creating a new Book!");
        }
        Book book = new Book(bookDTO.id(), "", bookDTO.title(), bookDTO.author(), bookDTO.yearOfPublication(), bookDTO.status(), bookDTO.bookCondition());
        book = bookRepository.save(book);
        return mapToDTO(book);
    }

    public BookDTO update(Integer id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found with ID: " + id));
        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setYearOfPublication(bookDTO.yearOfPublication());
        book.setStatus(bookDTO.status());
        book.setBookCondition(bookDTO.bookCondition());
        book = bookRepository.save(book);
        return mapToDTO(book);
    }

    public void delete(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found with ID: " + id));
        if (book.getLoans().size() > 0) {
            throw new DataIntegrityViolationException("Book has loans and cannot be deleted!");
        }
        bookRepository.deleteById(id);
    }

    private BookDTO mapToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getYearOfPublication(), book.getStatus(), book.getBookCondition());
    }
}
