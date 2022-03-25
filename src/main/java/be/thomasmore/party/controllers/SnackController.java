package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Snack;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SnackController {
    @Autowired
    private SnackRepository snackRepository;

    @GetMapping({"/snacklist","/snacklist/{extrawoord}"})
    public String snacklist(Model model,
                            @PathVariable(required = false) String extrawoord){

        Iterable<Snack> snacks = snackRepository.findAll();
        long nrOfSnacks = snackRepository.count();

        model.addAttribute("snacks", snacks);
        model.addAttribute("nrOfSnacks", nrOfSnacks);

        return "snacklist";
    }
}
