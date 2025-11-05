import java.time.LocalDateTime;
import java.util.*;

public class SistemaGestionTurnos {
    private Map<String, Paciente> pacientes;
    private Map<String, Medico> medicos;
    private Map<String, AgendaMedicoAVL> agendas;
    private PacientesMapa mapaPacientes;
    private SalaEspera salaEspera;
    private RecordatorioPlanner planner;
    private PlanificadorQuirofanoImpl planificadorQuirofano;
    private AgendaConHistorialImpl agendaConHistorial;
    
    private Scanner scanner;
    
    public SistemaGestionTurnos() {
        this.pacientes = new HashMap<>();
        this.medicos = new HashMap<>();
        this.agendas = new HashMap<>();
        this.mapaPacientes = new PacientesMapa();
        this.scanner = new Scanner(System.in);
        this.planner = new RecordatorioPlanner();
        inicializarDatosEjemplo();
    }
    
    private void inicializarDatosEjemplo() {
        // PACIENTES
        pacientes.put("1111", new Paciente("1111", "Juan Perez"));
        pacientes.put("4444", new Paciente("4444", "Ana Gomez"));
        pacientes.put("5555", new Paciente("5555", "Luis Torres"));
        pacientes.put("3333", new Paciente("3333", "Maria Lopez"));
        
        // MEDICOS
        medicos.put("M1111", new Medico("M1111", "Dr. Perez", "Cardiologia"));
        medicos.put("M2222", new Medico("M2222", "Dr. Gomez", "Clinica"));
        
        // Agregar pacientes al mapa hash
        for (Paciente p : pacientes.values()) {
            mapaPacientes.put(p.dni, p);
        }
        
        // Inicializar agendas para cada médico
        for (Medico m : medicos.values()) {
            agendas.put(m.matricula, new AgendaMedicoAVL());
        }
        
        // Sala de espera con capacidad 3
        this.salaEspera = new SalaEspera(3);
        
        // Planificador de quirófano con 2 quirófanos
        List<String> quirofanos = Arrays.asList("Quirof1", "Quirof2");
        this.planificadorQuirofano = new PlanificadorQuirofanoImpl(quirofanos);
        
        // Agenda con historial
        this.agendaConHistorial = new AgendaConHistorialImpl();
        
        // AGREGAR TURNOS DE EJEMPLO CON LOS DATOS REALES
        Turno turno1 = new Turno("T001", "1111", "M1111", 
                                LocalDateTime.now().plusDays(1).withHour(9).withMinute(0), 
                                30, "Control cardiaco");
        Turno turno2 = new Turno("T002", "3333", "M2222", 
                                LocalDateTime.now().plusDays(1).withHour(10).withMinute(0), 
                                45, "Consulta general");
        Turno turno3 = new Turno("T003", "4444", "M1111", 
                                LocalDateTime.now().plusDays(2).withHour(11).withMinute(0), 
                                60, "Electrocardiograma");
        
        agendas.get("M1111").agendar(turno1);
        agendas.get("M2222").agendar(turno2);
        agendas.get("M1111").agendar(turno3);
        
        System.out.println("=== DATOS CARGADOS EXITOSAMENTE ===");
        System.out.println("Pacientes: Juan Perez (1111), Ana Gomez (4444), Luis Torres (5555), Maria Lopez (3333)");
        System.out.println("Medicos: Dr. Perez (M1111 - Cardiologia), Dr. Gomez (M2222 - Clinica)");
        System.out.println("Turnos de ejemplo cargados en las agendas");
    }
    
    public void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTION DE TURNOS MEDICOS ===");
        System.out.println("1) Ver agenda de un medico");
        System.out.println("2) Buscar hueco libre");
        System.out.println("3) Sala de espera");
        System.out.println("4) Recordatorios");
        System.out.println("5) Indice de pacientes (Hash)");
        System.out.println("6) Consolidar agendas");
        System.out.println("7) Ordenar turnos");
        System.out.println("8) Undo/Redo");
        System.out.println("9) Quirofanos");
        System.out.println("0) Salir");
        System.out.print("Seleccione: ");
    }
    
    public void ejecutar() {
        System.out.println("=== SISTEMA DE GESTION DE TURNOS MEDICOS ===");
        System.out.println("Cargando datos iniciales...");
        
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1: verAgendaMedico(); break;
                case 2: buscarHuecoLibre(); break;
                case 3: menuSalaEspera(); break;
                case 4: menuRecordatorios(); break;
                case 5: menuIndicePacientes(); break;
                case 6: consolidarAgendas(); break;
                case 7: ordenarTurnos(); break;
                case 8: menuUndoRedo(); break;
                case 9: menuQuirofanos(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion invalida!");
            }
        } while (opcion != 0);
    }
    
    private void verAgendaMedico() {
        System.out.println("\n--- AGENDA MEDICO ---");
        System.out.println("Medicos disponibles: M1111 (Dr. Perez), M2222 (Dr. Gomez)");
        System.out.print("Ingrese matricula del medico: ");
        String matricula = scanner.nextLine();
        
        AgendaMedicoAVL agenda = agendas.get(matricula);
        if (agenda == null) {
            System.out.println("Medico no encontrado! Use M1111 o M2222");
            return;
        }
        
        Medico medico = medicos.get(matricula);
        System.out.println("\nAGENDA DE " + medico.nombre + " - " + medico.especialidad);
        
        List<Turno> turnos = agenda.getTurnosOrdenados();
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos agendados");
        } else {
            System.out.println("Turnos ordenados por fecha:");
            System.out.println("ID\tPACIENTE\tFECHA Y HORA\t\tMOTIVO");
            for (Turno t : turnos) {
                Paciente p = pacientes.get(t.dniPaciente);
                String nombrePaciente = (p != null) ? p.nombre : "Desconocido";
                System.out.println(t.id + "\t" + nombrePaciente + "\t" + t.fechaHora + "\t" + t.motivo);
            }
        }
        
        // Mostrar siguiente disponible
        System.out.print("\nIngrese fecha para buscar siguiente disponible (yyyy-MM-dd HH:mm): ");
        String fechaStr = scanner.nextLine();
        try {
            LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
            Optional<Turno> siguiente = agenda.siguiente(fecha);
            if (siguiente.isPresent()) {
                System.out.println("Siguiente disponible >= " + fecha + " -> " + siguiente.get().fechaHora);
            } else {
                System.out.println("No hay turnos disponibles después de " + fecha);
            }
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Use: 2024-01-15 09:00");
        }
    }
    
    private void buscarHuecoLibre() {
        System.out.println("\n--- BUSCAR HUECO LIBRE ---");
        System.out.println("Medicos disponibles: M1111 (Dr. Perez), M2222 (Dr. Gomez)");
        System.out.print("Ingrese matricula del medico: ");
        String matricula = scanner.nextLine();
        
        AgendaMedicoAVL agenda = agendas.get(matricula);
        if (agenda == null) {
            System.out.println("Medico no encontrado! Use M1111 o M2222");
            return;
        }
        
        System.out.print("Fecha desde (yyyy-MM-dd HH:mm): ");
        String fechaStr = scanner.nextLine();
        
        System.out.print("Duracion necesaria (minutos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine();
        
        try {
            LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
            Optional<LocalDateTime> hueco = agenda.primerHueco(fecha, duracion);
            if (hueco.isPresent()) {
                System.out.println("Primer hueco disponible: " + hueco.get());
            } else {
                System.out.println("No hay huecos disponibles");
            }
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido. Use: 2024-01-15 09:00");
        }
    }
    
    private void menuSalaEspera() {
        System.out.println("\n--- SALA DE ESPERA (Capacidad: 3) ---");
        
        int opcion;
        do {
            System.out.println("\n1) Llega paciente");
            System.out.println("2) Atender paciente");
            System.out.println("3) Ver estado");
            System.out.println("4) Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("DNI del paciente (1111, 4444, 5555, 3333): ");
                    String dni = scanner.nextLine();
                    salaEspera.llega(dni);
                    break;
                case 2:
                    String atendido = salaEspera.atiende();
                    if (atendido != null) {
                        Paciente p = pacientes.get(atendido);
                        String nombre = (p != null) ? p.nombre : atendido;
                        System.out.println("Atendiendo: " + nombre + " (DNI: " + atendido + ")");
                    } else {
                        System.out.println("No hay pacientes en espera");
                    }
                    break;
                case 3:
                    String[] enEspera = salaEspera.getPacientes();
                    System.out.println("Pacientes en espera: " + Arrays.toString(enEspera));
                    System.out.println("Total: " + salaEspera.size() + "/" + 3);
                    break;
            }
        } while (opcion != 4);
    }
    
    private void menuRecordatorios() {
        System.out.println("\n--- RECORDATORIOS ---");
        
        int opcion;
        do {
            System.out.println("\n1) Programar recordatorio");
            System.out.println("2) Ver proximo");
            System.out.println("3) Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("ID del recordatorio: ");
                    String id = scanner.nextLine();
                    System.out.print("DNI paciente (1111, 4444, 5555, 3333): ");
                    String dni = scanner.nextLine();
                    System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
                    String fechaStr = scanner.nextLine();
                    System.out.print("Mensaje: ");
                    String mensaje = scanner.nextLine();
                    
                    try {
                        LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
                        planner.programar(new Recordatorio(id, fecha, dni, mensaje));
                        System.out.println("Recordatorio programado!");
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido");
                    }
                    break;
                case 2:
                    Recordatorio prox = planner.proximo();
                    if (prox != null) {
                        Paciente p = pacientes.get(prox.dniPaciente);
                        String nombre = (p != null) ? p.nombre : prox.dniPaciente;
                        System.out.println("Próximo recordatorio: " + nombre + " - " + prox.fecha + " - " + prox.mensaje);
                    } else {
                        System.out.println("No hay recordatorios programados");
                    }
                    break;
            }
        } while (opcion != 3);
    }
    
    private void menuIndicePacientes() {
        System.out.println("\n--- INDICE DE PACIENTES (HASH TABLE) ---");
        mapaPacientes.mostrarEstado();
        
        System.out.println("\n1) Buscar paciente por DNI");
        System.out.println("2) Agregar paciente");
        System.out.print("Seleccione: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        if (opcion == 1) {
            System.out.print("DNI a buscar (1111, 4444, 5555, 3333): ");
            String dni = scanner.nextLine();
            Paciente p = mapaPacientes.get(dni);
            if (p != null) {
                System.out.println("Paciente encontrado: " + p.nombre + " (DNI: " + p.dni + ")");
            } else {
                System.out.println("Paciente no encontrado");
            }
        } else if (opcion == 2) {
            System.out.print("DNI del nuevo paciente: ");
            String dni = scanner.nextLine();
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();
            mapaPacientes.put(dni, new Paciente(dni, nombre));
            System.out.println("Paciente agregado al índice hash!");
        }
    }
    
    private void consolidarAgendas() {
        System.out.println("\n--- CONSOLIDAR AGENDAS ---");
        
        // Crear agendas de ejemplo con datos reales
        List<Turno> agendaLocal = new ArrayList<>();
        List<Turno> agendaNube = new ArrayList<>();
        
        agendaLocal.add(new Turno("T001", "1111", "M1111", 
                                LocalDateTime.now().plusHours(2), 30, "Control"));
        agendaLocal.add(new Turno("T004", "5555", "M2222", 
                                LocalDateTime.now().plusHours(5), 45, "Consulta"));
        
        agendaNube.add(new Turno("T001", "4444", "M1111", 
                               LocalDateTime.now().plusHours(3), 30, "Duplicado"));
        agendaNube.add(new Turno("T005", "3333", "M1111", 
                               LocalDateTime.now().plusHours(6), 60, "Nuevo turno"));
        
        System.out.println("Agenda Local: " + agendaLocal.size() + " turnos");
        System.out.println("Agenda Nube: " + agendaNube.size() + " turnos");
        
        List<Turno> resultado = AgendaConsolidator.merge(agendaLocal, agendaNube);
        System.out.println("\nAgenda Consolidada: " + resultado.size() + " turnos");
        
        for (Turno t : resultado) {
            Paciente p = pacientes.get(t.dniPaciente);
            String nombre = (p != null) ? p.nombre : t.dniPaciente;
            System.out.println("- " + t.id + ": " + nombre + " - " + t.fechaHora + " - " + t.motivo);
        }
    }
    
    private void ordenarTurnos() {
        System.out.println("\n--- ORDENAR TURNOS ---");
        
        // Crear turnos de ejemplo con datos reales
        List<Turno> turnos = new ArrayList<>();
        turnos.add(new Turno("T010", "1111", "M1111", 
                           LocalDateTime.now().plusHours(5), 90, "Consulta larga"));
        turnos.add(new Turno("T011", "4444", "M1111", 
                           LocalDateTime.now().plusHours(1), 30, "Control rápido"));
        turnos.add(new Turno("T012", "3333", "M2222", 
                           LocalDateTime.now().plusHours(3), 60, "Consulta media"));
        
        System.out.println("Turnos originales (sin ordenar):");
        for (Turno t : turnos) {
            Paciente p = pacientes.get(t.dniPaciente);
            String nombre = (p != null) ? p.nombre : t.dniPaciente;
            System.out.println("- " + t.id + ": " + nombre + " - " + t.duracionMin + "min");
        }
        
        // Ordenar por duración usando Insertion Sort
        SortAlgorithms.insertionSort(turnos, SortAlgorithms.comparadorPorDuracion());
        
        System.out.println("\nTurnos ordenados por duración (Insertion Sort):");
        for (Turno t : turnos) {
            Paciente p = pacientes.get(t.dniPaciente);
            String nombre = (p != null) ? p.nombre : t.dniPaciente;
            System.out.println("- " + t.id + ": " + nombre + " - " + t.duracionMin + "min");
        }
    }
    
    private void menuUndoRedo() {
        System.out.println("\n--- AUDITORIA UNDO/REDO ---");
        
        int opcion;
        do {
            System.out.println("\n1) Agendar turno");
            System.out.println("2) Cancelar turno");
            System.out.println("3) Undo (deshacer)");
            System.out.println("4) Redo (rehacer)");
            System.out.println("5) Ver turnos actuales");
            System.out.println("6) Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("ID del turno: ");
                    String id = scanner.nextLine();
                    System.out.print("DNI paciente (1111, 4444, 5555, 3333): ");
                    String dni = scanner.nextLine();
                    System.out.print("Matricula medico (M1111, M2222): ");
                    String matricula = scanner.nextLine();
                    System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
                    String fechaStr = scanner.nextLine();
                    System.out.print("Duracion (minutos): ");
                    int duracion = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Motivo: ");
                    String motivo = scanner.nextLine();
                    
                    try {
                        LocalDateTime fecha = LocalDateTime.parse(fechaStr.replace(" ", "T"));
                        Turno t = new Turno(id, dni, matricula, fecha, duracion, motivo);
                        if (agendaConHistorial.agendar(t)) {
                            System.out.println("Turno agendado exitosamente!");
                        } else {
                            System.out.println("Error: No se pudo agendar (posible superposición)");
                        }
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido");
                    }
                    break;
                case 2:
                    System.out.print("ID del turno a cancelar: ");
                    String idCancel = scanner.nextLine();
                    if (agendaConHistorial.cancelar(idCancel)) {
                        System.out.println("Turno cancelado!");
                    } else {
                        System.out.println("Error: No se pudo cancelar el turno");
                    }
                    break;
                case 3:
                    if (agendaConHistorial.undo()) {
                        System.out.println("Última acción deshecha (Undo)");
                    } else {
                        System.out.println("No hay acciones para deshacer");
                    }
                    break;
                case 4:
                    if (agendaConHistorial.redo()) {
                        System.out.println("Acción rehecha (Redo)");
                    } else {
                        System.out.println("No hay acciones para rehacer");
                    }
                    break;
                case 5:
                    List<Turno> turnos = agendaConHistorial.getTurnosOrdenados();
                    System.out.println("Turnos en agenda con historial: " + turnos.size());
                    for (Turno t : turnos) {
                        Paciente p = pacientes.get(t.dniPaciente);
                        String nombre = (p != null) ? p.nombre : t.dniPaciente;
                        System.out.println("- " + t.id + ": " + nombre + " - " + t.fechaHora);
                    }
                    break;
            }
        } while (opcion != 6);
    }
    
    private void menuQuirofanos() {
        System.out.println("\n--- PLANIFICADOR DE QUIROFANOS ---");
        
        int opcion;
        do {
            System.out.println("\n1) Procesar nueva cirugía");
            System.out.println("2) Top médicos más ocupados");
            System.out.println("3) Volver");
            System.out.print("Seleccione: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("ID de la cirugía: ");
                    String id = scanner.nextLine();
                    System.out.print("Matrícula del médico (M1111, M2222): ");
                    String matricula = scanner.nextLine();
                    System.out.print("Duración en minutos: ");
                    int duracion = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Deadline (yyyy-MM-dd HH:mm): ");
                    String deadlineStr = scanner.nextLine();
                    
                    try {
                        LocalDateTime deadline = LocalDateTime.parse(deadlineStr.replace(" ", "T"));
                        SolicitudCirugia solicitud = new SolicitudCirugia(id, matricula, duracion, deadline);
                        planificadorQuirofano.procesar(solicitud);
                    } catch (Exception e) {
                        System.out.println("Formato de fecha inválido");
                    }
                    break;
                case 2:
                    System.out.print("¿Cuántos médicos mostrar? (K): ");
                    int k = scanner.nextInt();
                    scanner.nextLine();
                    
                    List<String> topMedicos = planificadorQuirofano.topKMedicosBloqueados(k);
                    System.out.println("\nTop " + k + " médicos más ocupados:");
                    for (int i = 0; i < topMedicos.size(); i++) {
                        System.out.println((i + 1) + ") " + topMedicos.get(i));
                    }
                    break;
            }
        } while (opcion != 3);
    }
    
    public static void main(String[] args) {
        SistemaGestionTurnos sistema = new SistemaGestionTurnos();
        sistema.ejecutar();
    }
}