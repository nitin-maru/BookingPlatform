package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Screen;

import java.util.List;
import java.util.Optional;

public interface IScreenService {
    List<Screen> getAllScreens();

    Optional<Screen> getScreenById(Long id);

    Screen createScreen(Screen screen);

    Screen updateScreen(Long id, Screen screen);

    void deleteScreen(Long id);
}
