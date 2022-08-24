package me.jack.projectvanguardcore.utils;

import me.jack.projectvanguardcore.Projectvanguardcore;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SQLGetter {

    private Projectvanguardcore pl;

    public SQLGetter(Projectvanguardcore pl) {
        this.pl = pl;
    }

    public void createTable() {
        PreparedStatement ps;
        PreparedStatement ps2;
        try {
            ps = pl.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS vgrd_players " + "(userID INT NOT NULL AUTO_INCREMENT, IGN VARCHAR(100), minecraftUUID VARCHAR(100), IP VARCHAR(100), friendsCount INT(255), allianceID INT(10), firstJoin VARCHAR(100), lastJoin VARCHAR(100), rankID INT(20), teamID INT(1000), PRIMARY KEY(userID))");
            ps2 = pl.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS vgrd_ranks (rankID INT NOT NULL AUTO_INCREMENT, rankName VARCHAR(100), rankColour VARCHAR(100), rankHEX VARCHAR(100), staff BOOL");
            ps.executeUpdate();
            ps2.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        try {
            UUID uuid = player.getUniqueId();
            PreparedStatement ps = pl.sql.getConnection().prepareStatement("SELECT * FROM vgd_users WHERE minecraftUUID=?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();
            rs.next();

            if(!exists(uuid)){
                PreparedStatement ps2 = pl.sql.getConnection().prepareStatement("INSET IGNORE INTO vgd_users" +
                        "(IGN, minecraftUUID, IP, rankID, allianceID, firstJoin, lastJoin, friendsCount) VALUES (?,?,?,?,?,?,?)");
                ps2.setString(1, player.getName());
                ps2.setInt(4, 0);
                ps2.setString(3, player.getAddress().toString());
                ps2.setString(3, uuid.toString());
                ps2.setInt(5, 0);
                ps2.setString(6, new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
                ps2.setString(7,new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
                ps2.setInt(8, 0);
                ps2.execute();

                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid) {
        try {
            PreparedStatement ps = pl.sql.getConnection().prepareStatement("SELECT * FROM vgd_users WHERE UUID=?");
            ps.setString(1, uuid.toString());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {


        }
        return false;
    }

    public String getRank(UUID uuid) {
        try {
            PreparedStatement ps = pl.sql.getConnection().prepareStatement("SELECT * FROM vgd_players WHERE UUID=?");
            ps.setString(1, "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int rankID = rs.getInt("rankID");
                PreparedStatement ps2 = pl.sql.getConnection().prepareStatement("SELECT * FROM vgd_ranks WHERE rankID=?");
                ps2.setInt(1, rankID);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    return rs2.getString("rankName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "N/A";
    }

    public String getRankColour(UUID uuid){
        try {
            PreparedStatement ps = pl.sql.getConnection().prepareStatement("SELECT * FROM vgd_players WHERE UUID=?");
            ps.setString(1, "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int rankID = rs.getInt("rankID");
                PreparedStatement ps2 = pl.sql.getConnection().prepareStatement("SELECT * FROM vgd_ranks WHERE rankID=?");
                ps2.setInt(1, rankID);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    return rs2.getString("rankColour");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "N/A";
    }


}
