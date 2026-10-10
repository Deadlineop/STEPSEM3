// A1 - Overloaded Constructors for Hackathon Registration
// CONCEPT: this() chaining
// Solo participants get "Unassigned" as default team name via constructor chaining.

public class Participant {

    String name;
    String teamName;
    boolean registered;

    // Full constructor: participant with a known team
    public Participant(String name, String teamName) {
        this.name       = name;
        this.teamName   = teamName;
        this.registered = true;
    }

    // Solo constructor: chains to full constructor with "Unassigned"
    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.println(name + " | " + teamName + " | Registered: " + registered);
    }

    public static void main(String[] args) {
        String[] names     = { "Ravi", "Meera", "Karthik", "Divya" };
        String[] teamNames = { "ByteBusters", "", "CodeCrafters", "" };

        for (int i = 0; i < names.length; i++) {
            Participant p;
            if (teamNames[i].isEmpty()) {
                p = new Participant(names[i]);                    // solo -> "Unassigned"
            } else {
                p = new Participant(names[i], teamNames[i]);
            }
            p.printStatus();
        }
    }
}
