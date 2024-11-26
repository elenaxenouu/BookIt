
public class Main {
    public static void main(String[] args) {
         AppointmentSystem system = new AppointmentSystem();

        // Προσθήκη υπηρεσιών από τον διαχειριστή
        system.addService(new Service("Υπηρεσια1", 15.0, 1));
        system.addService(new Service("Υπηρεσια2", 50.0, 3));
        system.addService(new Service("Υπηρεσια3", 20.0, 1));
        system.addService(new Service("Υπηρεσια4", 35.0, 1));
        system.addService(new Service("Υπηρεσια5", 10.0, 2));
        system.addService(new Service("Υπηρεσια6", 20.0, 3));
        system.addService(new Service("Υπηρεσια7", 32.0, 2));
        // εδω θελουμε ελεγχο εγκυροτητας ο χρηστης πρεπει να μπορει να πληκτρολογει νουμερα απο 1-7 διαφορετικα μηνυμα λαθους 
        
        Menu menu = new Menu(system);
        menu.showMainMenu();
        
    }
}
