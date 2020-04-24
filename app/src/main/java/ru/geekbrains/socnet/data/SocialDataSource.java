package ru.geekbrains.socnet.data;

import ru.geekbrains.socnet.data.implementation.Soc;

public interface SocialDataSource {
    Soc getSoc(int position);
    int size();
}
