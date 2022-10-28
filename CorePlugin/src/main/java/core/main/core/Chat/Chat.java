package core.main.core.Chat;

public class Chat {

    public Boolean allChat;

    public Boolean partyChat;   //Va fii folosit mai incolo

    public Boolean staffChat;

    public Boolean factionChat;

    public Chat(){

        this.allChat = true;
        this.partyChat = false;
        this.staffChat = false;

    }

    public Boolean getAllChat() { return allChat; }

    public Boolean getPartyChat() { return partyChat; }

    public Boolean getStaffChat() { return staffChat; }

    public Boolean getFactionChat() { return this.factionChat; }

    public void setAllChat(Boolean allChat) { this.allChat = allChat; }

    public void setPartyChat(Boolean partyChat) { this.partyChat = partyChat; }

    public void setStaffChat(Boolean staffChat) { this.staffChat = staffChat; }

    public void setFactionChat(Boolean factionChat) { this.factionChat = factionChat; }
}
