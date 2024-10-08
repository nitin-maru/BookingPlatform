package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Screen;
import org.example.bookingplatform.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScreenServiceImpl implements IScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    public Optional<Screen> getScreenById(Long id) {
        return screenRepository.findById(id);
    }

    @Override
    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public Screen updateScreen(Long id, Screen screen) {
        return screenRepository.findById(id)
                .map(existingScreen -> {
                    existingScreen.setName(screen.getName());
                    existingScreen.setSeatCapacity(screen.getSeatCapacity());
                    existingScreen.setTheater(screen.getTheater());
                    return screenRepository.save(existingScreen);
                }).orElseThrow(() -> new RuntimeException("Screen not found"));
    }

    @Override
    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }

}
