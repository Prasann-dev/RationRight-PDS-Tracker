import java.util.ArrayList;

class Family {
    String cardId;
    String headName;
    String cardType;
    int members;
    ArrayList<String> collectedMonths;

    public Family(String cardId, String headName, String cardType, int members) {
        this.cardId = cardId;
        this.headName = headName;
        this.cardType = cardType;
        this.members = members;
        this.collectedMonths = new ArrayList<>();
    }

    public void displayFamily() {
        System.out.println("Card ID: " + cardId + " | Head: " + headName +
                           " | Type: " + cardType + " | Members: " + members +
                           " | Collected Months: " + (collectedMonths.isEmpty() ? "None" : collectedMonths));
    }
}
