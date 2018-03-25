package com.wordpress.carledwinj.thymeleaf.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wordpress.carledwinj.thymeleaf.model.Item;

@Controller
@RequestMapping("/itens")
public class ProdutosController {
    
    private static final List<Item> ITENS = new ArrayList<>(Arrays.asList(
            new Item(1L, "Item 1", new BigDecimal(438.44)),
            new Item(2L, "Item 2", new BigDecimal(1334.54)),
            new Item(3L, "Item 3", new BigDecimal(86.67)),
            new Item(4L, "Item 4", new BigDecimal(11.32)),
            new Item(5L, "Item 5", new BigDecimal(2235.00)))); 
    
    @GetMapping
    public ModelAndView lista(Item item) {
        ModelAndView model = new ModelAndView("/itens.html");
        
        List<Item> itens = ITENS.stream()
                .filter(i -> item.getId() == null || item.getId().equals(i.getId()))
                .filter(i -> StringUtils.isEmpty(item.getNome()) || i.getNome().startsWith(item.getNome()))
                .collect(Collectors.toList());
        
        model.addObject("itens", itens);
        
        return model;
    }
}