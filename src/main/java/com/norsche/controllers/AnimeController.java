package com.norsche.controllers;

import com.norsche.models.Anime;
import com.norsche.models.Category;
import com.norsche.models.data.CategoryDao;
import com.norsche.models.data.AnimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("anime")
public class AnimeController {

    @Autowired
    private AnimeDao animeDao;
    @Autowired
    private CategoryDao categoryDao;

    // Request path: /anime
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("animes", animeDao.findAll());
        model.addAttribute("title", "My Animes");

        return "anime/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddAnimeForm(Model model) {
        model.addAttribute("title", "Add Anime");
        model.addAttribute(new Anime());
        model.addAttribute("categories", categoryDao.findAll());
        return "anime/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddAnimeForm(@ModelAttribute  @Valid Anime newAnime, @RequestParam int categoryId, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Anime");
            return "anime/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newAnime.setCategory(cat);
        animeDao.save(newAnime);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveAnimeForm(Model model) {
        model.addAttribute("animes", animeDao.findAll());
        model.addAttribute("title", "Remove Anime");
        return "anime/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveAnimeForm(@RequestParam int[] animeIds) {

        for (int animeId : animeIds) {
            animeDao.delete(animeId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "edit/{animeId}", method = RequestMethod.GET)
    public String displayEditAnimeForm(Model model, @PathVariable int animeId) {

        model.addAttribute("title", "Edit Anime");
        model.addAttribute("anime", animeDao.findOne(animeId));
        model.addAttribute("categories", categoryDao.findAll());
        return "anime/edit";
    }

    @RequestMapping(value = "edit/{animeId}", method = RequestMethod.POST)
    public String processEditForm(Model model, @PathVariable int animeId,
                                  @ModelAttribute  @Valid Anime newAnime,
                                  @RequestParam int categoryId,
                                  Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Anime");
            return "anime/edit";
        }

        Anime editedAnime = animeDao.findOne(animeId);
        editedAnime.setName(newAnime.getName());
        editedAnime.setDescription(newAnime.getDescription());
        editedAnime.setCategory(categoryDao.findOne(categoryId));
        animeDao.save(editedAnime);

        return "redirect:/anime";
    }

}
