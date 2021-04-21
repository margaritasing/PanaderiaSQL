package com.company.myapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) {
        System.out.println("Welcome to the panaderia");
        // muestro lo que tengo en stock
        List<Producto> productos = ProductoDAO.getProductos();
        for(Producto producto:productos)
            System.out.println(producto);

        int op=0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("¿Desea crear nuevo pedido (1) o pagar (2) uno existente? Salir con 0. ");
            op = Integer.parseInt(sc.nextLine());
            switch (op){
                case 1 -> crearNuevoPedido();
                case 2 -> pagar();
            }
        }while(op!=0);
    }

    private static void crearNuevoPedido() {
        int op=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nueva orden. Indique 0 para salir.");
        HashMap<Integer, Integer> productosPorPedido = new HashMap<>();
        do {
            System.out.print("Indique el ID de producto: ");
            int idProd = Integer.parseInt(sc.nextLine());
            System.out.print("Indique la cantidad deseada: ");
            int cantidad = Integer.parseInt(sc.nextLine());
            productosPorPedido.put(idProd, cantidad);
            System.out.print("¿Desea finalizar? (1=No, 0=Si) ");
            op = Integer.parseInt(sc.nextLine());
        }while(op!=0);
        Pedido pedido = new Pedido(productosPorPedido); //Crea el pedido en la DB
        System.out.println("Pedido creado!");
        System.out.println(pedido.verDetalles());
    }

    private static void pagar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID del pedido: ");
        int idPedido = Integer.parseInt(sc.nextLine());
        Pedido pedido = new Pedido(idPedido);
        System.out.println(pedido.verDetalles());
        System.out.print("¿Está ok? 1=SI, 0=NO ");
        if(Integer.parseInt(sc.nextLine()) == 1){
            System.out.print("¿Con cuánto abona? ");
            int pago = Integer.parseInt(sc.nextLine());
            int vuelto = pedido.pagar(pago);
            if(vuelto>0) System.out.println("Vuelto: $"+vuelto);
            System.out.println("Listo!");
        }
    }
}