package com.GRH.GRH.controller;


import com.GRH.GRH.entity.Doctor;
import com.GRH.GRH.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web/doctors")
public class DoctorWebController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("pageTitle", "Gestion des Médecins");
        return "doctors/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("pageTitle", "Nouveau Médecin");
        return "doctors/create";
    }

    @PostMapping("/create")
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctorService.createDoctor(doctor);
        return "redirect:/web/doctors";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            model.addAttribute("pageTitle", "Modifier Médecin");
            return "doctors/edit";
        }
        return "redirect:/web/doctors";
    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable String id, @ModelAttribute Doctor doctor) {
        doctorService.updateDoctor(id, doctor);
        return "redirect:/web/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/web/doctors";
    }

    @GetMapping("/specialization/{specialization}")
    public String getDoctorsBySpecialization(@PathVariable String specialization, Model model) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
        model.addAttribute("doctors", doctors);
        model.addAttribute("pageTitle", "Médecins - " + specialization);
        return "doctors/list";
    }
}
