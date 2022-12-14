//package com.demo.onlineLibraryAnaMariaDoroftei.security;
//
//import com.demo.onlineLibraryAnaMariaDoroftei.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class SecurityAspect {
//    private final UserRepository userRepository;
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/UserService.saveUser(..))")
//    public void saveUser(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/UserService.deleteUser(..))")
//    public void deleteUser(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/BookService.saveBook(..))")
//    public void saveBook(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/BookService.deleteBook(..))")
//    public void deleteBook(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/ReadBookService.saveReadBook(..))")
//    public void saveReadBook(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/ReadBookService.deleteReadBook(..))")
//    public void deleteReadBook(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/CategoryBookService.saveCategoryBook(..))")
//    public void saveCategoryBook(){
//    }
//
//    @Pointcut("execution(* com/demo/onlineLibraryAnaMariaDoroftei/services/CategoryBookService.deleteCategoryBook(..))")
//    public void deleteCategoryBook(){
//    }
//
//
//}
