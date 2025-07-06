package com.bibliotheque.service;

import com.bibliotheque.repository.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    public boolean estFerie(LocalDate date) {
        return jourFerieRepository.existsByDate(date);
    }
}
