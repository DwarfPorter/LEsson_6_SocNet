package ru.geekbrains.socnet.data.implementation;
import android.content.res.Resources;

import ru.geekbrains.socnet.data.SocialDataSource;

public class SocSourceBuilder {
    private Resources resources;

    public SocSourceBuilder setResources(Resources resources){
        this.resources = resources;
        return this;
    }

    public SocialDataSource build(){
        SocSource socSource = new SocSource(resources);
        socSource.init();
        return socSource;
    }

}
