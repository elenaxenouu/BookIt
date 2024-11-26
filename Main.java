
public class Main {
    public static void main(String[] args) {
        AppointmentSystem system = new AppointmentSystem();

        // Προσθήκη υπηρεσιών από τον διαχειριστή
        system.addService(new Service("Service 1", 15.0, 1));
        system.addService(new Service("Service 2", 50.0, 3));
        system.addService(new Service("Service 3", 20.0, 1));
        system.addService(new Service("Service 4", 60.0, 2));
        system.addService(new Service("Service 5", 70.0, 3));
        system.addService(new Service("Service 6", 50.0, 2));
        system.addService(new Service("Service 7", 80.0, 3));
        
        
     
       Menu menu = new Menu(system);
       menu.showMainMenu();
                                                         
    }
}


