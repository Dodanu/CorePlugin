package core.main.core.Profile;

import core.main.core.Rank.Rank;

public class Profile {

    public String displayName;

    public Rank rank;

    public Integer iRank;

    public Boolean muted;

    public Boolean banned;

    public Long banTime;

    public Boolean deathBanned;

    public Long deathBanTime;

    public Integer warns = 0;

    public String lastBanReason;

    public PlayStates playerState = PlayStates.Normal;

    public String getDisplayName() { return this.displayName; }

    public Rank getRank() { return this.rank; }

    public Integer getiRank() { return this.iRank; }

    public Boolean getMuted() { return this.muted; }

    public Boolean getBanned() { return this.banned; }

    public Long getBanTime() { return banTime; }

    public Boolean getDeathBanned() { return this.deathBanned; }

    public Long getDeathBanTime() { return deathBanTime; }

    public Integer getWarns() { return this.warns; }

    public String getLastBanReason() { return this.lastBanReason; }

    public PlayStates getPlayerState() { return playerState; }

    public void setDisplayName(String name) { this.displayName = name; }

    public void setRank(Rank rank) { this.rank = rank; }

    public void setiRank(Integer iRank) { this.iRank = iRank; }

    public void setMuted(Boolean muted) { this.muted = muted; }

    public void setBanned(Boolean banned) { this.banned = banned; }

    public void setBanTime(Long banTime) { this.banTime = banTime; }

    public void setDeathBanned(Boolean deathBanned) { this.deathBanned = deathBanned; }

    public void setDeathBanTime(Long deathBanTime) { this.deathBanTime = deathBanTime; }

    public void setWarns(Integer warns) { this.warns = warns; }

    public void setLastBanReason(String lastBanReason) {this.lastBanReason = lastBanReason; }

    public void setPlayerState(PlayStates playstate) { this.playerState = playstate; }

    public enum PlayStates {
        Normal, Frozen, Staff;
    }

}
