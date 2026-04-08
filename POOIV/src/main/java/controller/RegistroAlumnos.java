package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import model.Alumno;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author torri
 */
public class RegistroAlumnos {
    
    public static ArrayList<Alumno> alumnos = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);

        int opc = 0;
        boolean exit = false;

        do {
            System.out.println("\n");
            System.out.println("1.Agregar nuevo alumno");
            System.out.println("2.Mostrar lista");
            System.out.println("3.Eliminar alumno registro");
            System.out.println("4.Buscar alumno por DNI");
            System.out.println("5.Exit");
            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    RegistroAlumnos.addAlumnos();
                    break;
                case 2:
                    RegistroAlumnos.showAlumnos();
                    break;
               case 3:
                    RegistroAlumnos.deleteAlumnos();
                    break;
                case 4:
                    RegistroAlumnos.searchDNI();
                    break;
                case 5:
                    exit = true;
                    break;

            }
        } while (!exit);

    }
//public static void cargarAlumnos() {
//    File f = new File("registros.txt");
//
//    try (BufferedReader br = new BufferedReader(new FileReader(f))) {
//
//        String linea;
//
//        while ((linea = br.readLine()) != null) {
//            String[] partes = linea.split(",");
//
//            String nombre = partes[0];
//            String apellido = partes[1];
//            int edad = Integer.parseInt(partes[2]);
//            String curso = partes[3];
//            String dni = partes[4];
//
//            Alumno a = new Alumno(nombre, apellido, edad, curso, dni);
//            alumnos.add(a);
//        }
//
//    } catch (IOException e) {
//        System.out.println("Error al cargar alumnos");
//    }
//}
    public static void writeFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f, true);
        PrintWriter pw = new PrintWriter(fw);
        Scanner sc = new Scanner(System.in);
        //ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Add new product: " + i);
            System.out.print("Write name: ");
            String name = sc.next();
            System.out.print("Write price: ");
            float price = sc.nextFloat();
            System.out.print("Write description: ");
            String description = sc.next();
            // add products to array list
            //products.add(new Product(price, name, description));
            // Write data in file
            //pw.append(products.get(i).toString());
        }
        pw.close();
        fw.close();

    }

    private static void addAlumnos() {
        FileWriter fw;
        try {
            fw = new FileWriter(new File("registros.txt"), true);
            PrintWriter pw = new PrintWriter(fw);
            Scanner sc = new Scanner(System.in);
            System.out.println("Nombre Alumno?");
            String nombre = sc.nextLine();
            System.out.println("Apellido Alumno?");
            String apellido = sc.nextLine();
            System.out.println("Edad alumno?");
            int edad = sc.nextInt();
            sc.nextLine();
            System.out.println("Curso alumno?");
            String curso = sc.nextLine();
            
            System.out.println("Dni alumno?");
            String DNI = sc.nextLine();
            Alumno alumno = new Alumno(nombre, apellido, edad, curso, DNI);

            pw.println(alumno.toFileString());
            pw.close();
            fw.close();

        } catch (IOException ex) {
            System.getLogger(RegistroAlumnos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    private static void showAlumnos() throws IOException{
        File f = new File(System.getProperty("user.dir") + File.separator + "registros.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        fr.close();
        br.close();
    }

    private static boolean existeDNI(String dni) {
    try {
        BufferedReader br = new BufferedReader(new FileReader("registros.txt"));
        String linea;

        while ((linea = br.readLine()) != null) {
            if (linea.trim().contains(dni)) {
                br.close();
                return true;
            }
        }
        br.close();

    } catch (IOException e) {
        System.out.println("Error al verificar DNI");
    }

    return false;
}
    private static void searchDNI() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese DNI a buscar:");
    String dni = sc.nextLine();

    try {
        BufferedReader br = new BufferedReader(new FileReader("registros.txt"));
        String linea;
        boolean encontrado = false;

        while ((linea = br.readLine()) != null) {
            if (linea.contains(dni)) {
                System.out.println("Alumno encontrado:");
                System.out.println(linea);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Alumno no encontrado.");
        }

        br.close();

    } catch (IOException e) {
        System.out.println("Error al buscar alumno");
    }
}
    
    private static void deleteAlumnos() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese DNI a eliminar:");
    String dni = sc.nextLine();

    File inputFile = new File("registros.txt");
    File tempFile = new File("temp.txt");

    try {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String linea;
        boolean eliminado = false;

        while ((linea = br.readLine()) != null) {
            if (!linea.contains(dni)) {
                pw.println(linea);
            } else {
                eliminado = true;
            }
        }

        br.close();
        pw.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        if (eliminado) {
            System.out.println("Alumno eliminado correctamente ✅");
        } else {
            System.out.println("No se encontró el DNI.");
        }

    } catch (IOException e) {
        System.out.println("Error al eliminar alumno");
    }
}
    
   
}

