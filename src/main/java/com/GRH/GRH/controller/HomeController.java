package com.GRH.GRH.controller;

import com.GRH.GRH.Service.PatientService;
import com.GRH.GRH.Service.DoctorService;
import com.GRH.GRH.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Hospital Management System");
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Get counts for dashboard statistics
        long patientCount = patientService.getAllPatients().size();
        long doctorCount = doctorService.getAllDoctors().size();
        long appointmentCount = appointmentService.getAllAppointments().size();

        // Get today's appointments
        long todayAppointments = appointmentService.getAppointmentsByDate(LocalDate.now()).size();

        // Get upcoming appointments (next 7 days)
        long upcomingAppointments = appointmentService.getAllAppointments().stream()
                .filter(app -> app.getDate().isAfter(LocalDate.now().minusDays(1))
                        && app.getDate().isBefore(LocalDate.now().plusDays(8)))
                .count();

        model.addAttribute("pageTitle", "Tableau de Bord");
        model.addAttribute("patientCount", patientCount);
        model.addAttribute("doctorCount", doctorCount);
        model.addAttribute("appointmentCount", appointmentCount);
        model.addAttribute("todayAppointments", todayAppointments);
        model.addAttribute("upcomingAppointments", upcomingAppointments);

        return "dashboard";
    }
}
