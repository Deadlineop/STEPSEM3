// P4 - MovieBookingProfile JavaBean & OTP Property
// CONCEPT: JavaBean convention + a true write-only property.
//
// JavaBean rules:
//   1. Public no-argument constructor (required by JavaBean-scanning frameworks).
//   2. Every readable/writable field gets a getX()/setX() pair.
//      Exception for boolean: use isX(), not getX() (e.g., isConfirmed()).
//   3. The class must be public.
//
// Special design: otp is write-only.
//   setOtp() exists (the framework needs it), but NO getOtp() anywhere on the class.
//   Once set, the OTP cannot be read back -- it only exists to be validated elsewhere.

public class MovieBookingProfile {

    // --- fields ---
    private String  name;
    private boolean confirmed;
    private String  otp;   // stored but never returned -- write-only by design

    // --- JavaBean: public no-arg constructor (mandatory for framework scanning) ---
    public MovieBookingProfile() {
        // default values: name = null, confirmed = false
    }

    // Convenience constructor: takes just a name, chains to the no-arg constructor
    // so any initialisation logic there runs exactly once.
    public MovieBookingProfile(String name) {
        this();            // delegates to the no-arg constructor above
        this.name = name;
    }

    // --- name property ---
    public String getName()          { return name; }
    public void   setName(String name) { this.name = name; }

    // --- confirmed property: boolean uses isX() not getX() ---
    public boolean isConfirmed()               { return confirmed; }
    public void    setConfirmed(boolean confirmed) { this.confirmed = confirmed; }

    // --- otp: setter only -- no getter exists anywhere on this class ---
    public void setOtp(String otp) {
        this.otp = otp;   // stored internally; cannot be retrieved from outside
    }

    public static void main(String[] args) {
        // 1. Convenience constructor sets name
        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        System.out.println(p.getName());        // Rahul Dev

        // 2. Setter works normally
        p.setName("Priya Sharma");
        System.out.println(p.getName());        // Priya Sharma

        // 3. boolean property uses isX()
        p.setConfirmed(true);
        System.out.println(p.isConfirmed());    // true

        // 4. OTP can be set but never retrieved -- no observable output
        p.setOtp("4471");
        // p.getOtp() -- does not compile: no such method on this class
        System.out.println("OTP set (no getter exists)");
    }
}
