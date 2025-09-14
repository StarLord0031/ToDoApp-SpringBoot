package com.todoapp.todoapp.controller;

import com.todoapp.todoapp.entity.todoEntity;
import com.todoapp.todoapp.repository.todoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class todoController {

    private final todoRepo todoRepoEntry;
    @GetMapping({"/","","/home"})
    public String showHomePage(Model model){
        model.addAttribute("todos",todoRepoEntry.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title){
        todoEntity newTodo=todoEntity.builder().title(title)
                .completed(false)
                .build();
        todoRepoEntry.save(newTodo);
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id){
        todoEntity existingTodo=todoRepoEntry.findById(id).orElseThrow(()-> new RuntimeException("Todo not Found: "+id));
        existingTodo.setCompleted(true);
        todoRepoEntry.save(existingTodo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        todoEntity existingTodo=todoRepoEntry.findById(id).orElseThrow(()-> new RuntimeException("Todo not Found: "+id));
        todoRepoEntry.delete(existingTodo);
        return "redirect:/";
    }
}
