package ru.netology;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

/*
для поиска класcов и имплементирующих webApplicationInitializer
и запуска на них методы onStartup

Создаем новый класс с имплементацией WebApplicationInitialize
и указываем в методе onStartup что мы хотим сделать:
1. Регистрируем диспетчер сервлет
2. Инициализация зависимостей
 */

public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("ru.netology");                                // указываем пакет где нужно найти необходимые конфигурации и анастации
        context.refresh();                                                      // заставляет фабрику объектов создать все бины

        final var servlet = new DispatcherServlet(context);                     // создаем сервлет с передачей всех бинов
        final var registration = servletContext.addServlet(             // добавляем сервлет в сервелтКонтекс
                "app", servlet
        );
        registration.setLoadOnStartup(1);                                       // программная особенность регистрации сервлета (1)
        registration.addMapping("/");                                 // указываем коневой мапинг
    }
}