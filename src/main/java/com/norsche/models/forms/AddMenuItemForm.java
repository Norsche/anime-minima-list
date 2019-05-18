package com.norsche.models.forms;

import com.norsche.models.Anime;
import com.norsche.models.Menu;

public class AddMenuItemForm {

    private Menu menu;
    private Iterable<Anime> animes;
    private int menuId;
    private int animeId;

    public AddMenuItemForm() {}

    public AddMenuItemForm(Menu menu, Iterable<Anime> animes) {
        this.menu = menu;
        this.animes = animes;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(Iterable<Anime> animes) {
        this.animes = animes;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }
}