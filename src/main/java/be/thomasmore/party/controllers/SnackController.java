package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Snack;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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

    @GetMapping({"/snackdetails/{id}", "/snackdetails"})
    public String snackdetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "snackdetails";

        Optional<Snack> snackFromDb = snackRepository.findById(id);
        if (snackFromDb.isPresent()) {
            model.addAttribute("snack", snackFromDb.get());

        }
        return "snackdetails";
    }

    @GetMapping({"/snacktdetails/{id}/prev"})
    public String snackdetailsPrev(Model model, @PathVariable int id) {
        Optional<Snack> prevSnackFromDb = snackRepository.findFirstByIdLessThanOrderByIdDesc(id);
        if (prevSnackFromDb.isPresent())
            return String.format("redirect:/snackdetails/%d", prevSnackFromDb.get().getId());
        Optional<Snack> lastSnackFromDb = snackRepository.findFirstByOrderByIdDesc();
        if (lastSnackFromDb.isPresent())
            return String.format("redirect:/snackdetails/%d", lastSnackFromDb.get().getId());
        return "snackdetails";
    }

    @GetMapping({"/snackdetails/{id}/next"})
    public String snackdetailsNext(Model model, @PathVariable int id) {
        Optional<Snack> nextSnackFromDb = snackRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
        if (nextSnackFromDb.isPresent())
            return String.format("redirect:/snackdetails/%d", nextSnackFromDb.get().getId());
        Optional<Snack> firstSnackFromDb = snackRepository.findFirstByOrderByIdAsc();
        if (firstSnackFromDb.isPresent())
            return String.format("redirect:/snackdetails/%d", firstSnackFromDb.get().getId());
        return "snackdetails";
    }
}
