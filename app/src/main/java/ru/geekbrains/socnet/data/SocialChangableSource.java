package ru.geekbrains.socnet.data;

public interface SocialChangableSource extends SocialDataSource {
    void add();
    void delete();
}
