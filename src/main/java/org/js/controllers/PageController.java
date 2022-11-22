package org.js.controllers;

import org.js.entities.Zadanie;
import org.js.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    public ZadanieRepository rep;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan(){
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();
        rep.save(zadanie);
        for(Zadanie i: rep.findAll()){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/dodatkoweZadania")
    @ResponseBody
    public void dodatkoweZadania(){
        Zadanie z;
        double k=1000;
        boolean wyk=false;
        for (int i=1;i<=10;i++){
            z = new Zadanie();
            z.setNazwa("zadanie "+i);
            z.setOpis("Opis czynnosci do wykonania w zadaniu "+i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            wyk=!wyk;
            k+=200.50;
            rep.save(z);
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteZadanie(@PathVariable Long id){
        rep.deleteById(id);
    }

    @RequestMapping("/zadaniaWykonane/{wykonane}")
    @ResponseBody
    public String zadaniaWykonane(@PathVariable Boolean wykonane) {
        StringBuilder odp = new StringBuilder();

        for(Zadanie i: rep.findByWykonane(wykonane)){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/zadania/mniejniz/{koszt}")
    @ResponseBody
    public String zadaniaKosztMniejNiż(@PathVariable String koszt){
        StringBuilder odp = new StringBuilder();

        Double kosztMax = Double.parseDouble(koszt);
        for(Zadanie i: rep.findByKosztLessThan(kosztMax)){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/zadania/miedzy/{kosztMin}/{kosztMax}")
    @ResponseBody
    public String zadaniaKosztMiedzy(@PathVariable String kosztMin, @PathVariable String kosztMax){
        StringBuilder odp = new StringBuilder();
        Double min = Double.parseDouble(kosztMin);
        Double max = Double.parseDouble(kosztMax);
        for(Zadanie i: rep.findByKosztBetween(min,max)){
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }



}
