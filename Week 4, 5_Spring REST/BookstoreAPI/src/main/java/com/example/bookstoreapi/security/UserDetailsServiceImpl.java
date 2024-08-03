package com.example.bookstoreapi.security;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public UserDetails loadUserByUsername(String bookname) throws UsernameNotFoundException {
        Book book = null;
		try {
			book = BookRepository.findById(bookname)
			        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + bookname));
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new org.springframework.security.core.userdetails.User(book.getTitle(), book.getAuthor(), null);
    }
}
