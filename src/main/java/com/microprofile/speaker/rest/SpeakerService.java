package com.microprofile.speaker.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SpeakerService {

    List<Speaker> speakers;

    @PostConstruct
    public void init() {
        speakers = Arrays.asList(
                new Speaker(1, "Rodrigo", "Silva", "Software Architect", "Integritas"),
                new Speaker(2, "Edson", "Yanaga", "Director of Developer Experience", "RedHat"),
                new Speaker(3, "Otavio", "Santana", "Software Engineer", "Tomitribe"),
                new Speaker(4, "Ivan", "Junckes", "Software Engineer", "Tomitribe"),
                new Speaker(5, "Elder", "Moraes", "Cloud Evangelist", "Oracle")
        );
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public Optional<Speaker> getById(Integer id) {
        return speakers.stream().filter(s -> s.getId().equals(id)).findFirst();
    }
}
