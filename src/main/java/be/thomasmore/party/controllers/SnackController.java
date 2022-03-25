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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SnackController {
    @Autowired
    private SnackRepository snackRepository;

    private Boolean filterStringToBoolean(String filterString) {
        return (filterString == null || filterString.equals("all")) ? null : filterString.equals("yes");
    }

    @GetMapping({"/snacklist","/snacklist/{extrawoord}"})
    public String snacklist(Model model,
                            @PathVariable(required = false) String extrawoord){

        Iterable<Snack> snacks = snackRepository.findAll();
        long nrOfSnacks = snackRepository.count();

        model.addAttribute("snacks", snacks);
        model.addAttribute("nrOfSnacks", nrOfSnacks);

        return "snacklist";
    }
    @GetMapping({"/snacklist/filter"})
    public String snacklistWithFilter(Model model,
                                      @RequestParam(required = false) Double maxPrice,
                                      @RequestParam(required = false) String sideDishPossible,
                                      @RequestParam(required = false) String vegan){

        List<Snack> snacks = snackRepository.findByFilterSnack( maxPrice, filterStringToBoolean(vegan));
        List<Snack> snackswithSide = snackRepository.findByFilterSnackAndSide( maxPrice, filterStringToBoolean(sideDishPossible), filterStringToBoolean(vegan));

        long nrOfSnacks = snacks.size();

        model.addAttribute("snacks", snacks);
        model.addAttribute("snackswithSide", snackswithSide);
        model.addAttribute("nrOfSnacks", nrOfSnacks);
        model.addAttribute("showFilters", true);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("vegan", vegan);

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
