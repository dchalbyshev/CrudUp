package com.dchalbyshevv.controllers;

import com.dchalbyshevv.dao.PersonDao;
import com.dchalbyshevv.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
private final PersonDao personDao;
@Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public  String index(Model model){
         model.addAttribute("people",personDao.index());
         // под  ключом people у анс лежит список людей

        // получение всех людей из DAO
        return  "index";
        // возращаем страницу (шаблон) отображающий список людей
    }
    @GetMapping("/{id}")
public  String show(@PathVariable("id") int id, Model model){
// @PathVariable("id")int id - извлекаем параметр из адреса и кидаем в пер. id
//  добавляем Model model так как добавляам одного человека в шаблон
        // логика - получаем одного человека из DAO и добавляем в шаблон
    model.addAttribute("person", personDao.show(id));
        return  "show";
        // возвращаем шаблон черовека

}
@GetMapping("/new") // по заданному заппросу вернем HTML форму
public String newperson(Model model){ // для создания человека
// если используем Thymeleaf формы  то в модели должны передать
// тот объект для которого эта форма нужна
model.addAttribute("person",new Person());
return "new";
}

/*
то же самое только за нас все сделает аннотация и положит  и создаст
    @GetMapping("/new") // по заданному заппросу вернем HTML форму
    public String newperson(@ModelAttribute("person")Person person){

        return "new";
    }  */





@PostMapping()
    public String create(@ModelAttribute("person")Person person ){
// по ключчу person извлекаем человека с данными из формы(которую
 //        заполняли на  ThymeLeaf HTML  странице (планирую что это
        //        будет new страница )

        personDao.save(person);
        /// сохраняю в базе данных
        return "redirect:/people";
        // осуществляем пепренаправление на другую страницу
        // после того  как человек будет добавлен


    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) {
        model.addAttribute("person", personDao.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
            public  String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
       //  здесь при помощи @PathVariable получаем поле  id
        //  приршедшего из формы объекта

        // и здесь же необходимо проработать логику обновления
        // в базе пришедшей из формы персоны (логика в DAO)
        personDao.update(id,person);
          return "redirect:/people";
        }
        @DeleteMapping
    public String delete(@PathVariable("id") int id){
          personDao.delete(id);
          return  "redirect:/people";
          // пепренаправляем на главную страницу
        }
    }

