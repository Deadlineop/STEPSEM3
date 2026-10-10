// A4 - LibraryMember JavaBean & Security Answer Property
// CONCEPT: JavaBean convention with two unusual protective patterns in the same class.
//
// Pattern 1 -- WRITE-ONCE (membershipId):
//   A setter exists (required by the framework scanner), but the second call is silently ignored.
//   Once membershipId is set to a non-null value, it never changes.
//
// Pattern 2 -- TRUE WRITE-ONLY (securityAnswer):
//   A setter exists, but NO getter exists anywhere on this class.
//   The value is transformed before storing (one-way hash: we simply uppercase here to
//   represent a deterministic one-way transform -- no real crypto library needed).
//   Even if someone could read the field, they'd only see the transformed form.
//
// NOTE: do NOT confuse the two:
//   membershipId  -- has a getter (readable), setter ignores calls after the first
//   securityAnswer -- has NO getter at all (truly unreadable from outside)

public class LibraryMember {

    private String  membershipId;    // write-once -- second setMembershipId() call is ignored
    private String  name;
    private boolean premiumMember;
    private String  securityAnswerHash;  // stored as one-way transform; never returned

    // --- JavaBean: mandatory public no-arg constructor ---
    public LibraryMember() {
        // membershipId starts null; the first set call will initialise it
    }

    // --- membershipId: write-once property ---
    public String getMembershipId() { return membershipId; }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            // First call: accept and store
            membershipId = id;
        }
        // Subsequent calls: silently ignored (no crash, no error message)
    }

    // --- name property: normal read/write ---
    public String getName()            { return name; }
    public void   setName(String name) { this.name = name; }

    // --- premiumMember: boolean uses isX() not getX() ---
    public boolean isPremiumMember()                    { return premiumMember; }
    public void    setPremiumMember(boolean premium)    { this.premiumMember = premium; }

    // --- securityAnswer: setter only -- NO getter anywhere on this class ---
    // One-way transform: uppercased before storing. The original answer is unrecoverable.
    public void setSecurityAnswer(String answer) {
        this.securityAnswerHash = answer.toUpperCase();  // deterministic but irreversible transform
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();

        // Write-once: first call takes effect
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());    // LIB-8841

        // Write-once: second call is silently ignored
        m.setMembershipId("FAKE-0000");
        System.out.println(m.getMembershipId());    // LIB-8841  (unchanged)

        System.out.println(m.isPremiumMember());    // true

        // Write-only: set but cannot retrieve
        m.setSecurityAnswer("BlueMountain");
        // m.getSecurityAnswer() -- does not compile: no such method
        System.out.println("Security answer set (no getter exists)");
    }
}
