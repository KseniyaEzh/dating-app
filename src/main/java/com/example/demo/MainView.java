package com.example.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.data.binder.Binder;
import java.util.Optional;

import java.awt.*;

@Route("")
public class MainView extends VerticalLayout {
    private PersonRepository repository;
    private TextField firstName = new TextField("First name"); // Имя
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");
    private Binder<Person> binder = new Binder<>(Person.class);
    private Grid<Person> grid = new Grid<>(Person.class);

    public MainView(PersonRepository repository) {
        this.repository = repository;

        grid.setColumns("firstName", "lastName", "email"); // для таблички поля
        add(getForm(), grid);
        refreshGrid();
    }

    private Component getForm() {
        var layout = new HorizontalLayout(); // типа div
        layout.setAlignItems(Alignment.BASELINE);

        var addButton = new Button("Add");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        var checkButton = new Button("Check in db");
        checkButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.add(firstName, lastName, email, addButton, checkButton);

        binder.bindInstanceFields(this);

        addButton.addClickListener(click -> {
            try {
                var person = new Person();
                binder.writeBean(person);
                repository.save(person);
                refreshGrid();
            } catch(ValidationException e) {
                //
            }
        });

        checkButton.addClickListener(click -> {
            Optional<Person> emailEntry = repository.findByEmail(email.getValue());
            System.out.println(emailEntry.isPresent());
        });

        return layout;
    }

    private void refreshGrid() {
        grid.setItems(repository.findAll());
    }
}
