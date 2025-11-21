package com.GRH.GRH.controller;


import com.GRH.GRH.entity.Appointment;
import com.GRH.GRH.entity.Doctor;
import com.GRH.GRH.entity.Patient;
import com.GRH.GRH.Service.AppointmentService;
import com.GRH.GRH.Service.DoctorService;
import com.GRH.GRH.Service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/web/appointments")
public class AppointmentWebController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentWebController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listAppointments(Model model) {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            List<Patient> patients = patientService.getAllPatients();
            List<Doctor> doctors = doctorService.getAllDoctors();

            // Create maps for quick lookup
            Map<String, String> patientMap = patients.stream()
                    .collect(Collectors.toMap(Patient::getPatientId, Patient::getName));
            Map<String, String> doctorMap = doctors.stream()
                    .collect(Collectors.toMap(Doctor::getDoctorId, Doctor::getName));

            model.addAttribute("appointments", appointments);
            model.addAttribute("patientMap", patientMap);
            model.addAttribute("doctorMap", doctorMap);
            model.addAttribute("pageTitle", "Gestion des Rendez-vous");
            return "appointments/list";
        } catch (Exception e) {
            logger.error("Error in listAppointments", e);
            model.addAttribute("error", "Erreur lors du chargement des rendez-vous");
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        model.addAttribute("pageTitle", "Nouveau Rendez-vous");
        return "appointments/create";
    }

    @PostMapping("/create")
    public String createAppointment(@ModelAttribute Appointment appointment) {
        try {
            appointmentService.createAppointment(appointment);
            return "redirect:/web/appointments";
        } catch (Exception e) {
            return "redirect:/web/appointments/create?error=" + e.getMessage();
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();

        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            model.addAttribute("patients", patients);
            model.addAttribute("doctors", doctors);
            model.addAttribute("pageTitle", "Modifier Rendez-vous");
            return "appointments/edit";
        }
        return "redirect:/web/appointments";
    }

    @PostMapping("/update/{id}")
    public String updateAppointment(@PathVariable String id, @ModelAttribute Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/web/appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable String id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/web/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/web/appointments";
    }

    @GetMapping("/available-slots")
    @ResponseBody
    public List<String> getAvailableSlots(@RequestParam String doctorId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return appointmentService.getAvailableTimeSlots(doctorId, localDate);
    }
}
