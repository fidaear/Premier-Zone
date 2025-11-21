package com.GRH.GRH.controller;


import com.GRH.GRH.entity.Patient;
import com.GRH.GRH.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/web/patients")
public class PatientWebController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        model.addAttribute("pageTitle", "Gestion des Patients");
        return "patients/list";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("pageTitle", "Nouveau Patient");
        return "patients/create";
    }

    @PostMapping("/create")
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.createPatient(patient);
        return "redirect:/web/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            model.addAttribute("pageTitle", "Modifier Patient");
            return "patients/edit";
        }
        return "redirect:/web/patients";
    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable String id, @ModelAttribute Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/web/patients";
    }


    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/web/patients";
    }

    @GetMapping("/search")
    public String searchPatients(@RequestParam String name, Model model) {
        List<Patient> patients = patientService.searchPatientsByName(name);
        model.addAttribute("patients", patients);
        model.addAttribute("pageTitle", "Résultats de recherche");
        return "patients/list";
    }
}
