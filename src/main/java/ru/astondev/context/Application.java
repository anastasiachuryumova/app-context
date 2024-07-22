package ru.astondev.context;

import ru.astondev.context.annotations.JustLikeSpringApplication;
import ru.astondev.context.classes.LikeSpringApplication;
import ru.astondev.context.interfaces.CommandLineRunner;

@JustLikeSpringApplication
public class Application implements CommandLineRunner {


    public static void main(String[] args) {
        LikeSpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {}
}
