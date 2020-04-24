package ru.geekbrains.socnet;

public interface SocialChangableSource extends SocialDataSource {
    void add();
    void delete();
}
