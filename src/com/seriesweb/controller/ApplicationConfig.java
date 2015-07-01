package com.seriesweb.controller;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ActorController.class);
        resources.add(EpisodeController.class);
        resources.add(GenreController.class);
        resources.add(IterationController.class);
        resources.add(ParentalGuidanceController.class);
        resources.add(SerieController.class);
        resources.add(UserController.class);
        resources.add(FileUpload.class); //inclui classe para fazer upload
        resources.add(MultiPartFeature.class); //define para receber multipart
    }
    
}