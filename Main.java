import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class RedHidraulicaConsola {
    private List<Tanque> tanques;
    private List<Cisterna> cisternas;
    private List<BombaAgua> bombas;
    private List<Turbina> turbinas;
    private Scanner scanner;

    public RedHidraulicaConsola() {
        this.tanques = new ArrayList<>();
        this.cisternas = new ArrayList<>();
        this.bombas = new ArrayList<>();
        this.turbinas = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Método principal para mostrar el menú
    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar tanque");
            System.out.println("2. Agregar cisterna");
            System.out.println("3. Agregar bomba de agua");
            System.out.println("4. Agregar turbina");
            System.out.println("5. Mostrar depósitos en mal o regular estado");
            System.out.println("6. Mostrar capacidad de tanques y cisternas ordenados");
            System.out.println("7. Mostrar mejor régimen de bombeo");
            System.out.println("8. Calcular tiempo promedio de bombeo de bombas en buen estado");
            System.out.println("9. Mostrar turbina de mayor fuerza");
            System.out.println("10. Calcular capacidad total de cisternas por forma y compartimentos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarTanque();
                    break;
                case 2:
                    agregarCisterna();
                    break;
                case 3:
                    agregarBomba();
                    break;
                case 4:
                    agregarTurbina();
                    break;
                case 5:
                    mostrarDepositosEnMalEstado();
                    break;
                case 6:
                    mostrarCapacidadOrdenada();
                    break;
                case 7:
                    mostrarMejorRegimen();
                    break;
                case 8:
                    calcularTiempoPromedioBombeo();
                    break;
                case 9:
                    mostrarTurbinaMayorFuerza();
                    break;
                case 10:
                    calcularCapacidadCisternas();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
    }

    // Métodos para cada funcionalidad
    private void agregarTanque() {
        System.out.print("Ingrese capacidad del tanque: ");
        double capacidad = scanner.nextDouble();
        System.out.print("Ingrese estado (bueno, regular, mal): ");
        String estado = scanner.next();
        System.out.print("Ingrese tipo de abasto: ");
        String tipoAbasto = scanner.next();
        System.out.print("Ingrese material (plástico, metal, fibrocemento): ");
        String material = scanner.next();

        tanques.add(new Tanque(capacidad, estado, tipoAbasto, material));
        System.out.println("Tanque agregado con éxito.");
    }

    private void agregarCisterna() {
        System.out.print("Ingrese capacidad de la cisterna: ");
        double capacidad = scanner.nextDouble();
        System.out.print("Ingrese estado (bueno, regular, mal): ");
        String estado = scanner.next();
        System.out.print("Ingrese tipo de abasto: ");
        String tipoAbasto = scanner.next();
        System.out.print("Ingrese clasificación (simple, compuesta): ");
        String clasificacion = scanner.next();
        System.out.print("Ingrese forma (si es simple): ");
        String forma = scanner.next();
        System.out.print("Ingrese cantidad de compartimentos (si es compuesta): ");
        int compartimentos = scanner.nextInt();

        cisternas.add(new Cisterna(capacidad, estado, tipoAbasto, clasificacion, forma, compartimentos));
        System.out.println("Cisterna agregada con éxito.");
    }

    private void agregarBomba() {
        System.out.print("Ingrese estado de la bomba (bueno, regular, mal): ");
        String estado = scanner.next();
        System.out.print("Ingrese régimen de bombeo: ");
        double regimen = scanner.nextDouble();
        System.out.print("Ingrese tiempo de bombeo: ");
        double tiempoBombeo = scanner.nextDouble();

        bombas.add(new BombaAgua(estado, regimen, tiempoBombeo));
        System.out.println("Bomba agregada con éxito.");
    }

    private void agregarTurbina() {
        System.out.print("Ingrese estado de la turbina (bueno, regular, mal): ");
        String estado = scanner.next();
        System.out.print("Ingrese régimen de la turbina: ");
        double regimen = scanner.nextDouble();
        System.out.print("Ingrese fuerza de la turbina: ");
        double fuerza = scanner.nextDouble();

        turbinas.add(new Turbina(estado, regimen, fuerza));
        System.out.println("Turbina agregada con éxito.");
    }

    private void mostrarDepositosEnMalEstado() {
        System.out.print("Ingrese tipo de abasto: ");
        String tipoAbasto = scanner.next();

        System.out.println("Tanques en mal o regular estado:");

        for (Deposito t : tanques) {
            if (("mal".equalsIgnoreCase(t.getEstado()) || "regular".equalsIgnoreCase(t.getEstado())) && t.getTipoAbasto().equalsIgnoreCase(tipoAbasto))
                System.out.println(t);
        }

        System.out.println("Cisternas en mal o regular estado:");
        for (Deposito c : cisternas) 
            if (("mal".equalsIgnoreCase(c.getEstado()) || "regular".equalsIgnoreCase(c.getEstado())) && c.getTipoAbasto().equalsIgnoreCase(tipoAbasto))
                System.out.println(c);
    }

    private void mostrarCapacidadOrdenada() {
        System.out.println("Capacidad de tanques ordenados por material:");
        tanques.stream()
                .sorted((t1, t2) -> t1.getMaterial().compareToIgnoreCase(t2.getMaterial()))
                .forEach(System.out::println);

        System.out.println("Capacidad de cisternas ordenadas por clasificación:");
        cisternas.stream()
                .sorted((c1, c2) -> c1.getClasificacion().compareToIgnoreCase(c2.getClasificacion()))
                .forEach(System.out::println);
    }

    private void mostrarMejorRegimen() {
        bombas.stream().max((b1, b2) -> Double.compare(b1.getRegimen(), b2.getRegimen()))
                .ifPresent(b -> System.out.println("Bomba con mejor régimen: " + b));

        turbinas.stream().max((t1, t2) -> Double.compare(t1.getRegimen(), t2.getRegimen()))
                .ifPresent(t -> System.out.println("Turbina con mejor régimen: " + t));
    }

    private void calcularTiempoPromedioBombeo() {
        double promedio = bombas.stream()
                .filter(b -> "bueno".equalsIgnoreCase(b.getEstado()))
                .mapToDouble(BombaAgua::getTiempoBombeo)
                .average()
                .orElse(0.0);
        System.out.println("Tiempo promedio de bombeo: " + promedio);
    }

    private void mostrarTurbinaMayorFuerza() {
        turbinas.stream().max((t1, t2) -> Double.compare(t1.getFuerza(), t2.getFuerza()))
                .ifPresent(t -> System.out.println("Turbina con mayor fuerza: " + t));
    }

    private void calcularCapacidadCisternas() {
        System.out.print("Ingrese la forma de la cisterna: ");
        String forma = scanner.next();
        System.out.print("Ingrese la cantidad de compartimentos: ");
        int compartimentos = scanner.nextInt();

        double capacidadTotal = cisternas.stream()
                .filter(c -> c.getForma().equalsIgnoreCase(forma) && c.getCompartimentos() == compartimentos)
                .mapToDouble(Cisterna::getCapacidad)
                .sum();

        System.out.println("Capacidad total de las cisternas: " + capacidadTotal);
    }

    public static void main(String[] args) {
        RedHidraulicaConsola app = new RedHidraulicaConsola();
        app.mostrarMenu();
    }
}

// Clase base para depósitos
abstract class Deposito {
    private double capacidad;
    private String estado;
    private String tipoAbasto;

    public Deposito(double capacidad, String estado, String tipoAbasto) {
        this.capacidad = capacidad;
        this.estado = estado;
        this.tipoAbasto = tipoAbasto;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoAbasto() {
        return tipoAbasto;
    }

    @Override
    public String toString() {
        return String.format("Capacidad: %.2f, Estado: %s, Tipo de Abasto: %s", capacidad, estado, tipoAbasto);
    }
}

// Clase para Tanques
class Tanque extends Deposito {
    private String material;

    public Tanque(double capacidad, String estado, String tipoAbasto, String material) {
        super(capacidad, estado, tipoAbasto);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return super.toString() + ", Material: " + material;
    }
}

// Clase para Cisternas
class Cisterna extends Deposito {
    private String clasificacion;
    private String forma;
    private int compartimentos;

    public Cisterna(double capacidad, String estado, String tipoAbasto, String clasificacion, String forma,
            int compartimentos) {
        super(capacidad, estado, tipoAbasto);
        this.clasificacion = clasificacion;
        this.forma = forma;
        this.compartimentos = compartimentos;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getForma() {
        return forma;
    }

    public int getCompartimentos() {
        return compartimentos;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Clasificación: %s, Forma: %s, Compartimentos: %d", clasificacion,
                forma, compartimentos);
    }
}

// Clase para Bombas de Agua
class BombaAgua {
    private String estado;
    private double regimen;
    private double tiempoBombeo;

    public BombaAgua(String estado, double regimen, double tiempoBombeo) {
        this.estado = estado;
        this.regimen = regimen;
        this.tiempoBombeo = tiempoBombeo;
    }

    public String getEstado() {
        return estado;
    }

    public double getRegimen() {
        return regimen;
    }

    public double getTiempoBombeo() {
        return tiempoBombeo;
    }

    @Override
    public String toString() {
        return String.format("Estado: %s, Régimen: %.2f, Tiempo de Bombeo: %.2f", estado, regimen, tiempoBombeo);
    }
}

// Clase para Turbinas
class Turbina {
    private String estado;
    private double regimen;
    private double fuerza;

    public Turbina(String estado, double regimen, double fuerza) {
        this.estado = estado;
        this.regimen = regimen;
        this.fuerza = fuerza;
    }

    public String getEstado() {
        return estado;
    }

    public double getRegimen() {
        return regimen;
    }

    public double getFuerza() {
        return fuerza;
    }

    @Override
    public String toString() {
        return String.format("Estado: %s, Régimen: %.2f, Fuerza: %.2f", estado, regimen, fuerza);
    }
}
